package org.ml.mldj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.entity.SysOperLog;
import org.ml.mldj.system.mapper.SysOperLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class SysOperLogService extends ServiceImpl<SysOperLogMapper, SysOperLog> implements IService<SysOperLog> {

    public PageVO<SysOperLog> page(PageQuery<?> query) {
        Page<SysOperLog> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<SysOperLog> result = this.page(page);
        return PageVO.buildPageVO(result);
    }
}
