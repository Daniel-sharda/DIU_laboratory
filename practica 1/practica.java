import java.awt.*;
import javax.swing.*;

public class practica {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Hello World Swing");
      frame.setSize(300, 900);
      JLabel label = new JLabel("Hello, World!");
      frame.add(label);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    });
  }
}
