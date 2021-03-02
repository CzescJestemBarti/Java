import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ArrayList<Iris> trainList, testList;
    private static Perceptron perceptron;

    public static void main(String[] args) {
        //czytanie z pliku i trening
        readTrain();
        double a;

        System.out.println("Wprowadzam wlasne wektory -> napisz" +
                " tak. Chce, aby zostaly wykonane dla pliku -> napisz nie");

        Scanner sc = new Scanner(System.in);

        if (sc.next().equalsIgnoreCase("tak")) {
            a = PleaseInsertA();
            perceptron = new Perceptron(trainList, a);
            System.out.println("Prosze podac wartosci po przecinkach");

            Scanner scanner = new Scanner(System.in);
            String[] tab = scanner.nextLine().split(",");
            double[] parsedTab= new double[tab.length];

            for(int i=0; i<tab.length; i++)
                parsedTab[i]=Double.parseDouble(tab[i]);

            //upewnianie sie ze dziala dla zadanej liczby atrybutow, reszte wag uzupelniam zerami
            double[] tmpTab = new double[parsedTab.length];
            if(parsedTab.length>perceptron.wagi.length){
                for(int i=0; i<parsedTab.length; i++){
                    if(i<perceptron.wagi.length){
                        tmpTab[i]=perceptron.wagi[i];
                    }else{
                        tmpTab[i]=0;
                    }
                }
            }
            double wynik= Calculate.obliczNetDlaWektor(tmpTab, parsedTab);
                if (wynik>=perceptron.theta)
                    System.out.println("Iris-setosa");
                else
                    System.out.println("Iris-virginica");
        } else {
            readTest();
            a = PleaseInsertA();

            perceptron = new Perceptron(trainList, testList, a);
            perceptron.Learn();
            perceptron.Test();
        }
    }

    private static double PleaseInsertA(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Prosze podac parametr a, z przedzialu (0,1) ");
        double a = sc.nextFloat();
        while(a<0 || a>=1){
            System.out.print("Prosze podac parametr a, z przedzialu (0,1) ");
            sc = new Scanner(System.in);
            a = sc.nextFloat();
        }
        return a;
    }

    private static void readTest() {
        String line;
        System.out.print("Proszę podać ścieżkę do danych testowych: ");
        Scanner sc = new Scanner(System.in);
        String testFile = sc.nextLine();

        if (!Files.exists(Paths.get(testFile))) {
            readTest();

        } else {
            testList = new ArrayList<>();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(testFile))) {
                while ((line = bufferedReader.readLine()) != null) {
                    String[] lineArgs = line.split(";");
                    if (lineArgs[4].equals("Iris-setosa")) {
                        testList.add(new Iris_setosa(Double.parseDouble(lineArgs[0]),
                                Double.parseDouble(lineArgs[1]),
                                Double.parseDouble(lineArgs[2]),
                                Double.parseDouble(lineArgs[3]),
                                lineArgs[4]));
                    } else if(lineArgs[4].equals("Iris-virginica")){
                        testList.add(new Iris_virginica(Double.parseDouble(lineArgs[0]),
                                Double.parseDouble(lineArgs[1]),
                                Double.parseDouble(lineArgs[2]),
                                Double.parseDouble(lineArgs[3]),
                                lineArgs[4]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readTrain() {
        System.out.print("Proszę podać ścieżkę do danych treningowych: ");
        Scanner sc = new Scanner(System.in);
        String trainingFile = sc.nextLine();

        if (Files.notExists(Paths.get(trainingFile))) {
            readTrain();
        } else {
            trainList = new ArrayList<>();

            try {
                List<String> lines = Files.readAllLines(Paths.get(trainingFile));

                for (String s : lines) {
                    String[] line = s.split(";");
                    switch (line[4]) {
                        case "Iris-setosa":
                            trainList.add(new Iris_setosa(Double.parseDouble(line[0]),
                                    Double.parseDouble(line[1]),
                                    Double.parseDouble(line[2]),
                                    Double.parseDouble(line[3]),
                                    line[4]));
                            break;
                        case "Iris-virginica":
                            trainList.add(new Iris_virginica(Double.parseDouble(line[0]),
                                    Double.parseDouble(line[1]),
                                    Double.parseDouble(line[2]),
                                    Double.parseDouble(line[3]),
                                    line[4]));
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}