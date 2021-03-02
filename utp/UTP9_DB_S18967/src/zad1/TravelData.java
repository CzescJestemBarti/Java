package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.*;

public class TravelData {
    private Scanner sc;
    private ArrayList<ArrayList<String>> ceiling = new ArrayList<ArrayList<String>>();
    private ArrayList<String> godl;
    private Locale myLocals;

    public TravelData(File dataDir) {
        try {
            File file = new File(dataDir + "\\dane");
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] container = line.split("\\t");

            ArrayList<String> basement = new ArrayList<>(Arrays.asList(container));

            ceiling.add(basement);
        }
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        godl = new ArrayList<>();
        Date date;
        String out = "";

        String[] loc = locale.split("_");
        if (loc.length == 2) {
            myLocals = new Locale(loc[0], loc[1]);
        } else if (loc.length == 1) {
            myLocals = new Locale(loc[0]);
        }
        SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getInstance();
        sdf.applyPattern(dateFormat);

        for (ArrayList<String> list : ceiling) {
            String[] helper = list.get(0).split("_");
            Locale innerloc;

            if (helper.length == 2) {
                innerloc = new Locale(helper[0], helper[1]);
            } else {
                innerloc = new Locale(helper[0]);
            }

            Locale.setDefault(innerloc);

            Locale[] countries = Locale.getAvailableLocales();
            Locale country = null;
            for (int i = 0; i < countries.length; i++) {
                if (countries[i].getDisplayCountry().equals(list.get(1))) {
                    country = countries[i];
                }
            }
            out += country.getDisplayCountry(myLocals);
            try {
                date = sdf.parse(list.get(2));
                out += " " + sdf.format(date);

                date = sdf.parse(list.get(3));
                out += " " + sdf.format(date);

                ResourceBundle rb = ResourceBundle.getBundle("zad1.MyResourceBundle", myLocals);
                out += " " + rb.getString(list.get(4));

                NumberFormat nf = NumberFormat.getInstance(innerloc);
                Number number = nf.parse(list.get(5));
                out += " " + nf.format(number);

                Currency c = Currency.getInstance(list.get(6));
                out += " " + c.getCurrencyCode();

                godl.add(out);
                out = "";

            } catch (ParseException e) {
                System.err.println("Exception in Formating");
                e.printStackTrace();
            }
        }
        return godl;
    }

}
