/**
 *
 *  @author Dąbrowski Bartosz S18967
 *
 */

package zad2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream().filter((a)->{
      return a.startsWith("WAW");

    }).map((b)->{
      String cost = b.substring(8);
      return "to " + b.substring(4,7)
              + " - price in PLN:\t" + (int)(Double.parseDouble(cost)*ratePLNvsEUR);
    }).collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}