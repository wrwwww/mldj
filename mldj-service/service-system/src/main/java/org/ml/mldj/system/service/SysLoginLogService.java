package org.ml.mldj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.entity.SysLoginLog;
import org.ml.mldj.system.mapper.SysLoginLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统登录日志表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class SysLoginLogService extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements IService<SysLoginLog> {

    public PageVO<SysLoginLog> page(PageQuery<?> query) {
        Page<SysLoginLog> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<SysLoginLog> result = this.page(page);
        return PageVO.buildPageVO(result);
    }
}
