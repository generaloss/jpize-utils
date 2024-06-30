package jpize.util.function;

import java.util.Objects;

@FunctionalInterface
public interface FloatConsumer {

    void accept(float v);

    default FloatConsumer andThen(FloatConsumer after) {
        Objects.requireNonNull(after);
        return t -> {
            this.accept(t);
            after.accept(t);
        };
    }

}