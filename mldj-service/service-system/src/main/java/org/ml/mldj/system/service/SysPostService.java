package org.ml.mldj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.entity.SysPost;
import org.ml.mldj.system.mapper.SysPostMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class SysPostService extends ServiceImpl<SysPostMapper, SysPost> implements IService<SysPost> {

    public PageVO<SysPost> page(PageQuery<?> query) {
        Page<SysPost> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<SysPost> result = this.page(page);
        return PageVO.buildPageVO(result);
    }

    public boolean updateStatus(Long id, Integer status) {
        SysPost post = new SysPost();
        post.setId(id);
        post.setStatus(status);
        return this.updateById(post);
    }
}
