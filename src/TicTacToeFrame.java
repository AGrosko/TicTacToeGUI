import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {

    JPanel mainPnl;
    JPanel controlPnl;

    JButton OneOneBtn;
    JButton OneTwoBtn;
    JButton OneThreeBtn;
    JButton TwoOneBtn;
    JButton TwoTwoBtn;
    JButton TwoThreeBtn;
    JButton ThreeOneBtn;
    JButton ThreeTwoBtn;
    JButton ThreeThreeBtn;

    public TicTacToeFrame(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createControlPnl();
        mainPnl.add(controlPnl);
        this.add(mainPnl);
    }
    private void createControlPnl(){
        OneOneBtn = new JButton();
        OneTwoBtn = new JButton();
        OneThreeBtn = new JButton();

        TwoOneBtn = new JButton();
        TwoTwoBtn = new JButton();
        TwoThreeBtn = new JButton();

        ThreeOneBtn = new JButton();
        ThreeTwoBtn = new JButton();
        ThreeThreeBtn = new JButton();

        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(3,3));
        controlPnl.add(OneOneBtn);
        controlPnl.add(TwoOneBtn);
        controlPnl.add(ThreeOneBtn);

        controlPnl.add(OneTwoBtn);
        controlPnl.add(TwoTwoBtn);
        controlPnl.add(ThreeTwoBtn);



    }
}
