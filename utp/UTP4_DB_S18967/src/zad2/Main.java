

/**
 *
 *  @author Dąbrowski Bartosz S18967
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<-- niezbędne import */
public class Main {

  public static void main(String[] args) throws IOException {

      InputInterface<String,List<String>> flines = o -> {
          List<String> list = new ArrayList<>();
          String line;
          BufferedReader br = new BufferedReader(new FileReader((o)));
          while ((line = br.readLine()) != null) {
                  list.add(line);
              }
          return list;
      };

      Function<List<String>, String> join = o -> {
          String str = "";
          for (int i = 0; i < o.size(); i++) {
              str += o.get(i);
          }
          return str;
      };
      Function<String, List<Integer>> collectInts = o -> {
          List<Integer> ilist = new ArrayList<>();
          Pattern p = Pattern.compile("-?\\d+");
          Matcher m = p.matcher(o);
          while (m.find()) {
              ilist.add(Integer.parseInt(m.group()));
          }
          return ilist;
      };


      Function<List<Integer>, Integer> sum = o -> {
          Integer container = 0;
          for (Integer integer : o)
              container += integer;

          return container;
      };


      String fname = System.getProperty("user.home") + "/LamComFile.txt";
      InputConverter<String> fileConv = new InputConverter<>(fname);
      List<String> lines = fileConv.convertBy(flines);
      String text = fileConv.convertBy(flines, join);
      List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
      Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

      System.out.println(lines);
      System.out.println(text);
      System.out.println(ints);
      System.out.println(sumints);

      List<String> arglist = Arrays.asList(args);
      InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
      sumints = slistConv.convertBy(join, collectInts, sum);
      System.out.println(sumints);

  }
}