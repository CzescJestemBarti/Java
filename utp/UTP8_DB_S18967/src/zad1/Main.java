/**
 * @author Dąbrowski Bartosz S18967
 */

package zad1;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ListaTowarow lt = new ListaTowarow();
        ThreadA threadA = new ThreadA(lt);
        ThreadB threadB = new ThreadB(lt);
        threadA.start();
        threadB.start();
        try {
            threadA.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        try {
            threadB.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
