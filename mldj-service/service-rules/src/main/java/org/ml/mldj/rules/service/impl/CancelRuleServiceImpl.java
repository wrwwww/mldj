package org.ml.mldj.rules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ml.mldj.rules.mapper.CancelRuleMapper;
import org.ml.mldj.model.rules.entity.CancelRule;
import org.ml.mldj.rules.service.ICancelRuleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单取消规则表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class CancelRuleServiceImpl extends ServiceImpl<CancelRuleMapper, CancelRule> implements ICancelRuleService {

}
