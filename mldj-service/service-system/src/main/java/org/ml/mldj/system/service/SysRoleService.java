package org.ml.mldj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.vo.AssginRoleVo;
import org.ml.mldj.system.mapper.SysRoleMapper;
import org.ml.mldj.system.mapper.SysUserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> implements IService<SysRole> {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    public List<SysRole> toAssign(Long userId) {
        return sysRoleMapper.queryRoleByUserId(userId);
    }

    public PageVO<SysRole> page(PageQuery<?> query) {
        Page<SysRole> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<SysRole> result = this.page(page);
        return PageVO.buildPageVO(result);
    }

    /**
     * 给用户分配角色
     */
    public void doAssign(AssginRoleVo assginRoleVo) {
        Long userId = assginRoleVo.getUserId();
        List<Long> roleIdList = assginRoleVo.getRoleIdList();

        // 1. 删除原有用户角色关联
        sysUserRoleMapper.deleteByUserId(userId);

        // 2. 插入新的关联
        if (roleIdList != null && !roleIdList.isEmpty()) {
            List<org.ml.mldj.model.system.entity.SysUserRole> list = roleIdList.stream().map(roleId -> {
                org.ml.mldj.model.system.entity.SysUserRole ur = new org.ml.mldj.model.system.entity.SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                return ur;
            }).collect(Collectors.toList());
            if (!list.isEmpty()) {
                sysUserRoleMapper.batchInsert(list);
            }
        }
    }
}
