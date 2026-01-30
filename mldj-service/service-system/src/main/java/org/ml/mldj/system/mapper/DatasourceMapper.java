package org.ml.mldj.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ml.mldj.model.system.Datasource;

@Mapper
public interface DatasourceMapper extends BaseMapper<Datasource> {
}