package software.ulpgc.imageviewer;

import java.io.File;
import java.nio.file.Paths;

public class Main {
    public static final String root = "C:\\Users\\mcash\\Pictures";
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        File file = Paths.get(root).toFile();
        Image image = new FileImageLoader(file).load();
        frame.imageDisplay().show(image);
        frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
        frame.add(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);
    }}
