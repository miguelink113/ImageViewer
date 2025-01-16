package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.model.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private software.ulpgc.imageviewer.model.Image image;
    private BufferedImage bitmap;

    @Override
    public void show(software.ulpgc.imageviewer.model.Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        Resizer resizer = new Resizer(new Dimension(this.getWidth(), this.getHeight()));
        Dimension resized = resizer.resize(new Dimension(bitmap.getWidth(), bitmap.getHeight()));

        int x = (this.getWidth() - resized.width) / 2;  // Usar resized.width correctamente
        int y = (this.getHeight() - resized.height) / 2;  // Usar resized.height correctamente

        g.drawImage(bitmap, x, y, resized.width, resized.height, null);
    }

    public static class Resizer {
        private final Dimension panelDimension;

        public Resizer(Dimension panelDimension) {
            this.panelDimension = panelDimension;
        }

        public Dimension resize(Dimension imageDimension) {
            int panelWidth = panelDimension.width;
            int panelHeight = panelDimension.height;
            int imageWidth = imageDimension.width;
            int imageHeight = imageDimension.height;

            double panelAspectRatio = (double) panelWidth / panelHeight;
            double imageAspectRatio = (double) imageWidth / imageHeight;

            int resizedWidth;
            int resizedHeight;

            if (panelAspectRatio > imageAspectRatio) {
                resizedHeight = panelHeight;
                resizedWidth = (int) (imageWidth * ((double) panelHeight / imageHeight));
            } else {
                resizedWidth = panelWidth;
                resizedHeight = (int) (imageHeight * ((double) panelWidth / imageWidth));
            }

            return new Dimension(resizedWidth, resizedHeight);
        }
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
