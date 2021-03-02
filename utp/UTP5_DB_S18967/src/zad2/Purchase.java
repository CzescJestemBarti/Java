/**
 *
 *  @author Dąbrowski Bartosz S18967
 *
 */

package zad2;


public class Purchase {

    String id_klienta;
    String nazwisko;
    String imie;
    String nazwa_towaru;
    double cena;
    double zakupiona_ilość;
    double koszt;

    Purchase(String id_klienta, String nazwisko, String imie, String nazwa_towaru, double cena, double zakupiona_ilość,double koszt){
        this.id_klienta=id_klienta;
        this.nazwisko=nazwisko;
        this.imie=imie;
        this.nazwa_towaru=nazwa_towaru;
        this.cena=cena;
        this.zakupiona_ilość=zakupiona_ilość;
        this.koszt=koszt;
    }

    public String getId_klienta(){
        return id_klienta;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    public Double getKoszt(){
        return koszt;
    }

    @Override
    public String toString() {
        return id_klienta + ";" + nazwisko + " " + imie + ";" + nazwa_towaru + ";" + cena +
                ";" + zakupiona_ilość;
    }
}
