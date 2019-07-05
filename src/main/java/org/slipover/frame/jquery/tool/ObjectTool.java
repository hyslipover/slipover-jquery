package org.slipover.frame.jquery.tool;

import com.alibaba.fastjson.JSON;
import org.slipover.frame.jquery.extend.$Optional;
import org.springframework.objenesis.ObjenesisBase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
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
     * 所有对象无意义
     * @see ObjectTool#isEmpty(java.lang.Object)
     * @param objs
     * @return
     */
    default boolean isAllEmpty(Object... objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }
        for (Object obj : objs) {
            if (!isEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 所有对象有意义
     * @see ObjectTool#isEmpty(java.lang.Object)
     * @param objs
     * @return
     */
    default boolean nonEmpty(Object... objs) {
        if (objs == null || objs.length == 0) {
            return false;
        }
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否都是空
     * @param objs
     * @return
     */
    default boolean isAllNull(Object... objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }
        for (Object obj : objs) {
            if (obj != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否都不为空
     * @param objs
     * @return
     */
    default boolean nonNull(Object... objs) {
        if (objs == null || objs.length == 0) {
            return false;
        }
        for (Object obj : objs) {
            if (obj == null) {
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

    /**
     * 将对象转成json
     * @param obj
     * @return
     */
    default String toJSONString(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 返回序列化结果
     * @return
     */
    default String toSerializable(Serializable serializable) {
        if (serializable == null) {
            return null;
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream)) {
            objectOutput.writeObject(serializable);
            return outputStream.toString(StandardCharsets.ISO_8859_1);
        } catch (Exception ignored) { }
        return serializable.toString();
    }

    /**
     * 将字符串转化成对象
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    default <T> T toObject(String str, Class<T> clazz) {
        if (str == null || clazz == null) {
            return null;
        }
        if (JSON.isValid(str)) {
            return JSON.parseObject(str, clazz);
        }
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
             ObjectInputStream objectInput = new ObjectInputStream(inputStream)) {
            Object obj = objectInput.readObject();
            if (obj != null) {
                if (obj.getClass().equals(clazz)) {
                    return (T) obj;
                }
                return JSON.parseObject(toJSONString(obj), clazz);
            }
        } catch (Exception ignored) { }
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception ignored) { }
        return null;
    }

}
