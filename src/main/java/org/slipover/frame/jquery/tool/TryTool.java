package org.slipover.frame.jquery.tool;

import org.slipover.frame.jquery.extend.chained.CatchChained;
import org.slipover.frame.jquery.extend.chained.ReturnCatchChained;
import org.slipover.frame.share.function.ThrowableRunnable;
import org.slipover.frame.share.function.ThrowableSupplier;

import java.util.Objects;

/**
 * 捕获 Throwable
 */
public interface TryTool {

    /**
     * 将代码块异常抛出
     *
     * @param runnable
     */
    default CatchChained tryCatch(ThrowableRunnable runnable) {
        return tryCatch(runnable, () -> { });
    }

    /**
     * 将代码块异常抛出
     *
     * @param runnable
     */
    default CatchChained tryCatch(ThrowableRunnable runnable, Runnable finallyRunnable) {
        Throwable throwable = null;
        try {
            Objects.requireNonNull(runnable).run();
        } catch (Throwable e) {
            throwable = e;
        } finally {
            finallyRunnable.run();
        }
        return new CatchChained(throwable);
    }

    /**
     * 将代码块异常抛出
     *
     * @param supplier
     */
    default <R> ReturnCatchChained<R> tryCatch(ThrowableSupplier<R> supplier) {
        return tryCatch(supplier, () -> { });
    }

    /**
     * 将代码块异常抛出
     *
     * @param supplier
     */
    default <R> ReturnCatchChained<R> tryCatch(ThrowableSupplier<R> supplier, Runnable finallyRunnable) {
        Throwable throwable = null;
        R data = null;
        try {
            data = Objects.requireNonNull(supplier).get();
        } catch (Throwable e) {
            throwable = e;
        } finally {
            Objects.requireNonNull(finallyRunnable).run();
        }
        return new ReturnCatchChained<>(data, throwable);
    }

}
