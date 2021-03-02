public abstract class Iris {
    double sepallength; //t
    double sepalwidth; //u
    double petallength; //w
    double petalwidth; //x
    String type;

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
}