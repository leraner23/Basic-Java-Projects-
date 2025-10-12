// simple Java based Tic Tac Toe game



import javax.swing.*;
import javax.swing.border.Border; // for JPanel border
import java.awt.*;

// java swing action listener components
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program {
        private static char player = 'X'; // setting the main start with 'X' character,
    // it is in static as the main methods is in static so to make the call between main and other methods

    private static JButton[] buttons =  new JButton[9]; // component of java swing to create buttons,
    // only nine buttons consists as 3 * 3 has 9 places to fill and these buttons act as a places to fill

    // main methods
    public static void main(String[] args){

// Java frame
        JFrame f = new JFrame("Tic Tac Toe");
        f.setSize(600,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLayout(new BorderLayout()); // frame is set in border layout


        JLabel name = new JLabel("Starts with 'x'");

        f.add(name,BorderLayout.NORTH);

        // main board area
        JPanel p = new JPanel();

        Border inner = BorderFactory.createEmptyBorder(80,100,80,100); // border of the buttons inside the board
        Border outer = BorderFactory.createLineBorder(Color.white,6); // board border thickness
        p.setBorder(BorderFactory.createCompoundBorder(inner,outer)); // setting borders for in and out
        p.setBackground(Color.DARK_GRAY);// outside color
        f.add(p,BorderLayout.CENTER);// board placed in center
        p.setLayout(new GridLayout(3,3,3,3)); // border layout (3 * 3 grid formation)

// Buttons
     for (int i = 0; i<9; i++){
         buttons[i] = new JButton("");
         buttons[i].addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     JButton clicked = (JButton) e.getSource(); // e triggered when clicked and returns the object

                     if(!clicked.getText().equals("")){return;} // checking for empty buttons

                     clicked.setText(String.valueOf(player)); // setting the buttons text or placing the fill
                     // logic of the game
                     if(checkWin()){
                        JOptionPane.showMessageDialog(f,player + " "+ "  wins");
                        reset();
                     }else if (draw()){
                        JOptionPane.showMessageDialog(f,"The game is draws");
                         reset();
                     }else{
                         name.setText("Next: " + player); // shows the next character to play

                     }
                    player = (player == 'X')? 'O':'X'; // players  sign changes
                 }
         });

         p.add(buttons[i]); // adding buttons in panel
     }


    }

// win conditions

    private  static boolean checkWin(){
    int [][] combos ={ {0,1,2}, {3,4,5},{6,7,8}, {0,3,6},{1,4,7},{2,5,8}, {0,4,8},{2,4,6}}; // conditions to match

        // all the buttons text
        for(int[]combo : combos){
        String a = buttons[combo[0]].getText();
        String b = buttons[combo[1]].getText();
        String c = buttons[combo[2]].getText();

        // checking for the combos
        if(!a.equals("")&& a.equals(b) && a.equals(c)){
            return true;
        }
        }
    return false;
    }

// reset conditions
    // setting all the buttons text to "" (empty)
    private static void reset(){

        for(JButton button : buttons){
            button.setText("");

        }
        player = 'X';
        JOptionPane.showMessageDialog(null," Board has been reset");

    }

    // draw conditions
    // if the whole buttons are filled but not win by any player then draw
    public static boolean draw(){
    for(JButton button : buttons){
        if(button.getText().equals("")) {
            return false;
        }
    }

    return true;
    }

}
