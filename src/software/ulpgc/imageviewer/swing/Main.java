package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.controller.FileImageLoader;
import software.ulpgc.imageviewer.controller.NextImageCommand;
import software.ulpgc.imageviewer.controller.PreviousImageCommand;
import software.ulpgc.imageviewer.model.Image;

import java.io.File;
import java.nio.file.Paths;
import javax.swing.JFrame;

public class Main {
    public static final String root = "C:\\Users\\mcash\\Pictures";
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        File file = Paths.get(root).toFile();
        Image image = new FileImageLoader(file).load();
        frame.imageDisplay().show(image);
        frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
        frame.add(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);
    }}