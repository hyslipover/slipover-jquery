package org.slipover.frame.jquery.tool;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 锁操作
 */
public interface LockTool {

    /**
     * 锁住代码块
     *
     * @param key
     * @param runnable
     */
    default void lock(final Object key, Runnable runnable) {
        synchronized (Objects.requireNonNull(key)) {
            runnable.run();
        }
    }

    /**
     * 锁住代码块
     *
     * @param key
     * @param supplier
     * @param <R>
     * @return
     */
    default <R> R lock(final Object key, Supplier<R> supplier) {
        synchronized (Objects.requireNonNull(key)) {
            return supplier.get();
        }
    }

    /**
     * 锁住代码块
     *
     * @param key
     * @param consumer
     * @param <T>
     */
    default <T> void lock(final T key, Consumer<T> consumer) {
        synchronized (Objects.requireNonNull(key)) {
            consumer.accept(key);
        }
    }

    /**
     * 锁住代码块
     *
     * @param key
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    default <T, R> R lock(final T key, Function<T, R> function) {
        synchronized (Objects.requireNonNull(key)) {
            return function.apply(key);
        }
    }

}
