import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//fuente Arial
public class Practica2 extends JFrame {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(Practica2::new);
  }

  public Practica2() {
    setTitle("Lab 2.Ej");
    setLayout(new BorderLayout(0,0));
    // setSize(315, 480);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    add(new CuadradoDeTexto(), BorderLayout.NORTH);
    add(new CuadroDeCuadraditos(), BorderLayout.CENTER);
    pack();

    setResizable(false);
    setVisible(true);
  }



  public class CuadradoDeTexto extends JPanel {
    public CuadradoDeTexto () {
      setPreferredSize(new Dimension(315, 40));
    }
    protected void paintComponent (Graphics g) {
      super.paintComponent(g);

      g.setFont(new Font("Arial", Font.BOLD, 20));
      g.drawString("Peter Keler", 8, 20);

      g.setFont(new Font("Arial", Font.PLAIN, 10));
      g.drawString("De Stijl 1", 8, 30);

    }

  }

  public class CuadroDeCuadraditos extends JPanel {

    private static final Color PETER_RED = new Color(198, 58, 33);
    private static final Color PETER_YELLOW = new Color(230, 183, 31);
    private static final Color PETER_BLUE = new Color(63, 75, 148);
    private static final Color PETER_DARK_GREY = new Color(76, 76, 76);
    private static final Color PETER_MID_GREY = new Color(130, 130, 130);
    private static final Color PETER_LIGHT_GREY = new Color(162, 162, 162);

    private final int ancho = 315;
    private final int alto = 440;

    private final double CAncho = ancho/15;
    private final double CAlto = alto/18;



    public CuadroDeCuadraditos() {
      setPreferredSize(new Dimension(ancho, (int) CAlto*18));
    }

    protected void paintComponent (Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;

      g2d.setPaint(Color.WHITE);
      g2d.fill(new Rectangle2D.Double(0, 0, 2*CAncho, 11*CAlto));

      g2d.setPaint(PETER_YELLOW);
      g2d.fill(new Rectangle2D.Double(2*CAncho, 0, 4*CAncho, 11*CAlto));

      g2d.setPaint(PETER_RED);
      g2d.fill(new Rectangle2D.Double(6*CAncho, 0, 9*CAncho, 11*CAlto));

      g2d.setPaint(PETER_BLUE);
      g2d.fill(new Rectangle2D.Double(0, 11*CAlto, 6*CAncho, 7*CAlto));

      g2d.setPaint(Color.WHITE);
      g2d.fill(new Rectangle2D.Double(6*CAncho, 11*CAlto, 9*CAncho, 3*CAlto));

      g2d.setPaint(PETER_DARK_GREY);
      g2d.fill(new Rectangle2D.Double(6*CAncho, 14*CAlto, 3*CAncho, 3*CAlto));

      g2d.setPaint(PETER_MID_GREY);
      g2d.fill(new Rectangle2D.Double(9*CAncho, 14*CAlto, 3*CAncho, 3*CAlto));

      g2d.setPaint(PETER_LIGHT_GREY);
      g2d.fill(new Rectangle2D.Double(12*CAncho, 14*CAlto, 3*CAncho, 3*CAlto));

      g2d.setPaint(Color.BLACK);
      g2d.fill(new Rectangle2D.Double(6*CAncho, 17*CAlto, 9*CAncho, 1*CAlto));

      pack();

    }
  }
}
