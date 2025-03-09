import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EjOpcional1 extends JFrame {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(EjOpcional1::new);
  }

  public EjOpcional1() {
    setTitle("Ejercicio 1");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new GridLayout(1, 2));
    setSize(400, 300);

    JPanel cuadroColor = new JPanel();
    cuadroColor.setBackground(Color.LIGHT_GRAY);
    cuadroColor.setPreferredSize(new Dimension(200, 300));


    add(crearBotonera(cuadroColor));
    add(cuadroColor);


    pack();

    setVisible(true);


    
  }


  public JPanel crearBotonera(JPanel cuadroColor) {
    JPanel botonera = new JPanel();
    botonera.setLayout(new GridLayout(3, 1, 20, 20));

    Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    String[] colorTexto = {"Rojo", "Verde", "Azul"};

    for(int i = 0; i < colors.length; i++) {
      botonera.add(addBoton(cuadroColor, colors[i], colorTexto[i]));
    }

    return botonera;
  }

  public JButton addBoton(JPanel cuadroColor, Color color, String nombre) {
    JButton button = new JButton(nombre);
    button.setBackground(color);

    button.setPreferredSize(new Dimension(200, 100));
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cuadroColor.setBackground(color);
        button.setEnabled(false);
      }
    });
    return button;

  }
}
