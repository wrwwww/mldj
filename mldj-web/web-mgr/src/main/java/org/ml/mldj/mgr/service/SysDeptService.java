package org.ml.mldj.mgr.service;


import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.system.entity.SysDept;
import org.ml.mldj.system.client.SysDeptFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysDeptService {

    @Autowired
    private SysDeptFeignClient sysDeptFeignClient;



    public Result<List<SysDept>> findNodes() {
        return sysDeptFeignClient.findNodes();
    }


    public Result<List<SysDept>> findUserNodes() {
        return sysDeptFeignClient.findUserNodes();
    }


    public Result<?> updateStatus(Long id, Integer status) {
        return sysDeptFeignClient.updateStatus(id, status);
    }


    public Result<SysDept> getById(Long id) {
        return sysDeptFeignClient.getById(id);
    }


    public Result<?> save(SysDept sysDept) {
        return sysDeptFeignClient.save(sysDept);
    }


    public Result<?> update(SysDept sysDept) {
        return sysDeptFeignClient.update(sysDept);
    }


    public Result<?> remove(Long id) {
       return sysDeptFeignClient.remove(id);
    }
}

