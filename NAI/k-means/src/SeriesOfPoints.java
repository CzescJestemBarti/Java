import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Zbiór punktów
class SeriesOfPoints {
    private int k, count=Integer.MAX_VALUE, E;
    private List<Point> allList;
    private HashMap<Integer, List<Point>> CollectionMap;
    private Point[] centroids;

    SeriesOfPoints(String path, int k) {
        this.k = k;
        allList = createList(path);

        //ogólny zbiór punktów pogrupowany w podzbiory
        CollectionMap = randomAllocatePoints();
        System.out.println("Base: " + CollectionMap); //sout bazowego pliku

        //zbiór centroidów
        centroids = createCentroids();
        System.out.println(centroids[0] + " " + centroids[1] + " " + centroids[2]); //sout centroidow dla samego siebie

        int ile = 0;
        //zmiana punktów
        while (count != 0) {
            ile++;
            count = reallocatePoints();

            for (int i = 0; i < k; i++) {
                E += Calculations.CalculateE(CollectionMap, i, centroids);
            }
            System.out.println("Iteracja " + ile + "\n  E: " + E);
            E = 0;
            centroids = createCentroids();
            //System.out.println(Arrays.toString(centroids));
            System.out.println(CollectionMap); //sout zmiany
        }
    }

    //zczytywanie z pliku plus od razu pakowanie wartosci do list
    private List<Point> createList(String path) {
        List<Point> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int pointNr = 1;

            while ((line = br.readLine()) != null) {
                //linia dzielona po znakach ;
                String[] stringValues = line.split(";");
                //"parsowanie" ze stringow na double
                double[] values = new double[stringValues.length];
                for (int i = 0; i < stringValues.length; i++) {
                    values[i] = Double.parseDouble(stringValues[i]);
                }
                //dodawanie do list punktow nowy obiekt
                result.add(new Point(pointNr, values));
                pointNr++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private HashMap<Integer, List<Point>> randomAllocatePoints() {
        //lista zbiorow {{A,B,C},{D,E,F},{G,H,I}}
        HashMap<Integer, List<Point>> result = new HashMap<>();

        // tworzenie k klastrów (objęte zbiorem głównym)
        for (int i = 0; i < k; i++) {
            List<Point> emptyList = new ArrayList<>();
            result.put(i, emptyList);
        }

        // po przydzieleniu dla kazdego z k punktow odpowiedniego zbioru, losowo przydzielam punkty
        // do kazdej z nich
        for (Point point : allList) {
            int var = (int) (Math.random() * k); // np. {0,1,2} dla k=3
            result.get(var).add(point);
        }
        return result;
    }

    //tworzenie tablicy Centroidow
    private Point[] createCentroids() {
        Point[] result = new Point[CollectionMap.size()];

        for (int i = 0; i < CollectionMap.size(); i++){
            //System.out.println(points.get(i));
            result[i] = new Point(Calculations.settingMessure(CollectionMap.get(i)));
        }
        return result;
    }

    //zmiana miejscami punktow oraz zwrocenie ilosci bledow, do dalszych obliczen
    private int reallocatePoints() {
        int number, helper = 0;
        count = 0;

        for (Point point : allList) {                            // k=3,
            number = Calculations.findClosest(point, centroids); //znajduje najblizszy centroid dla punkta (jego index)
            //P1 do Centroida 0
                    //0 - > P1
            if ( !CollectionMap.get(number).contains(point)
                    && CollectionMap.get(number).size()!=1 ) { //zamieniamy zbiory punktow

                //szukam klucza
                for (int z = 0; z < k; z++) {
                    if (CollectionMap.get(z).contains(point)) {
                        helper = z;
                    }
                }

                CollectionMap.get(helper).remove(point);
                CollectionMap.get(number).add(point);
                count++;
            }
        }
        return count;
    }
}