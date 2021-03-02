
/**
 *
 *  @author Weikert Robert S17092
 *
 */

        package zad2;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.Comparator;
        import java.util.List;


public class CustomersPurchaseSortFind {

    private List<Purchase> lista = null;

    public void readFile(String fname) {
        lista = new ArrayList<>();
        String line = null;
        BufferedReader br;
        try {
            br=new BufferedReader(new FileReader(new File(fname)));
            while ((line = br.readLine())!=null){
                String[] helper = line.split(";| ");
                Purchase klient = new Purchase(helper[0], helper[1], helper[2], helper[3],
                        Double.parseDouble(helper[4]), Double.parseDouble(helper[5]),
                        Double.parseDouble(helper[4]) * Double.parseDouble(helper[5]));

                lista.add(klient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showSortedBy(String type) {
        Comparator<Purchase> koszt = Comparator.comparing(Purchase::getKoszt).reversed().thenComparing(Purchase::getId_klienta);
        Comparator<Purchase> nazwisko = Comparator.comparing(Purchase::getNazwisko).thenComparing(Purchase::getId_klienta);
        if(type.equals("Nazwiska")){
            System.out.println(type);
            lista.stream().sorted(nazwisko).forEach(System.out::println);
        }
        if(type.equals("Koszty")){
            System.out.println(type);
            lista.stream().sorted(koszt).forEach(o -> System.out.println(o+" (koszt: " + o.getKoszt() + ")"));
        }

        System.out.println();
    }

    void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        lista.stream().filter(o->id.equals(o.getId_klienta())).forEach(System.out::println);
        System.out.println();
    }
}