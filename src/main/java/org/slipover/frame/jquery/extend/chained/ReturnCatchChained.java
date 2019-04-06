package org.slipover.frame.jquery.extend.chained;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("all")
public class ReturnCatchChained<R> {

    private R data;

    private Throwable throwable = null;

    public ReturnCatchChained(R data, Throwable throwable) {
        this.data = data;
        this.throwable = throwable;
    }

    public R success() {
        return data;
    }

    public R success(R other) {
        return data != null ? data : other;
    }

    public ReturnCatchChained orCatch(Consumer<Throwable> consumer) {
        if (Objects.nonNull(throwable)) {
            consumer.accept(throwable);
        }
        return this;
    }

    public ReturnCatchChained orCatch(Function<Throwable, R> function) {
        if (Objects.nonNull(throwable)) {
            data = function.apply(throwable);
        }
        return this;
    }

    public <T extends Throwable> ReturnCatchChained orCatch(Class<T> target, Consumer<T> consumer) {
        if (Objects.nonNull(throwable) && throwable.getClass().equals(target)) {
            consumer.accept((T) throwable);
        }
        return this;
    }

    public <T extends Throwable> ReturnCatchChained orCatch(Class<T> target, Function<T, R> function) {
        if (Objects.nonNull(throwable) && throwable.getClass().equals(target)) {
            data = function.apply((T) throwable);
        }
        return this;
    }

    public ReturnCatchChained andFinally(Runnable runnable){
        runnable.run();
        return this;
    }

}
