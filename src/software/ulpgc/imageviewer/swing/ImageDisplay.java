package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.model.Image;

public interface ImageDisplay {
    void show(Image image);
    Image image();
}
