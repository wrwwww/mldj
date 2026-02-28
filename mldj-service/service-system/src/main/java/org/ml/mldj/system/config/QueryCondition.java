package org.ml.mldj.system.config;

import java.lang.annotation.*;

/**
 * 动态查询条件注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryCondition {
    /**
     * 查询方式
     */
    Type type() default Type.EQ;
    /**
     * 查询方式枚举
     */
    enum Type {
        EQ,         // 等于
        NE,         // 不等于
        LIKE,       // 模糊查询
        LIKE_LEFT,  // 左模糊
        LIKE_RIGHT, // 右模糊
        GT,         // 大于
        GE,         // 大于等于
        LT,         // 小于
        LE,         // 小于等于
        BETWEEN,    // 区间
        IN,         // IN查询
        NOT_IN,     // NOT IN
        IS_NULL,    // IS NULL
        IS_NOT_NULL // IS NOT NULL
    }
}
