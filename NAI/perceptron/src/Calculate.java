interface Calculate {
    public static double obliczNet(double wagi[], Iris iris) {
        return iris.sepallength * wagi[0]
                + iris.sepalwidth * wagi[1]
                + iris.petallength * wagi[2]
                + iris.petalwidth * wagi[3];
    }

    public static double obliczNetDlaWektor(double wagi[], double[] wektor){
        return wektor[0]*wagi[0] + wektor[1]*wagi[1] + wektor[2]*wagi[2] + wektor[3]*wagi[3];
    }

    public static double[] noweWagi(double wagi[], Iris iris,
                                    double a, double oczekiwaneWyjscie,
                                    double naszeWyjscie) {

        double var = (oczekiwaneWyjscie - naszeWyjscie) * a;
        double tmp[] = {iris.sepallength, iris.sepalwidth, iris.petallength, iris.petalwidth};

        for (int i = 0; i < wagi.length; i++) {
            tmp[i] *= var;
            wagi[i] += tmp[i];
        }
        return wagi;
    }

    public static int nowaTheta(double a, int theta, double oczekiwaneWyjscie, double naszeWyjscie) {
        return (int) (theta + (naszeWyjscie - oczekiwaneWyjscie) * a * (-1));
    }
}