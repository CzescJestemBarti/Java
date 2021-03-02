/**
 * @author Dąbrowski Bartosz S18967
 */

package zad1;


import java.util.*;

public class Main {

    static List<String> getPricesInPLN(List<String> destinations, double xrate) {
        return ListCreator.collectFrom(destinations)
                .when(a -> {
                            if (a.toString().startsWith("WAW")) {
                                return true;
                            }
                            return false;

                        }
                )
                .mapEvery( b -> {
                          String cost = b.toString().substring(8);
                          return "to " + b.toString().substring(4,7)
                                  + " - price in PLN:\t" + (int)(Double.parseDouble(cost)*xrate);
                        }
                );
    }

    public static void main(String[] args) {
        // Lista destynacji: port_wylotu port_przylotu cena_EUR
        List<String> dest = Arrays.asList(
                "bleble bleble 2000",
                "WAW HAV 1200",
                "xxx yyy 789",
                "WAW DPS 2000",
                "WAW HKT 1000"
        );
        double ratePLNvsEUR = 4.30;
        List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
        for (String r : result) System.out.println(r);
    }
}
