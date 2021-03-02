import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Działanie testowe - wpisz 0. Podaj wlasna sciezke i k - wpisz 1");

        Scanner scanner = new Scanner(System.in);
        if (scanner.nextInt() == 0) {

            new SeriesOfPoints("dane.csv", 3);

        } else {

            Scanner sc = new Scanner(System.in);

            System.out.println("Podaj ścieżke");
            String path = sc.next();
            while (!Files.exists(Paths.get(path))) {
                System.out.println("Błąd. Podaj poprawną ścieżke");
                path = sc.next();
            }


            System.out.println("Podaj wartosc elementu k");
            int k = sc.nextInt();

            new SeriesOfPoints(path, k);
        }
    }
}