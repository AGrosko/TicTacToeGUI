import javax.swing.*;
import java.awt.*;

public class TicTacToeViewer {
    public static void main(String[] args) {


        TicTacToeFrame frame = new TicTacToeFrame();

        frame.setTitle("Fortune Teller");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (dimension.width)/2+ (dimension.width)/4;
        int height  = (dimension.height)/2 + (dimension.height)/4;
        width = height;
        int x = (dimension.width - width) / 2;

        int y = (dimension.height - height) / 2;
        frame.setLocation(x,y);
        frame.setSize(width,height);
        System.out.println(dimension.width);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
}}
