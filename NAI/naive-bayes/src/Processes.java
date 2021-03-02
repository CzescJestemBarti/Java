import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Processes {
    static List<Attribute> trainList, testList;
    static Map<String, Integer> countingAttributesMap = new HashMap<>();
    static Map<String, Integer> attributesMap = new HashMap<>();
    static Double allPosibilities;
    static String[] namesOfTable;
    static List<String> decisions = new ArrayList<>();

    Processes() {
        // jezeli bedzie wiecej tabel, nalezy uwzglednic prosbe o wpisanie ich tytulow, tak aby output byl sensowny
        namesOfTable = new String[]{"pogoda", "wiatr", "wilgotnosc", "temperatura", "decyzja"};
        trainList = readFromFile("trainingset.csv");
        testList = readFromFile("testset.csv");
        allPosibilities = (double) testList.size();

        System.out.println(trainList);

        // lista roznych mozliwych decyzji
        fillDecisions();

        // ilosc wystapien danych typow
        fillCountingMap();

        // mapa zliczajaca wystapienie wszystkich mozliwosci ile razy
        System.out.println(countingAttributesMap);

        // mapa sprawdzajaca dane warunki pod wzgledem tak/nie
        fillAttributesMap();
        System.out.println(attributesMap);


        System.out.println("***************************************************************************************************");
        System.out.println("*Wpisz TESTOWE, jezeli chcesz aby podac wyniki testowych plikow, badz WLASNE jezeli wlasny warunek*");
        System.out.println("***************************************************************************************************");
        Scanner scanner = new Scanner(System.in);

        String information = scanner.next().toLowerCase();

        if (information.equals("testowe"))
            Test();
        else if (information.equals("wlasne"))
            TestSpecified();

    }

    public List<Attribute> readFromFile(String path) {
        int ktory = 1;
        String line;
        String[] table;
        List<Attribute> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                table = line.split(",");
                result.add(new Attribute(ktory, table));
                ktory++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // zczytuje wszystkie mozliwe decyzje (np. tak, nie, moze...)
    public void fillDecisions(){
        for(Attribute attribute : trainList){
            if(!decisions.contains(attribute.values.get(attribute.values.size()-1))){
                decisions.add(attribute.values.get(attribute.values.size()-1));
            }
        }
    }

    public void fillCountingMap() {

        // pogoda, wiatr, wilgotnosc, temperatura, decyzja
        for (Attribute attribute : trainList) {
            List container = attribute.getValues(); //slonecznie,tak,srednia,wysoka,tak
            for (int i = 0; i < container.size(); i++) {
                if (!countingAttributesMap.containsKey(namesOfTable[i % container.size()] + " " + container.get(i)))

                    //dodaje do mapy wartosci gdzie klucz to reszta z dzielenia
                    countingAttributesMap.put(namesOfTable[i % container.size()] + " " + container.get(i), 1);
                else

                    //jezeli istnieje juz taki klucz to dodaje kolejne wystapienie
                    countingAttributesMap.computeIfPresent(namesOfTable[i % container.size()] + " " + container.get(i), (key, value) -> value + 1);
            }
        }
    }

    public void fillAttributesMap() {
        for (Attribute attribute : trainList) {
            List container = attribute.getValues();
            for (int i = 0; i < container.size() - 1; i++) {
                // deklaracja wystapien wszystkich mozliwych typow
                for(String s:decisions){
                    attributesMap.put("[decyzja="+s+"] "+namesOfTable[i%container.size()]+ " "+ container.get(i),0);
                }
            }
        }

        for (Attribute attribute : trainList) {
            List container = attribute.getValues();
            for (int i = 0; i < container.size() - 1; i++) {
                attributesMap.computeIfPresent("[decyzja=" + container.get(container.size() - 1) + "] " + namesOfTable[i % container.size()] + " " + container.get(i), (key, value) -> (value + 1));
            }
        }
    }

    // String pobierany to nazwa jednego z atrybutow ( np. pogoda slonecznie, wilgotnosc niska... )
    public double Laplace(String attribute, String decision) {

        //licznik (ilosc atrybunkow pod warunkiem ze @decyzja
        double nominator;
        if(attributesMap.get("[decyzja=" + decision + "] " + attribute)==null){
            nominator=0;
        }else{
            nominator = attributesMap.get("[decyzja=" + decision + "] " + attribute);
        }

        //mianownik (ilosc wszystkich @decyzja)
        double denominator = countingAttributesMap.get("decyzja " + decision);

        //ilosc roznych typow atrybutow tj. np wiatr-nie wiatr-tak, wilgotnosc-srednia/niska itp.
        double ammountOfTypes = countAllTypes(attribute);

        if (nominator == 0) {
            denominator += ammountOfTypes;
            nominator++;
            if(attributesMap.get("[decyzja=" + decision + "] " + attribute)==null)
                denominator ++; // +1 jako ze nowy typ sie pojawia ( przynajmniej tak mi sie wydaje )
        }
        return nominator / denominator;
    }

    private int countAllTypes(String attribute) {
        int result = 0;
        String[] container = attribute.split(" ");
        for (String key : countingAttributesMap.keySet())
            // sprawdzam dla np. pogoda ulewa, element pogoda
            if (key.contains(container[0]))
                result++;
        return result;
    }

    public void Test() {
        int ile = 0;
        for (Attribute attribute : testList) {
            List args = attribute.getValues(); // np. slonecznie, tak, niska, srednia, tak
            ile++;

            double P1 = 0, P2;
            String decision="";

            for (int i = 0; i < args.size(); i++) {
                for(String s:decisions){
                    //pierwsza pętla 1<P(wszystkiego|"tak"), kolejna P(wszystkiego|"tak")<P(wszystkiego|"nie")...
                    P2 = Laplace(namesOfTable[i] + " " + args.get(i),s) * countingAttributesMap.get("decyzja "+s)/allPosibilities;
                    if(P2>P1) {
                        P1 = P2;
                        decision = s;
                    }
                }
            }

            args.add(decision);
            attribute.setValues(args);

            System.out.println("Atrybut " + ile + " został zakwalifikowany jako [decyzja " + attribute.values.get(attribute.values.size() - 1) + "]");
        }
    }

    public void TestSpecified() {
        System.out.println("Podaj dane po przecinkach");
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        String[] output = scanner.nextLine().split(",");

        for(String s:output)
            list.add(s);

        double P1 = 0, P2;
        String decision="";

        for (int i = 0; i < list.size(); i++) {
            for(String s:decisions){
                //pierwsza pętla 1<P(wszystkiego|"tak"), kolejna P(wszystkiego|"tak")<P(wszystkiego|"nie")...
                P2 = Laplace(namesOfTable[i] + " " + list.get(i),s) * countingAttributesMap.get("decyzja "+s)/allPosibilities;
                if(P2>P1) {
                    P1 = P2;
                    decision = s;
                }
            }
        }

        list.add(decision);

        System.out.println("Atrybut został zakwalifikowany jako [decyzja " + list.get(list.size() - 1) + "]");

        System.out.println("Czy chcesz zakwalifikować kolejny atrybut? Napisz 0 jezeli nie, 1 jezeli tak");

        Scanner scanner1 = new Scanner(System.in);

        int choice = scanner1.nextInt();

        if(choice==0)
            System.exit(0);
        else if(choice==1)
            TestSpecified();
    }
}