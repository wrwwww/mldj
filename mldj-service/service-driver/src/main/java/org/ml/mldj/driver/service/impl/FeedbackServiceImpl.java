package org.ml.mldj.driver.service.impl;

import org.ml.mldj.model.entity.Feedback;
import org.ml.mldj.model.mapper.FeedbackMapper;
import org.ml.mldj.model.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

}
