import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Iris> giveKClosest(int k, List<Iris> list,
                                          double x, double y,
                                          double a, double b) {
        List<Iris> returnList = new ArrayList<>();

        //Irys dodawany
        Iris myIris = null;
        for (int i = 0; i < k; i++) {
            //Problem ze znalezieniem sensownego ustawienia wielkosci doubla
            double smallestdistance = 10000;
            //szukanie k najmniejszych
            for (Iris iris : list) {
                double distance = iris.getDistance(x, y, a, b);
                if (smallestdistance > distance) {
                    if (!returnList.contains(iris)) {
                        smallestdistance = distance;
                        myIris = iris;
                    }
                }
            }
            returnList.add(myIris);
        }
        return returnList;
    }

    public static List<String> getTrain_setPath() {
        List<String> resultList = new ArrayList<>();
        String line;

        System.out.println("Podaj destynacje pliku treningowego .csv");
        Scanner sc = new Scanner(System.in);
        String testPathstring = sc.next();
        Path testPath = Paths.get(testPathstring);

        while (Files.notExists(testPath)) {
            System.out.println("Podano zla sciezke, sprobuj ponownie.");
            testPathstring = sc.next();
            testPath = Paths.get(testPathstring);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(testPathstring))) {
            while ((line = bufferedReader.readLine()) != null) {
                resultList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }
    public static void receiveVectors(List<Iris> trainList){
        Scanner scanner = new Scanner(System.in);
        List<Iris> newIrisList;
        System.out.println("Podaj wartosci wektorów, po enterach:\n Wektor 1");
        double x,y,a,b;
        int k, ammountOfVirginica=0, ammountOfVersicolor=0, ammountOfSetosa=0;
        x=scanner.nextDouble();
        System.out.println("Wektor 2");
        y=scanner.nextDouble();
        System.out.println("Wektor 3");
        a=scanner.nextDouble();
        System.out.println("Wektor 4");
        b=scanner.nextDouble();
        System.out.println("Podaj parametr k-nn.");
        k=scanner.nextInt();

        newIrisList = giveKClosest(k,trainList,x,y,a,b);
        for (Iris iris : newIrisList) {
            switch (iris.toString()) {
                case "Iris-virginica":
                    ammountOfVirginica++;
                    break;
                case "Iris-versicolor":
                    ammountOfVersicolor++;
                    break;
                case "Iris-setosa":
                    ammountOfSetosa++;
                    break;
            }
        }
        if(ammountOfSetosa >= ammountOfVersicolor && ammountOfSetosa >=ammountOfVirginica){
            System.out.println("Jest to Setosa");
        }else if(ammountOfVirginica>ammountOfSetosa && ammountOfVirginica>=ammountOfVersicolor){
            System.out.println("Jest to Virginica");
        }else{
            System.out.println("Jest to Versicolor");
        }

        System.out.println("Czy chcesz podac kolejny wektor?");
        String string = scanner.next();
        if( string.equalsIgnoreCase("Tak") ){
            receiveVectors(trainList);
        }else{
            System.exit(0);
        }
    }

    public static List<String> getTest_setPath() {
        List<String> resultList = new ArrayList<>();
        String line;

        System.out.println("Podaj destynacje pliku testowego .csv");
        Scanner sc = new Scanner(System.in);
        String testPathstring = sc.next();
        Path testPath = Paths.get(testPathstring);

        while (Files.notExists(testPath)) {
            System.out.println("Podano zla sciezke, sprobuj ponownie.");
            testPathstring = sc.next();
            testPath = Paths.get(testPathstring);
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(testPathstring))) {
            while ((line = bufferedReader.readLine()) != null) {
                resultList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static void main(String[] args) {

        double countLines = 0, wrongLines = 0;

        List<Iris> irisList = new ArrayList<>();
        List<String> stringList = getTrain_setPath();

        for (String s : stringList) {
            String[] result = s.split(";");

            switch (result[4]) {
                case "Iris-setosa":
                    irisList.add(new Iris_setosa(Double.parseDouble(result[0]),
                            Double.parseDouble(result[1]),
                            Double.parseDouble(result[2]),
                            Double.parseDouble(result[3]),
                            result[4]));
                    break;
                case "Iris-versicolor":
                    irisList.add(new Iris_versicolor(Double.parseDouble(result[0]),
                            Double.parseDouble(result[1]),
                            Double.parseDouble(result[2]),
                            Double.parseDouble(result[3]),
                            result[4]));
                    break;
                case "Iris-virginica":
                    irisList.add(new Iris_virginica(Double.parseDouble(result[0]),
                            Double.parseDouble(result[1]),
                            Double.parseDouble(result[2]),
                            Double.parseDouble(result[3]),
                            result[4]));
                    break;
            }
            countLines++;
        }

        System.out.println("Czy chcesz sprawdzic wlasny wektor - wpisz 0" +
                ", czy moze z pliku testowego .csv? - wpisz 1");

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        while (x<0 || x>1) {
            System.out.println("Podano zly argument, sprobuj jeszcze raz");
            x = scanner.nextInt();
        }
        if(x==0) {
            receiveVectors(irisList);

        }else{
            System.out.println("Podaj parametr k, z przedziału (0,105] dla liczb calkowitych.");
            Scanner sc = new Scanner(System.in);
            int k = sc.nextInt();
            while (k > 105 || k < 1) {
                System.out.println("Podano zly argument, sprobuj ponowanie.");
                k = sc.nextInt();
            }

            List<Iris> testIrisList;
            List<String> testList = getTest_setPath();

            for (String s : testList) {
                int ammountOfVirginica = 0, ammountOfVersicolor = 0, ammountOfSetosa = 0;
                String[] res = s.split(";");

                testIrisList = giveKClosest(k, irisList,
                        Double.parseDouble(res[0]),
                        Double.parseDouble(res[1]),
                        Double.parseDouble(res[2]),
                        Double.parseDouble(res[3]));

                for (Iris iris : testIrisList) {
                    switch (iris.toString()) {
                        case "Iris-virginica":
                            ammountOfVirginica++;
                            break;
                        case "Iris-versicolor":
                            ammountOfVersicolor++;
                            break;
                        case "Iris-setosa":
                            ammountOfSetosa++;
                            break;
                    }
                }
                // Teraz czas na ifologie sprawdzajaca skutecznosc
                // naszego programu do wynikow z test-set

                if (res[4].equals("Iris-virginica")) {
                    if (!(ammountOfVirginica >= ammountOfSetosa) ||
                            !(ammountOfVirginica >= ammountOfVersicolor))
                        wrongLines++;
                } else if (res[4].equals("Iris-versicolor")) {
                    if (!(ammountOfVersicolor >= ammountOfSetosa) ||
                            !(ammountOfVersicolor >= ammountOfVirginica))
                        wrongLines++;
                } else {
                    if (!(ammountOfSetosa >= ammountOfVirginica) ||
                            !(ammountOfSetosa >= ammountOfVersicolor))
                        wrongLines++;
                }
                countLines++;
            }
            System.out.println("Dokladnosc testu to "
                    + 100 * (countLines - wrongLines) / countLines + "%.");
        }
    }
}