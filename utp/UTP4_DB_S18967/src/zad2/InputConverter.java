package zad2;

import java.io.IOException;
import java.util.function.Function;

public class InputConverter<T> {

    private T fname;

    InputConverter(T fname){
        this.fname=fname;
    }

    public <V> V convertBy(Function... methods) throws IOException {
        Object container = fname;
        Object res=null;
        for(Function f: methods){
            if(f instanceof InputInterface){
                res = ((InputInterface) f).applyIf(container);
                container = res;
            }else{
                res = f.apply(container);
                container = res;
            }
        }
        return (V) res;


    }

}
