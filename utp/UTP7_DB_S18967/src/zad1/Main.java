/**
 *
 *  @author Dąbrowski Bartosz S18967
 *
 */

package zad1;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Letters letters = new Letters("ABCD");
    for (Thread t : letters.getThreads()) System.out.println(t.getName());

    for (Thread t : letters.getThreads())
      t.start();

      Thread.sleep(5000);

      letters.setRun();

    System.out.println("\nProgram skończył działanie");
  }

}
