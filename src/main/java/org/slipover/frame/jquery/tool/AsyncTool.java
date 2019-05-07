package org.slipover.frame.jquery.tool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步操作
 */
public interface AsyncTool {

    /**
     * 异步执行代码块
     *
     * @param runnable
     */
    default CompletableFuture<Void> async(Runnable runnable) {
        return CompletableFuture.runAsync(runnable);
    }

    /**
     * 异步执行代码块
     *
     * @param corePoolSize
     * @param maximumPoolSize
     * @param capacity
     * @param runnable
     * @see ThreadPoolExecutor
     */
    default CompletableFuture<Void> async(int corePoolSize, int maximumPoolSize, int capacity, Runnable runnable) {
        return async(runnable, new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(capacity)));
    }

    /**
     * 异步执行代码块
     *
     * @param runnable
     * @param executor
     */
    default CompletableFuture<Void> async(Runnable runnable, Executor executor) {
        return CompletableFuture.runAsync(runnable, executor);
    }

}
