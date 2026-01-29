package org.ml.mldj.model.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuTreeVO {

    private Long id;
    private Long parentId;
    private String name;
    private String path;
    private String component;
    private String icon;
    private Integer sort;
    private Integer visible;
    private String perms;
    private Integer menuType;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuTreeVO> children;

    // 前端需要的额外字段
    private String redirect;
    private Boolean hidden;
    private Boolean alwaysShow;

    private String title;
    private Boolean noCache;
    private String link;
}
