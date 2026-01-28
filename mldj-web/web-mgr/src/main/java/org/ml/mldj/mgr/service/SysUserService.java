package org.ml.mldj.mgr.service;


import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysUserQuery;
import org.ml.mldj.model.system.entity.SysUser;
import org.ml.mldj.system.client.SysUserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

    @Autowired
    private SysUserFeignClient sysUserFeignClient;


    public Result<SysUser> getById(Long id) {
        return sysUserFeignClient.getById(id);
    }


    public Result<?> save(SysUser sysUser) {
        return sysUserFeignClient.save(sysUser);
    }


    public Result<?> update(SysUser sysUser) {
        return sysUserFeignClient.update(sysUser);
    }


    public Result<?> remove(Long id) {
        return sysUserFeignClient.remove(id);
    }


    public Result<PageVO<SysUser>> findPage(PageQuery<SysUserQuery> sysUserQuery) {
        return sysUserFeignClient.findPage(sysUserQuery);
    }


    public Result<?> updateStatus(Long id, Integer status) {
       return  sysUserFeignClient.updateStatus(id, status);
    }

}
