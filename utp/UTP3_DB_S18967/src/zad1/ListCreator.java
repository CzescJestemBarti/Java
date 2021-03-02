package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> {

    public List<T> list;

    public static <T> ListCreator collectFrom(List<T> list) {
        ListCreator<T> listCreator = new ListCreator<>();
        listCreator.list = new ArrayList<>();
        listCreator.list.addAll(list);
        return listCreator;
    }

    public ListCreator<T> when(Predicate<T> pr) {
        for(int i=0; i<list.size(); i++){
            if(!pr.test(list.get(i))){
                list.remove(i);
                i--;
            }
        }
        return this;
    }

    public List<T> mapEvery(Function<T,T> func) {
        for(int i=0; i<list.size(); i++){
            list.set(i,func.apply(list.get(i)));
        }
        return list;
    }


}
