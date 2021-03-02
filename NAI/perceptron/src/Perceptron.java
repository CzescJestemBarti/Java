import java.util.ArrayList;
import java.util.Collections;

public class Perceptron {
    double a;
    double[] wagi;
    int theta;
    double TP = 0, FP = 0, TN = 0, FN = 0;

    private ArrayList<Iris> trainData, testData;


    //Setosa=1 //Virginica=0

    //Konstruktor dla pliku testowego
    public Perceptron(ArrayList<Iris> trainData, ArrayList<Iris> testData, double a) {
        this.a = a;
        this.testData = testData;
        this.trainData = trainData;

        Collections.shuffle(trainData);
        Collections.shuffle(testData);

        wagi = new double[4];
        for (int i = 0; i < wagi.length; i++) {
            wagi[i] = (Math.random() * 10) - 5;//przedzial od (-5,5) dla rzeczywistych
        }
        theta = (int) (Math.random() * 4) - 2;//przedzial od <-2,2> dla calkowitych
    }

    //Konstruktor dla wlasnych danych
    public Perceptron(ArrayList<Iris> trainData, double a){
        this.a=a;
        this.trainData=trainData;

        Collections.shuffle(trainData);

        wagi = new double[4];
        for (int i = 0; i < wagi.length; i++) {
            wagi[i] = (Math.random() * 10) - 5; //przedzial od (-5,5) dla rzeczywistych
        }
        theta = (int) (Math.random() * 4) - 2; //przedzial od <-2,2> dla calkowitych
    }

    public void Learn() {
        double resultNet;

        for (Iris iris : trainData) {
            resultNet = Calculate.obliczNet(wagi, iris);
            int y;
            if (resultNet >= theta) {
                y = 1; //y=1 to setosa
                if (!iris.type.equals("Iris-setosa")) {
                    FP++; //false positive
                    wagi = Calculate.noweWagi(wagi, iris, a, 0, y);
                    theta = Calculate.nowaTheta(a, theta, 0, y);
                } else {
                    TP++; //true positive
                }
            } else {
                y = 0; //y=0 to virginica
                if (!iris.type.equals("Iris-virginica")) {
                    FN++; //false negative
                    wagi = Calculate.noweWagi(wagi, iris, a, 1, y);
                    theta = Calculate.nowaTheta(a, theta, 1, y);
                } else {
                    TN++; //true negative
                }
            }
        }
        System.out.println("*** Dla danych treningowych ***");
        dokladnosc();
    }

    public void Test() {
        double resultNet;

        for (Iris iris : testData) {
            resultNet = Calculate.obliczNet(wagi, iris);
            int y;
            if (resultNet >= theta) {
                y = 1;
                trainData.add(iris);
                if (!iris.type.equals("Iris-setosa")) {
                    FP++;
                    wagi = Calculate.noweWagi(wagi, iris, a, 0, y);
                    theta = Calculate.nowaTheta(a, theta, 0, y);
                } else
                    TP++;
            } else {
                y = 0;
                trainData.add(iris);
                if (!iris.type.equals("Iris-virginica")) {
                    FN++;
                    wagi = Calculate.noweWagi(wagi, iris, a, 1, y);
                    theta = Calculate.nowaTheta(a, theta, 1, y);
                } else
                    TN++;
            }
        }
        System.out.println("*** Dla danych testowych ***");
        dokladnosc();
    }

    private void dokladnosc() {

        double poprawne = ((TN + TP) / (TN + TP + FN + FP)); //true negative + true positive/all
        double setosa = ( TP / (FP + TP)); // true positive / all positive
        double virginica = TN / (FN + TN); // true negative / all negative

        System.out.println("Dokladnosc dla setosy: " + setosa +
                ", virginiki: " + virginica
                + " oraz calosci: " + poprawne);
    }
}