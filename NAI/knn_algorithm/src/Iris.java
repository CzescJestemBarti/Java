public abstract class Iris {
   private double sepallength;
   private double sepalwidth;
   private double petallength;
   private double petalwidth;
   private String type;

        Iris(double sepallength, double sepalwidth, double petallength,
             double petalwidth, String type){
            this.petallength=petallength;
            this.petalwidth=petalwidth;
            this.sepallength=sepallength;
            this.sepalwidth=sepalwidth;
            this.type=type;
        }

    @Override
    public String toString() {
        return type;
    }

    //odleglosc sprawdzana poprzez uzycie metryki euklidesowskiej.
    public double getDistance(double x, double y, double a, double b){
            double pow_distance = Math.pow(petallength-x,2)
                                +Math.pow(petalwidth-y,2)
                                +Math.pow(sepallength-a,2)
                                +Math.pow(sepalwidth-b,2);
            return Math.sqrt(pow_distance);
    }
}