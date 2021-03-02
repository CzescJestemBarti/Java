package zad1;

public class ThreadB extends Thread {

    ListaTowarow lt;
    int suma = 0, count = 0;

    ThreadB(ListaTowarow lt) {
        this.lt = lt;
    }

    public void run() {
        while (!lt.czyKoniecTowarow()) {
            Towar jedenTowar = lt.getTowar();
            if (jedenTowar != null) {
                suma += jedenTowar.wage;
                count++;
                if (count % 100 == 0) {
                    System.out.println("policzono wage " + count + " towarow");
                }
            }
        }
        System.out.println("suma wag = " + suma);
    }
}