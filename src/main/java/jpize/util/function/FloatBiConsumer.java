package jpize.util.function;

import java.util.Objects;

public interface FloatBiConsumer {

    void accept(float a, float b);

    default FloatBiConsumer andThen(FloatBiConsumer after) {
        Objects.requireNonNull(after);
        return (a, b) -> {
            this.accept(a, b);
            after.accept(a, b);
        };
    }

}
