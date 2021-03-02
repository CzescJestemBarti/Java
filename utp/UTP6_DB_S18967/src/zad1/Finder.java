/**
 * @author Dąbrowski Bartosz S18967
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {

    private String fname;
    private String fileContains;

    public Finder(String fname) throws IOException {
        this.fname = fname;
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContains += line;
            }
        }
    }


    public int getIfCount() {
        int countIf = 0;
        //String regex = "((\\{)(\\s)*)?" + "if(\\s)*\\(.\\)";
        String regex="([^e]\\\\s)(if)(\\\\s?\\\\((\\\\D[^\\\\)]+)\\\\))\n";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileContains);
        while (matcher.find()) countIf++;
        return countIf;
    }

    public int getStringCount(String wariant){
        int countWariant = 0;
        Pattern pattern = Pattern.compile(wariant);
        Matcher matcher = pattern.matcher(fileContains);
        while(matcher.find()) countWariant++;
        return countWariant;
    }
}

