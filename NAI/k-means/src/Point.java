public class Point {
    double[] values;
    int dimensions, number;

    //punkt, w ktorym chce zapamietywac numer (index)
    Point(int number, double... values){ //P1: {dane}, P2:{dane}
        this.values=values;
        dimensions=values.length;
        this.number=number;
    }

    //centroid, w ktorym nie chce zapamietywac numeru
    Point(double... values){ // Centroid: {dane}, Centroid: {dane}
        this.values=values;
        dimensions=values.length;
    }

    @Override
    public String toString() {
        String vectors="";
        for(int i=0; i<dimensions; i++){
            if(i!=dimensions-1)
                vectors+=values[i]+"; ";
            else
                vectors+=values[i]+";";
        }
        if(number!=0)
            return "P"+number+": "+vectors;
        else
            return "{Centroid: "+vectors+"}";
    }
}