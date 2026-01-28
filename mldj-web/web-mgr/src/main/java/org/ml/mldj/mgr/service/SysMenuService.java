package org.ml.mldj.mgr.service;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.vo.AssginMenuVo;
import org.ml.mldj.system.client.SysMenuFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuService   {

    @Autowired
    private SysMenuFeignClient sysMenuFeignClient;


    public Result<?> save(SysMenu sysMenu) {
     return    sysMenuFeignClient.save(sysMenu);
    }


    public Result<?> update(SysMenu sysMenu) {
      return   sysMenuFeignClient.update(sysMenu);
    }


    public Result<?> remove(Long id) {
      return   sysMenuFeignClient.remove(id);
    }


    public List<SysMenu> findNodes() {
        return sysMenuFeignClient.findNodes();
    }


    public Result<?> doAssign(AssginMenuVo assginMenuVo) {
      return   sysMenuFeignClient.doAssign(assginMenuVo);
    }


    public Result<?> toAssign(Long roleId) {
        return sysMenuFeignClient.toAssign(roleId);
    }
}
