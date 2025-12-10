package org.ml.mldj.driver.service.impl;

import org.ml.mldj.model.entity.Dept;
import org.ml.mldj.model.mapper.DeptMapper;
import org.ml.mldj.model.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
