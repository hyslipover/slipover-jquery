package org.slipover.frame.jquery.tool;

import com.alibaba.fastjson.JSON;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

@SuppressWarnings("unchecked")
public interface ObjectTool {

    /**
     * 对象无意义
     * 例：
     * 1) 对象为 null
     * 2) 对象为 String 类型，且长度为0
     * 3) 对象为 Array 类型，且长度为0
     * 4) 对象为 Collection 类型，且长度为0
     * 5) 对象为 Map 类型，且长度为0
     *
     * @param obj
     * @return
     */
    default boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            return ((String) obj).isEmpty();
        } else if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    /**
     * 对象有意义
     * @param obj
     * @return
     */
    default boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 是否相等
     * @param objs
     * @return
     */
    default boolean equals(Object ... objs) {
        if (objs == null || objs.length == 1) {
            return true;
        }
        Object obj = objs[0];
        for (int i = 1; i < objs.length; i++) {
            if (!Objects.equals(obj, objs[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否相等
     * @param objs
     * @return
     */
    default boolean deepEquals(Object ... objs) {
        if (objs == null || objs.length == 1) {
            return true;
        }
        Object obj = objs[0];
        for (int i = 1; i < objs.length; i++) {
            if (!Objects.deepEquals(obj, objs[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 遍历数组带下标
     * @param ts
     * @param biConsumer
     * @param <T>
     */
    default <T> void each(T[] ts, BiConsumer<Integer, T> biConsumer) {
        for (int i = 0; i < ts.length; i++) {
            biConsumer.accept(i, ts[i]);
        }
    }

    default <T> void each(List<T> list, BiConsumer<Integer, T> biConsumer) {
        for (int i = 0; i < Objects.requireNonNull(list).size(); i++) {
            biConsumer.accept(i, list.get(i));
        }
    }

    /**
     * 遍历对象属性
     *
     * @param obj
     * @param biConsumer
     */
    default void each(Object obj, BiConsumer<String, Object> biConsumer) {
        JSON.parseObject(JSON.toJSONString(Objects.requireNonNull(obj)), Map.class).forEach(biConsumer);
    }

}
