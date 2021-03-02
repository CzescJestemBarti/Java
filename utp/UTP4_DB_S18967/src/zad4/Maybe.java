package zad4;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    private T value;

    Maybe(){}

    Maybe(T x){
        value=x;
    }

    public static <T> Maybe<T> of(T x) {
        return new Maybe(x);
    }

    public void ifPresent(Consumer<T> cons) {
        if (isPresent()){
            cons.accept(value);
        }
    }

    <V> Maybe<V> map(Function<T, V> func) {
        if (this.isPresent())
            return Maybe.of(func.apply(value));
        else
        return new Maybe<V>();
    }

    public T get() throws NoSuchElementException {
        if(this.isPresent())
            return value;
        else
        throw new NoSuchElementException(" " + toString().toLowerCase());
    }

    public boolean isPresent() {
        return value!=null;
    }

    public T orElse(T defVal) {
        if (this.isPresent()) {
            return value;
        } else {
            return defVal;
        }
    }

    public Maybe<T> filter(Predicate<T> pred) {
        if(pred.test(value) || !this.isPresent())
        return this;
        else
            return new Maybe<>();
    }

    public String toString() {
        if (isPresent()) {
            return "Maybe has value " + value.toString();
        } else {
            return "Maybe is empty";
        }
    }
}