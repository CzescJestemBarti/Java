import java.util.ArrayList;
import java.util.List;

public class Attribute {
    List<String> values;
    int which;

    public Attribute(int which, String... strs) {
        values = arrayToList(strs);
        this.which=which;
    }

    //metoda Arrays.toList() sprawiala problemy, wiec stworzylem wlasna metode tworzaca liste
    public List<String> arrayToList(String[] array){
        List<String> result = new ArrayList<>();
        for(String string : array){
            result.add(string);
        }
        return result;
    }

    //czysto do outputu
    public String ValuesToString() {
        String result = "";
        for(int i=0; i<values.size(); i++){
            if(i!=values.size()-1)
                result+= values.get(i)+ ", ";
            else
                result+= values.get(i);
        }
        return result;
    }

    //getter
    public List getValues(){
        return values;
    }

    //setter
    public void setValues(List list){
        values = list;
    }

    @Override
    public String toString() {
        return "Attribute " + which + ": {" + ValuesToString() + "}";
    }
}
