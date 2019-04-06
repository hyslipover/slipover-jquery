package org.slipover.frame.jquery.extend.chained;

import java.util.Objects;
import java.util.function.Consumer;

@SuppressWarnings("all")
public class CatchChained {

    private Throwable throwable = null;

    public CatchChained(Throwable throwable) {
        this.throwable = throwable;
    }

    public CatchChained orCatch(Consumer<Throwable> consumer) {
        if (Objects.nonNull(throwable)) {
            consumer.accept(throwable);
        }
        return this;
    }

    public <T extends Throwable> CatchChained orCatch(Class<T> target, Consumer<T> consumer) {
        if (Objects.nonNull(throwable) && throwable.getClass().equals(target)) {
            consumer.accept((T) throwable);
        }
        return this;
    }

    public void andFinally(Runnable runnable){
        runnable.run();
    }

}
