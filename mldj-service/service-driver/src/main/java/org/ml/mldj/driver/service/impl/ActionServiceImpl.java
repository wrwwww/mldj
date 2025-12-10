package org.ml.mldj.driver.service.impl;

import org.ml.mldj.model.entity.Action;
import org.ml.mldj.model.mapper.ActionMapper;
import org.ml.mldj.model.service.IActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行为表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class ActionServiceImpl extends ServiceImpl<ActionMapper, Action> implements IActionService {

}
