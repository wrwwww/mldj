package org.ml.mldj.model.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class PageVO<T> {
    private long total;
    private long pageSize;
    private long pageNum;
    private List<T> list;

    public static <T> PageVO<T> buildPageVO(IPage<T> page) {
        PageVO<T> vo = new PageVO<>();
        vo.setTotal(page.getTotal());
        vo.setPageNum(page.getCurrent());
        vo.setPageSize(page.getSize());
        vo.setList(page.getRecords());
        return vo;
    }
}
