package org.ml.mldj.mgr.service;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysRoleQuery;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.vo.AssginRoleVo;
import org.ml.mldj.system.client.SysRoleFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleService   {

    @Autowired
    private SysRoleFeignClient sysRoleFeignClient;


    public Result<SysRole> getById(Long id) {
        return sysRoleFeignClient.getById(id);
    }


    public Result<?> save(SysRole sysRole) {
        return sysRoleFeignClient.save(sysRole);
    }


    public Result<?> update(SysRole sysRole) {
      return   sysRoleFeignClient.update(sysRole);
    }


    public Result<?> remove(Long id) {
       return sysRoleFeignClient.remove(id);
    }


    public Result<PageVO<SysRole>> findPage(PageQuery<SysRoleQuery> roleQuery) {
        return sysRoleFeignClient.findPage(roleQuery);
    }


    public Result<?> batchRemove(List<Long> idList) {
      return   sysRoleFeignClient.batchRemove(idList);
    }


    public Result<?> toAssign(Long userId) {
        return sysRoleFeignClient.toAssign(userId);
    }


    public Result<?> doAssign(AssginRoleVo assginRoleVo) {
      return   sysRoleFeignClient.doAssign(assginRoleVo);
    }


    public List<SysRole> findAll() {
        return sysRoleFeignClient.findAll();
    }
}
