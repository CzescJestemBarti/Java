/**
 *
 *  @author Dąbrowski Bartosz S18967
 *
 */


package zad3;


import javax.swing.*;
public class Main {

  public static void main(String[] args) {
    SwingUtilities.invokeLater( ()-> {
        JFrame f = new JFrame();
        JButton b = new JButton("Myszą ciśnij");
        b.addMouseListener ( (MousePressListener) e -> System.out.println("ok") );
        f.add(b);
        f.pack();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
      }
    );
  }
}
