package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


class ImageSelectionPanel extends JPanel {

    private int finalWidth;
    private int finalHeight;
    private int id;
    public JPanel overlayPanel;

    public ImageSelectionPanel(int width, int height, String imagePath, int id) throws IOException {
        setLayout(new BorderLayout());
        this.finalWidth = width;
        this.finalHeight = height;
        this.id = id;

        // Creamos un JLayeredPane para gestionar el overlay
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(finalWidth, finalHeight));
        add(layeredPane, BorderLayout.CENTER);

        setPreferredSize((new Dimension(finalWidth, finalHeight))); //

        // TODO: Paso 1) Cargamos las imágenes en un BufferedImage
        // Pista: usa ImageIO.read(getClass().getResource("/" + imagePath)); si imagePath no es una URL
        // Pista: usa ImageIO.read(new URL(imagePath)); si imagePath es una URL
        // --- Escribe aquí tu código ---
        BufferedImage img = javax.imageio.ImageIO.read(new URL(imagePath));
        BufferedImage imgTick= javax.imageio.ImageIO.read(getClass().getResource("/tick.png"));;
        // ------------------------------

        // Escalamos la imagen manteniendo la relación de aspecto
        double aspectRatio = (double) img.getWidth() / img.getHeight();
        int newWidth, newHeight;
        if (finalWidth / (double) finalHeight > aspectRatio) {
            newWidth = finalWidth;
            newHeight = (int) (newWidth / aspectRatio);
        } else {
            newHeight = finalHeight;
            newWidth = (int) (newHeight * aspectRatio);
        }
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Calculamos los offsets para centrar la imagen escalada
        int xOffset = (finalWidth - newWidth) / 2;
        int yOffset = (finalHeight - newHeight) / 2;

        // TODO: Paso 2) Creamos un panel para dibujar la imagen
        // Pista: haz un override del método paintComponent para dibujar la imagen escalada
        // puedes usar: g2d.drawImage(scaledImage, xOffset, yOffset, this);
        // --- Escribe aquí tu código ---
        JPanel imagePanel= new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(scaledImage, xOffset, yOffset, this);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
        };
        imagePanel.setSize(new Dimension(finalWidth, finalHeight));
        imagePanel.setVisible(true);
        // ------------------------------

        // Añadimos el panel en la capa inferior
        layeredPane.add(imagePanel, JLayeredPane.DEFAULT_LAYER);

        // TODO: Paso 3) Creamos un panel para el overlay (usa la instancia de overlayPanel)
        // Pista: usa un JPanel con un override del método paintComponent para dibujar un rectángulo negro semitransparente
        // puedes usar este color: new Color(0, 0, 0, 60)
        // Pista 2: dibuja después la imagen del tick con una separación con respecto al borde inferior y al derecho
        // --- Escribe aquí tu código ---
        Color color = new Color(0, 0, 0, 60);
        overlayPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(color);
                g2d.fill(new Rectangle2D.Double(0, 0, img.getWidth(), img.getHeight()));
                g2d.drawImage(imgTick, img.getWidth() - 30, img.getHeight() - 30, null);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                setSize(finalWidth, finalHeight);
            }
        };
        overlayPanel.setOpaque(false);
        overlayPanel.setSize(new Dimension(finalWidth, finalHeight));

        // ------------------------------

        // Añadimos el panel a la capa overlay
        layeredPane.add(overlayPanel, JLayeredPane.PALETTE_LAYER);

        // TODO: Paso 4) Haz que el panel de overlay no sea visible por defecto
        // --- Escribe aquí tu código ---
        overlayPanel.setVisible(false);

        // ------------------------------

        // TODO: Paso 5) Haz que el JFrame pueda escuchar clicks y cambia la visibilidad del panel de overlay para hacer el toggle
        // Toggle de visibilidad al hacer click
        // --- Escribe aquí tu código ---
        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                overlayPanel.setVisible(!overlayPanel.isVisible());
            }
        });
        // ------------------------------
    }

    public boolean isSelected() {
        // TODO: Paso 6) completa esta función para que devuelva true si la imagen está seleccionada, false si no
        return overlayPanel.isVisible();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame window = new JFrame();
                window.setTitle("Select Image");
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setSize(300, 300);
                window.setLocationRelativeTo(null);

                try {
                    window.add(new ImageSelectionPanel(200,300, "https://picsum.photos/200/300",0));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                window.pack();
                window.setVisible(true);
            }
        });
    }
}