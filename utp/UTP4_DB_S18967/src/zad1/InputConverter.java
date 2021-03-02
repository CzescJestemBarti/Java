package zad1;

import java.util.function.Function;

public class InputConverter<T> {

    private T s;

    InputConverter(T s) {
        this.s =s;
    }

    public <V> V convertBy(Function... methods) {
        if (methods.length == 1) {
            return (V) methods[0].apply(s);
        }if(methods.length==2){
            return (V) methods[1].apply(methods[0].apply(s)); // tu powinien byc String
        }if(methods.length==3){
            return (V) methods[2].apply(methods[1].apply(methods[0].apply(s))); // tu List<Integer>
        }if(methods.length==4){
            return (V) methods[3].apply(methods[2].apply(methods[1].apply(methods[0].apply(s)))); // a tu Integer
        }
        return null;
    }
}
