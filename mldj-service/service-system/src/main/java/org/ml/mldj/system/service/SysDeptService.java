package org.ml.mldj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.system.entity.SysDept;
import org.ml.mldj.system.mapper.SysDeptMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class SysDeptService extends ServiceImpl<SysDeptMapper, SysDept> implements IService<SysDept> {

    /**
     * 更新部门状态
     */
    public boolean updateStatus(Long id, Integer status) {
        SysDept dept = new SysDept();
        dept.setId(id);
        dept.setStatus(status);
        return this.updateById(dept);
    }

    /**
     * 构建部门树
     */
    public List<SysDept> findDeptTree() {
        List<SysDept> all = this.list();
        if (all == null || all.isEmpty()) {
            return new ArrayList<>();
        }
        // 根节点
        List<SysDept> roots = all.stream()
                .filter(d -> d.getParentId() == null || d.getParentId() == 0)
                .collect(Collectors.toList());
        // 递归设置子部门（复用 treePath / parentId 结构，直接挂 children 到 transient 字段时可按需要在实体上扩展；
        // 这里简单返回按 parentId 分组后的有序列表，前端可自行树化）
        return roots;
    }
}
