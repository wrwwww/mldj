package org.ml.mldj.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.vo.AssginRoleVo;
import org.ml.mldj.system.config.QueryCondition;
import org.ml.mldj.system.mapper.SysRoleMapper;
import org.ml.mldj.system.mapper.SysUserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;


    /**
     * 分页查询角色表
     */
    public PageVO<SysRole> page(PageQuery<SysRole> pageQuery) {
        Page<SysRole> pageInfo = pageQuery.toPage();
        // 构建查询条件
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        SysRole filters = pageQuery.getFilters();
        if (filters != null) {
            for (Field field : filters.getClass().getDeclaredFields()) {
                try {
                    String o = (String)field.get(filters);
                    if (!Strings.isNullOrEmpty(o)) {
                        QueryCondition annotation = field.getAnnotation(QueryCondition.class);
                        if (annotation!=null){
                            switch (annotation.type()){
                                case EQ -> {
                                    queryWrapper.eq(field.getName(),o);
                                }

                            }

                        }
//                        queryWrapper.
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // 构建排序条件
        pageQuery.getSorts().forEach(e -> {
            String column = e.getUnderlineColumn();
            if (e.isAscending()) {
                queryWrapper.orderByAsc(column);
            } else {
                queryWrapper.orderByDesc(column);
            }
        });
        // 分页查询
        Page<SysRole> result = sysRoleMapper.selectPage(pageInfo, queryWrapper);
        return PageVO.buildPageVO(result);
    }

    /**
     * 根据ID查询角色表
     */
    public SysRole getById(String id) {
        return sysRoleMapper.selectById(id);
    }

    /**
     * 新增角色表
     */
    public Long save(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
        return sysRole.getId();
    }

    /**
     * 修改角色表
     */
    public void update(SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        sysRoleMapper.updateById(sysRole);
    }

    /**
     * 删除角色表
     */
    public void delete(String id) {
        sysRoleMapper.deleteById(id);
    }


    public List<SysRole> toAssign(Long userId) {
        return sysRoleMapper.queryRoleByUserId(userId);
    }


    /**
     * 给用户分配角色
     */
    public void doAssign(AssginRoleVo assginRoleVo) {
        Long userId = assginRoleVo.getUserId();
        List<Long> roleIdList = assginRoleVo.getRoleIdList();

        // 1. 删除原有用户角色关联
        sysUserRoleMapper.deleteByUserId(userId);

        // 2. 插入新的关联
        if (roleIdList != null && !roleIdList.isEmpty()) {
            List<org.ml.mldj.model.system.entity.SysUserRole> list = roleIdList.stream().map(roleId -> {
                org.ml.mldj.model.system.entity.SysUserRole ur = new org.ml.mldj.model.system.entity.SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                return ur;
            }).collect(Collectors.toList());
            if (!list.isEmpty()) {
                sysUserRoleMapper.batchInsert(list);
            }
        }
    }

    public List<SysRole> list() {
        return sysRoleMapper.selectList(new QueryWrapper<>());
    }

    public int removeBatchByIds(List<Long> idList) {
        return sysRoleMapper.deleteByIds(idList);
    }
}
