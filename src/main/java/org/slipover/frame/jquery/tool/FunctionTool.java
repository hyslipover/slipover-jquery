package org.slipover.frame.jquery.tool;

import org.slipover.frame.jquery.extend.chained.CatchChained;
import org.slipover.frame.jquery.extend.chained.ReturnCatchChained;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 封装代码块
 */
public interface FunctionTool {

    /**
     * 异步执行代码块
     * @param runnable
     */
    default void async(Runnable runnable){
        new Thread(runnable).start();
    }

    /**
     * 锁住代码块
     * @param key
     * @param consumer
     * @param <T>
     */
    default <T> void lock(final T key, Consumer<T> consumer){
        synchronized (Objects.requireNonNull(key)) {
            consumer.accept(key);
        }
    }

    /**
     * 将代码块异常抛出
     * @param runnable
     */
    default CatchChained tryCatch(Runnable runnable) {
        Exception exception = null;
        try {
            Objects.requireNonNull(runnable).run();
        } catch (Exception e) {
            exception = e;
        } finally {

        }
        return new CatchChained(exception);
    }

    /**
     * 将代码块异常抛出
     * @param supplier
     */
    default <R> ReturnCatchChained<R> tryCatch(Supplier<R> supplier) {
        Exception exception = null;
        R data = null;
        try {
            data = Objects.requireNonNull(supplier).get();
        } catch (Exception e) {
            exception = e;
        } finally {

        }
        return new ReturnCatchChained<>(data, exception);
    }

}
