package org.ml.mldj.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.entity.SysUser;
import org.ml.mldj.system.mapper.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 分页查询用户
     */
    public PageVO<SysUser> page(PageQuery<?> query) {
        Object filters = query.getFilters();

        Page<SysUser> page = new Page<>(query.getPageNum(), query.getPageSize());
        // 构建查询条件
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        // 分页查询
        Page<SysUser> result = sysUserMapper.selectPage(page, queryWrapper);

        return PageVO.buildPageVO(result);
    }

    /**
     * 修改用户表
     */
    public void update(SysUser sysUser) {
        sysUserMapper.updateById(sysUser);
    }

    /**
     * 根据用户名查询
     */
    public SysUser queryByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(wrapper);
    }

    /**
     * 新增用户表
     */
    public boolean save(SysUser sysUser) {
        return sysUserMapper.insert(sysUser) > 0;
    }

    /**
     * 删除用户表
     */
    public void delete(String id) {
        sysUserMapper.deleteById(id);
    }

    public SysUser getById(String id) {
        return sysUserMapper.selectById(id);
    }
}
