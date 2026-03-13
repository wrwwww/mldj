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
    private String path;
    private String component;

    private String perms;
    private Integer menuType;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuTreeVO> children;

    // 前端需要的额外字段
    private String redirect;




    private Boolean noCache;
    private String link;
    private Meta meta;

    @Data
    @Builder
    public static class Meta {
        private Integer sort;
        private Boolean alwaysShow;
        private String title;
        private String titleI18nKey;
        private String icon;
        private Integer hideInMenu;
    }
}
