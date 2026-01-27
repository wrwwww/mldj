package org.ml.mldj.model.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageQuery<T> extends PageRequest {

    private List<SortItem> sort;

    private T filters;

}
