import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TicTacToeFrame extends JFrame {

    JPanel mainPnl;
    JPanel controlPnl;


    TicTacToeTile[][] board;
    JButton quitBtn;

    private static final int ROW = 3;
    private static final int COL = 3;


    boolean finished = false;
    boolean playing = true;

    String player = "X";
    int moveCnt = 0;
    int row = -1;
    int col = -1;
    final int MOVES_FOR_WIN = 5;
    final int MOVES_FOR_TIE = 7;




    public TicTacToeFrame(){



        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createControlPnl();
        mainPnl.add(controlPnl);
        this.add(mainPnl);




    }






    private void createControlPnl(){

        board = new TicTacToeTile[ROW][COL];

        quitBtn = new JButton("Quit");

        controlPnl = new JPanel();
        controlPnl.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();


        board[0][0] = new TicTacToeTile(1,1);
        board[1][0] = new TicTacToeTile(2,1);
        board[2][0] = new TicTacToeTile(3,1);

        board[0][1] = new TicTacToeTile(1,2);
        board[1][1] = new TicTacToeTile(2,2);
        board[2][1] = new TicTacToeTile(3,2);

        board[0][2] = new TicTacToeTile(1,3);
        board[1][2] = new TicTacToeTile(2,3);
        board[2][2] = new TicTacToeTile(3,3);

        cons.ipadx = 150;
        cons.ipady = 150;
        cons.gridx= 0;
        cons.gridy = 0;
        controlPnl.add(board[0][0],cons);
        cons.gridx= 1;

        controlPnl.add(board[1][0],cons);
        cons.gridx = 2;
        controlPnl.add(board[2][0],cons);

        cons.gridx=0;
        cons.gridy = 1;
        controlPnl.add(board[0][1],cons);
        cons.gridx=1;
        controlPnl.add(board[1][1],cons);
        cons.gridx=2;
        controlPnl.add(board[2][1],cons);

        cons.gridx=0;
        cons.gridy = 2;
        controlPnl.add(board[0][2],cons);
        cons.gridx=1;
        controlPnl.add(board[1][2],cons);
        cons.gridx=2;
        controlPnl.add(board[2][2],cons);
        cons.gridx=1;
        cons.gridy=3;
        cons.ipadx=100;
        cons.ipady=100;
        controlPnl.add(quitBtn,cons);


        board[0][0].addActionListener(new tileListener(0,0));
        board[0][1].addActionListener(new tileListener(0,1));
        board[0][2].addActionListener(new tileListener(0,2));

        board[1][0].addActionListener(new tileListener(1,0));
        board[1][1].addActionListener(new tileListener(1,1));
        board[1][2].addActionListener(new tileListener(1,2));

        board[2][0].addActionListener(new tileListener(2,0));
        board[2][1].addActionListener(new tileListener(2,1));
        board[2][2].addActionListener(new tileListener(2,2));



        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice =JOptionPane.showConfirmDialog(null,"Do you want to quit?");
                if (choice == 0){
                    System.exit(0);
                }
            }
        });

    }


    class tileListener implements ActionListener{
        int row;
        int col;
        public tileListener(int Row, int Col){
            row = Row;
            col = Col;
        }

        public void actionPerformed(ActionEvent e){

            if (e.getSource() instanceof TicTacToeTile){
                if (((TicTacToeTile) e.getSource()).getState().equals("empty")){
                ((TicTacToeTile) e.getSource()).setText(player);}



                //copy and pasted code
                //while(!isValidMove(row, col));
                if(!isValidMove(row,col)){
                    JOptionPane.showMessageDialog(null,"Not a valid move");
                    return;
                }
                board[row][col].setState(player);
                moveCnt++;

                if(moveCnt >= MOVES_FOR_WIN)
                {
                    if(isWin(player))
                    {
                       // display();
                        //System.out.println("Player " + player + " wins!");
                        int choice = JOptionPane.showConfirmDialog(null,player + " Won! \n" +"Do you want to play again?");
                        if (choice == 0){
                            controlPnl.setVisible(false);

                            createControlPnl();
                            mainPnl.add(controlPnl);
                        }
                        else{System.exit(0);}
                        playing = false;
                    }
                }
                if(moveCnt >= MOVES_FOR_TIE)
                {
                    if(isTie())
                    {
                       // display();
                        int choice = JOptionPane.showConfirmDialog(null,"It's a Tie! \n Do you want to play again?");
                        if (choice == 0){
                            controlPnl.setVisible(false);

                            createControlPnl();
                            mainPnl.add(controlPnl);
                        }
                        else{System.exit(0);}

                        //System.out.println("It's a Tie!");
                        playing = false;
                    }
                }
                if(player.equals("X"))
                {
                    player = "O";
                }
                else
                {
                    player = "X";
                }

                //end paste

            }

        }
    }


    //copy and pasted methods
    private  boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].getState().equals("empty"))
            retVal = true;

        return retVal;

    }
    private  boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }
    private  boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getState().equals(player) &&
                    board[1][col].getState().equals(player) &&
                    board[2][col].getState().equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private  boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getState().equals(player) &&
                    board[row][1].getState().equals(player) &&
                    board[row][2].getState().equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private  boolean isDiagnalWin(String player)
    {
        // checks for a diagonal win for the specified player

        if(board[0][0].getState().equals(player) &&
                board[1][1].getState().equals(player) &&
                board[2][2].getState().equals(player) )
        {
            return true;
        }
        if(board[0][2].getState().equals(player) &&
                board[1][1].getState().equals(player) &&
                board[2][0].getState().equals(player) )
        {
            return true;
        }
        return false;
    }

    // checks for a tie before board is filled.
    // check for the win first to be efficient
    private  boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getState().equals("X") ||
                    board[row][1].getState().equals("X") ||
                    board[row][2].getState().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].getState().equals("O") ||
                    board[row][1].getState().equals("O") ||
                    board[row][2].getState().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getState().equals("X") ||
                    board[1][col].getState().equals("X") ||
                    board[2][col].getState().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].getState().equals("O") ||
                    board[1][col].getState().equals("O") ||
                    board[2][col].getState().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].getState().equals("X") ||
                board[1][1].getState().equals("X") ||
                board[2][2].getState().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].getState().equals("O") ||
                board[1][1].getState().equals("O") ||
                board[2][2].getState().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].getState().equals("X") ||
                board[1][1].getState().equals("X") ||
                board[2][0].getState().equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].getState().equals("O") ||
                board[1][1].getState().equals("O") ||
                board[2][0].getState().equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }
}
    //end paste

