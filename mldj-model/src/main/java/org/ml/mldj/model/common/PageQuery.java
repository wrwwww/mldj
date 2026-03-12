package org.ml.mldj.model.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageQuery<T> extends PageRequest {

    private List<SortItem> sorts;

    private T filters;
    /**
     * 转换为MyBatis-Plus分页对象
     */
    public <E> Page<E> toPage() {
        return new Page<>(getSafePageNum(), getSafePageNum());
    }

    /**
     * 转换为MyBatis-Plus分页对象（支持是否优化count查询）
     */
    public <E> Page<E> toPage(boolean optimizeCountSql) {
        Page<E> page = new Page<>(getSafePageNum(), getSafePageNum());
        page.setOptimizeCountSql(optimizeCountSql);
        return page;
    }

    /**
     * 获取安全的页码
     */
    public long getSafePageNum() {
        return Math.max(1, getPageNum());
    }

    /**
     * 获取安全的页大小
     */
    public long getSafePageSize() {
        return Math.max(1, Math.min(100, getPageSize()));
    }

    /**
     * 是否包含排序条件
     */
    public boolean hasSort() {
        return sorts != null && !sorts.isEmpty();
    }


}
