package zad3;

import java.util.HashSet;
import java.util.Set;

public class Programmer {

        private String nazwisko;
        private Set jez = new HashSet<String>();

        public Programmer(String nazwisko, Set<String> jez) {
            this.nazwisko = nazwisko;
            this.jez = jez;
        }

        Set getJez(){
            return jez;
        }

        String getNazwisko(){
            return nazwisko;
        }
        Integer getJezSize(){
            return jez.size();
        }

    @Override
    public String toString() {
        return ""+jez;
    }
}
