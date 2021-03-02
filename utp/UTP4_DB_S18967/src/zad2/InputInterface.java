package zad2;

import java.io.IOException;
import java.util.function.Function;

public interface InputInterface<T,V> extends Function<T,V> {
    default V apply(T t){return null;}

    V applyIf(T t) throws IOException;
}
