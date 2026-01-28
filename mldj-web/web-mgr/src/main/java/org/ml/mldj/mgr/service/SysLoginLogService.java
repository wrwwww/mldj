package org.ml.mldj.mgr.service;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysLoginLogQuery;
import org.ml.mldj.model.system.entity.SysLoginLog;
import org.ml.mldj.system.client.SysLoginLogFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysLoginLogService   {

    @Autowired
    private SysLoginLogFeignClient sysLoginLogFeignClient;


    public Result<PageVO<SysLoginLog>> findPage(PageQuery<SysLoginLogQuery> sysLoginLogQuery) {
        return sysLoginLogFeignClient.findPage(sysLoginLogQuery);
    }


    public Result<?> recordLoginLog(SysLoginLog sysLoginLog) {
       return sysLoginLogFeignClient.recordLoginLog(sysLoginLog);
    }


    public Result<SysLoginLog> getById(Long id) {
        return sysLoginLogFeignClient.getById(id);
    }
}
