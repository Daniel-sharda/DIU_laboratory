import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Lab1Ejercicio extends JFrame {
  private static int windowHeight = 570;
  private static int windowLength = 280;
  private static int footerHeaderHeight = 60;
  private static double percentHeader = 0.2;

  public static void main(String args[]) {
    SwingUtilities.invokeLater(Lab1Ejercicio::new);
  }

  public Lab1Ejercicio() {
    setTitle("EJ. Lab 1");
    setSize(windowLength,windowHeight);
    setLayout(new BorderLayout(0, 16));

    //Center
    add(createCentralPanel(), BorderLayout.CENTER);
    //South
    add(createSouthPanel(), BorderLayout.SOUTH);


    //North
    // frame.add(createNorthPanel(), BorderLayout.NORTH);
    add(createNorthPanelGridBagLayout(), BorderLayout.NORTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }





  private static JPanel createColorPanel(Color color) {
    JPanel panel = new JPanel();
    panel.setBackground(color);
    return panel;
  }

  private static JPanel createColorPanel (Color color, Dimension dimension) {
    JPanel panel = createColorPanel(color);
    panel.setPreferredSize(dimension);
    return panel;
  }

  //Panel Central
  private static JPanel createCentralPanel() {
    JPanel central = new JPanel();
    central.setLayout(new GridLayout(2, 2, 8, 8));
    for (int i = 0; i < 2; i++) {
      central.add(createColorPanel(Color.black));
      central.add(createCentralRightPanel());
    }
    return central;
  }

  private static JPanel createCentralRightPanel() {
    JPanel grid = new JPanel();
    grid.setLayout(new GridLayout(3, 1, 0, 8));
    for (int i = 0; i < 3; i++) {
      grid.add(createColorPanel(Color.gray));
    }
    return grid;
  }

  //Añadir el panel SUR
  private static JPanel createSouthPanel() {
    JPanel south = new JPanel();
    south.setPreferredSize(new Dimension(windowLength, footerHeaderHeight));
    south.setLayout(new GridLayout(1, 4, 16, 16));

    Color[] colors = {Color.red, Color.green, Color. blue, Color.yellow};
    for (var i = 0; i < 4; i++) {
      south.add(createColorPanel(colors[i]));
    }

    return south;
  }


  //Añadir el panel norte


  private static JPanel createNorthPanel() {
    JPanel north = new JPanel();
    north.setPreferredSize(new Dimension(windowLength, footerHeaderHeight));
    north.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

    north.add(createColorPanel(Color.green, new Dimension((int)(windowLength * percentHeader), footerHeaderHeight)));
    north.add(createColorPanel(Color.red, new Dimension((int)(windowLength * (1 - percentHeader)), footerHeaderHeight)));

    return north;
  }

  private static JPanel createNorthPanelGridBagLayout() { 
    JPanel north = new JPanel();
    north.setPreferredSize(new Dimension(windowLength, footerHeaderHeight));
    north.setLayout(new GridBagLayout());

    GridBagConstraints gb = new GridBagConstraints();
    gb.fill = GridBagConstraints.BOTH;
    gb.weighty = 1;

    addPanelGridBag(north, createColorPanel(Color.green), gb, 0, 0, 1, 1, 1);
    addPanelGridBag(north, createColorPanel(Color.red), gb, 1, 0, 4, 1, 4);
    return north;
  }

  private static void addPanelGridBag(JPanel layoutPanel, Component c, GridBagConstraints gb, int x, int y, int gridWidth, int gridHeight, double weightx) {
    gb.gridx = x;
    gb.gridy = y;
    gb.gridwidth = gridWidth;
    gb.gridheight = gridHeight;

    gb.weightx = weightx;

    layoutPanel.add(c, gb);
  }
}
