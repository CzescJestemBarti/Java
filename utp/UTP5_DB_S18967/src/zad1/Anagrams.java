/**
 *
 *  @author Dąbrowski Bartosz S18967
 *
 */

package zad1;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anagrams {

    private List<String> stringList;
    private List<List> list;

    Anagrams(String s) {
        stringList = new ArrayList<>();
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(new File((s))));
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                stringList.addAll(Arrays.asList(words));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<List> getSortedByAnQty() {

        List<String> innerStringList = new ArrayList<>();
        list = new ArrayList<>();

        for (int i = 0; i < stringList.size(); i++) {

            if (!innerStringList.contains(stringList.get(i))) {
                List<String> tmp = new ArrayList<>();

                for (String s : stringList) {
                    if (mySort(stringList.get(i), s)) {

                        innerStringList.add(s);
                        tmp.add(s);
                    }
                }

                list.add(tmp);
            }

        }

        list.sort((element1, element2) -> element2.size()- element1.size());
        return list;


    }
    String getAnagramsFor(String next) {
        for (List list : list) {
            List<String> tmp = new ArrayList<String>(list);

            for (int j = 0; j < tmp.size(); j++) {
                if (tmp.get(j).equals(next)) {
                    tmp.remove(j);
                    return next + ": " + tmp;
                }

            }
        }
        return "";
    }

    boolean mySort (String one, String two){
        char [] oneContainer = one.toCharArray();
        char [] twoContainer = two.toCharArray();
        Arrays.sort(oneContainer); Arrays.sort(twoContainer);
        return Arrays.equals(oneContainer, twoContainer);
    }


}