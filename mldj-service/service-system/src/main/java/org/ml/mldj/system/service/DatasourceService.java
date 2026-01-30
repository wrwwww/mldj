package org.ml.mldj.system.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.Datasource;
import org.ml.mldj.model.system.TableMeta;
import org.ml.mldj.system.mapper.DatasourceMapper;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DatasourceService extends ServiceImpl<DatasourceMapper, Datasource> {

    private final DatasourceMapper datasourceMapper;

    public boolean testConnection(Datasource datasource) {
        Connection conn = null;
        try {
            String url = buildJdbcUrl(datasource);
            conn = DriverManager.getConnection(url, datasource.getUsername(), datasource.getPassword());
            return conn.isValid(5);
        } catch (Exception e) {
            log.error("测试数据库连接失败", e);
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
        }
    }

    public boolean testConnection(String id) {
        Datasource datasource = getById(id);
        if (datasource == null) {
            throw new RuntimeException("数据源不存在");
        }
        return testConnection(datasource);
    }

    public List<TableMeta> getTables(String datasourceId) {
        Datasource datasource = getById(datasourceId);
        if (datasource == null) {
            throw new RuntimeException("数据源不存在");
        }

        List<TableMeta> tables = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;

        try {
            String url = buildJdbcUrl(datasource);
            conn = DriverManager.getConnection(url, datasource.getUsername(), datasource.getPassword());

            // 获取所有表（MySQL示例）
            DatabaseMetaData metaData = conn.getMetaData();

            if ("mysql".equalsIgnoreCase(datasource.getDbType())) {
                rs = metaData.getTables(datasource.getDatabaseName(), null, "%", new String[]{"TABLE"});
            } else if ("postgresql".equalsIgnoreCase(datasource.getDbType())) {
                rs = metaData.getTables(null, "public", "%", new String[]{"TABLE"});
            } else {
                rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            }

            while (rs.next()) {
                TableMeta table = new TableMeta();
                table.setTableName(rs.getString("TABLE_NAME"));
                table.setTableComment(rs.getString("REMARKS"));
                table.setColumns(getColumns(metaData, datasource, table.getTableName()));
                tables.add(table);
            }

        } catch (Exception e) {
            log.error("获取表信息失败", e);
            throw new RuntimeException("获取表信息失败: " + e.getMessage());
        } finally {
            closeResources(conn, rs, null);
        }

        return tables;
    }

    private List<TableMeta.ColumnMeta> getColumns(DatabaseMetaData metaData, Datasource datasource, String tableName) throws SQLException {
        List<TableMeta.ColumnMeta> columns = new ArrayList<>();
        ResultSet columnRs = null;

        try {
            if ("mysql".equalsIgnoreCase(datasource.getDbType())) {
                columnRs = metaData.getColumns(datasource.getDatabaseName(), null, tableName, "%");
            } else if ("postgresql".equalsIgnoreCase(datasource.getDbType())) {
                columnRs = metaData.getColumns(null, "public", tableName, "%");
            } else {
                columnRs = metaData.getColumns(null, null, tableName, "%");
            }

            // 获取主键
            ResultSet pkRs = metaData.getPrimaryKeys(null, null, tableName);
            List<String> primaryKeys = new ArrayList<>();
            while (pkRs.next()) {
                primaryKeys.add(pkRs.getString("COLUMN_NAME"));
            }
            pkRs.close();

            while (columnRs.next()) {
                TableMeta.ColumnMeta column = new TableMeta.ColumnMeta();
                column.setColumnName(columnRs.getString("COLUMN_NAME"));
                column.setColumnType(columnRs.getString("TYPE_NAME"));
                column.setColumnComment(columnRs.getString("REMARKS"));
                column.setColumnSize(columnRs.getInt("COLUMN_SIZE"));
                column.setDecimalDigits(columnRs.getInt("DECIMAL_DIGITS"));
                column.setIsNullable("YES".equals(columnRs.getString("IS_NULLABLE")));
                column.setIsPrimaryKey(primaryKeys.contains(column.getColumnName()));
                columns.add(column);
            }
        } finally {
            if (columnRs != null) columnRs.close();
        }

        return columns;
    }

    public PageVO<Datasource> page(PageQuery<Datasource> query) {
        // 使用 MyBatis-Plus 分页查询
        Page<Datasource> mpPage =
                new Page<>(query.getPageNum(),
                        query.getPageSize());


        LambdaQueryWrapper<Datasource> queryWrapper = new LambdaQueryWrapper<>();
        if (query.getFilters() != null) {
            queryWrapper
                    .like(query.getFilters().getName() != null, Datasource::getName, query.getFilters().getName());
        }

        mpPage=datasourceMapper.selectPage(mpPage, queryWrapper);
        return PageVO.buildPageVO(mpPage);

    }

    private String buildJdbcUrl(Datasource datasource) {
        String dbType = datasource.getDbType().toLowerCase();
        switch (dbType) {
            case "mysql":
                return String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true%s",
                        datasource.getHost(), datasource.getPort(), datasource.getDatabaseName(),
                        datasource.getParams() != null ? "&" + datasource.getParams() : "");
            case "postgresql":
                return String.format("jdbc:postgresql://%s:%d/%s%s",
                        datasource.getHost(), datasource.getPort(), datasource.getDatabaseName(),
                        datasource.getParams() != null ? "?" + datasource.getParams() : "");
            default:
                throw new IllegalArgumentException("不支持的数据库类型: " + dbType);
        }
    }

    private void closeResources(Connection conn, ResultSet rs, Statement stmt) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            log.error("关闭ResultSet失败", e);
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            log.error("关闭Statement失败", e);
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            log.error("关闭Connection失败", e);
        }
    }

    public List<Datasource> list() {
        return datasourceMapper.selectList(new LambdaQueryWrapper<Datasource>().orderByAsc(Datasource::getId));
    }

    public Datasource getById(String id) {
        return datasourceMapper.selectById(id);
    }
}