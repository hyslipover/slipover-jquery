package org.slipover.frame.jquery;

import org.slipover.frame.jquery.extend.$Optional;
import org.slipover.frame.jquery.core.SpringContainer;
import org.slipover.frame.jquery.tool.AsyncTool;
import org.slipover.frame.jquery.tool.EncryptionTool;
import org.slipover.frame.jquery.tool.TryTool;
import org.slipover.frame.jquery.tool.HttpTool;
import org.slipover.frame.jquery.tool.LockTool;
import org.slipover.frame.jquery.tool.ObjectTool;
import org.slipover.frame.jquery.tool.RandomTool;
import org.slipover.frame.jquery.tool.ThrowTool;

/**
 * 模仿 js 的 jquery 对 java 的 bean 进行操作
 *
 * @author slipover
 * @version 1.0
 */
public final class JQuery implements ObjectTool, RandomTool, HttpTool, ThrowTool, EncryptionTool, TryTool, LockTool, AsyncTool {

    public final static JQuery $ = new JQuery();

    /**
     * 根据 java.lang.Class 获取实例化对象
     *
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> $Optional<T> $(Class<T> requiredType) {
        return $Optional.ofNullable(SpringContainer.getBean(requiredType));
    }

    /**
     * 根据 name 查找实例化对象
     *
     * @param name
     * @return
     */
    public static $Optional<Object> $(String name) {
        return $Optional.ofNullable(SpringContainer.getBean(name));
    }

    /**
     * 根据 name 查找实例化对象
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> $Optional<T> $(String name, Class<T> requiredType) {
        return $Optional.ofNullable(SpringContainer.getBean(name, requiredType));
    }

    /**
     * 包装对象
     * @param t
     * @param <T>
     * @return
     */
    public static <T> $Optional<T> $(T t){
        return $Optional.ofNullable(t);
    }

}
