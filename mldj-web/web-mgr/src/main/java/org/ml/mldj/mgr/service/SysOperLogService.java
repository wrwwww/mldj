package org.ml.mldj.mgr.service;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysOperLogQuery;
import org.ml.mldj.model.system.entity.SysOperLog;
import org.ml.mldj.system.client.SysOperLogFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysOperLogService   {

	@Autowired
	private SysOperLogFeignClient sysOperLogFeignClient;


	public Result<PageVO<SysOperLog>> findPage(PageQuery<SysOperLogQuery> sysOperLogQuery) {
		return sysOperLogFeignClient.findPage(sysOperLogQuery);
	}


	public Result<?> saveSysLog(SysOperLog sysOperLog) {
		return sysOperLogFeignClient.saveSysLog(sysOperLog);
	}


	public Result<SysOperLog> getById(Long id) {
		return sysOperLogFeignClient.getById(id);
	}
}
