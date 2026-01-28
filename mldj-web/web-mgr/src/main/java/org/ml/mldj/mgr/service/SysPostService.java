package org.ml.mldj.mgr.service;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysPostQuery;
import org.ml.mldj.model.system.entity.SysPost;
import org.ml.mldj.system.client.SysPostFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysPostService   {

    @Autowired
    private SysPostFeignClient sysPostFeignClient;


    public Result<SysPost> getById(Long id) {
        return sysPostFeignClient.getById(id);
    }


    public Result<?> save(SysPost sysPost) {
        return sysPostFeignClient.save(sysPost);
    }


    public Result<?> update(SysPost sysPost) {
      return   sysPostFeignClient.update(sysPost);
    }


    public Result<?> remove(Long id) {
       return sysPostFeignClient.remove(id);
    }


    public Result<PageVO<SysPost>> findPage(PageQuery<SysPostQuery> sysPostQuery) {
        return sysPostFeignClient.findPage(sysPostQuery);
    }


    public Result<?> updateStatus(Long id, Integer status) {
        return sysPostFeignClient.updateStatus(id, status);
    }


    public List<SysPost> findAll() {
        return sysPostFeignClient.findAll();
    }
}
