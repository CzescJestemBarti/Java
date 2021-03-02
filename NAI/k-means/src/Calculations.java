import java.util.*;

public interface Calculations {

    //Euklides z uwzględnieniem nierównych przestrzeni geometrycznych.
    public static double getEuklidesDistance(Point p1, Point p2) {
        double distance = 0;

        //sprawdzasz odleglosc uwzgledniajac przestrzenie, jezeli sie nie zgadzaja tj. drugiej jest wieksza niz pierwszej
        //jak ponizej, to dystans bedziemy liczyc tylko dla dlugosci p1.dimensions(czyli nie chcemy potem odejmowac nulli od intow)
        if (p1.dimensions != p2.dimensions) {
            if (p1.dimensions < p2.dimensions) {
                for (int i = 0; i < p1.dimensions; i++) {
                    distance += Math.pow(p1.values[i] - p2.values[i], 2);
                }
            } else {
                for (int i = 0; i < p2.dimensions; i++) {
                    distance += Math.pow(p1.values[i] - p2.values[i], 2);
                }
            }
        } else {
            for (int i = 0; i < p1.dimensions; i++) {
                distance += Math.pow(p1.values[i] - p2.values[i], 2);
            }
        }
        return Math.sqrt(distance);
    }


    //wyznaczanie centroida
    static double[] settingMessure(List<Point> point) {

        int maxDim = 0; //zmienna ktora bedzie mowila o najwiekszej ilosci przestrzeni

        //szukam najwieksza ilosc przestrzeni sposrod listy
        for (Point point1 : point) {
            if (maxDim < point1.dimensions) //dimension = przestrzen
                maxDim = point1.dimensions;
        }

        //tworzenie tablicy dla centroida
        double[] centroid = new double[maxDim]; //tworze centroida ktory uwzgledni wszystkie przestrzenie

        //dla wszystkich punktow w liscie
        for (Point point2 : point) {
            //po wszystkich wartosciach
            for (int j = 0; j < point2.dimensions; j++)
                centroid[j] += point2.values[j];
        }

        //dzielenie przez wszystkie elementy listy
        for (int i = 0; i < centroid.length; i++) {
            centroid[i] /= point.size();
        }
        return centroid;
    }

    public static int findClosest(Point point, Point[] centroids) {
        double distance = Double.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < centroids.length; i++) {
            if (Calculations.getEuklidesDistance(point, centroids[i]) < distance) {
                distance = Calculations.getEuklidesDistance(point, centroids[i]);
                result = i; //k=3 centroid dla klastra 0
            }
        }
        return result;
    }

    public static double CalculateE(HashMap<Integer, List<Point>> map, int x, Point[] centroid) {
        double distance = 0;
        //obliczanie odleglosci wew. jednego klastra (kwadratow odleglosci [E])
        for (int j = 0; j < map.get(x).size(); j++) {
            distance += Calculations.getEuklidesDistance(map.get(x).get(j), centroid[x]);
        }
        return Math.pow(distance,2);
    }

}