package org.ml.mldj.common.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class MyBeanUtils {
    public static <S, T> List<T> copyList(Collection<S> source, Class<T> target) {

        return source.stream().map(e -> {
            try {
                T t = target.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(e, t);
                return t;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
        }).toList();
    }

    public static <S, T> List<T> copyList(Collection<S> source, Supplier<T> target) {

        return source.stream().map(e -> {

            T t = target.get();
            BeanUtils.copyProperties(e, t);
            return t;

        }).toList();
    }
}
