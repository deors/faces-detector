package com.accenture.jprize4.facescounter.livepreview;

import com.accenture.jprize4.facescounter.main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;

public class LivePreviewFrame extends JFrame implements WindowListener, ComponentListener {

    private JPanel content;
    private JLabel imageLabel;
    private Image lastImage;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public LivePreviewFrame() {
        super();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Image Detector Live Preview");
        setSize(WIDTH, HEIGHT);
        setResizable(true);

        addWindowListener(this);

        content = new JPanel();
        content.setLayout(new BorderLayout());
        content.addComponentListener(this);

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        content.add(imageLabel, BorderLayout.CENTER);

        setContentPane(content);

        paintImage(loadImage(this.getClass().getResourceAsStream("/testpic.jpg")));
    }

    public Image loadImage(InputStream imageStream) {
        try {
            return ImageIO.read(imageStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    public void paintImage(Image image) {
        if (imageLabel == null || image == null) {
            return;
        }

        SwingUtilities.invokeLater(() -> {
            lastImage = image;
            ImageIcon icon = new ImageIcon(image);

            double widthMult = (double) WIDTH / icon.getIconWidth();
            double heightMult = (double) HEIGHT / icon.getIconHeight();
            double actualMult = Math.min(widthMult, heightMult);

            Image scaledImage = image;
            /*Image scaledImage = image.getScaledInstance(
                (int) (icon.getIconWidth() * actualMult),
                (int) (icon.getIconHeight() * actualMult),
                Image.SCALE_FAST);*/

            imageLabel.setIcon(new ImageIcon(scaledImage));
            repaint();
        });
    }

    @Override
    public void windowActivated(WindowEvent event) {
    }

    @Override
    public void windowClosed(WindowEvent event) {
    }

    @Override
    public void windowClosing(WindowEvent event) {
        dispose();
        Main.running = false;
    }

    @Override
    public void windowDeactivated(WindowEvent event) {
    }

    @Override
    public void windowDeiconified(WindowEvent event) {
    }

    @Override
    public void windowIconified(WindowEvent event) {
    }

    @Override
    public void windowOpened(WindowEvent event) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
