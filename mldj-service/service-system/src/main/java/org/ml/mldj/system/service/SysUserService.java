package org.ml.mldj.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.entity.SysUser;
import org.ml.mldj.system.mapper.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements IService<SysUser> {

    /**
     * 分页查询用户
     */
    public PageVO<SysUser> page(PageQuery<?> query) {
        Page<SysUser> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<SysUser> result = this.page(page);
        return PageVO.buildPageVO(result);
    }

    /**
     * 更新用户状态
     */
    public boolean updateStatus(Long id, Integer status) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setStatus(status);
        return this.updateById(user);
    }

    /**
     * 根据用户名查询
     */
    public SysUser queryByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username).last("limit 1");
        return this.getOne(wrapper);
    }
}
