import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HarderSecure
  implements ActionListener
{
  private final Color[] buttonColors = { Color.RED, Color.BLUE, Color.GREEN };
  private Square[][] board;
  private JFrame frame;

  public HarderSecure()
  {
    this.frame = new JFrame();
    this.frame.setLayout(new GridLayout(10, 10));
    this.frame.setDefaultCloseOperation(3);

    this.board = new Square[10][10];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        JButton temp = new JButton();
        this.board[i][j] = new Square(temp, 0);
        temp.setBackground(this.buttonColors[0]);
        this.frame.getContentPane().add(temp);
        temp.addActionListener(this);
      }
    }

    this.frame.setSize(400, 400);
    this.frame.setVisible(true);
  }

  private void check() {
    String s = "";
    for (int i = 0; i < this.board[0].length; i++) {
      for (int j = 0; j < this.board.length; j++) {
        char[] temp = new char[3];
        temp[0] = '0';
        temp[1] = '0';
        temp[2] = '0';
        int red = this.buttonColors[this.board[j][i].colorID].getRed();
        int green = this.buttonColors[this.board[j][i].colorID].getGreen();
        int blue = this.buttonColors[this.board[j][i].colorID].getBlue();
        if (red == 255)
          temp[0] = '1';
        else if (green == 255)
          temp[1] = '1';
        else if (blue == 255) {
          temp[2] = '1';
        }

        s = s + new String(temp);
      }

    }

    if (s.equals("100001001001001001001001100100100001100100001100100100100100100001100100100001001001100100100100100100100001010001100100100100100100100001001001100100100100100100100100100001100100100100100100100100100100100100100100100001010001001001100100100100100100100100100100100100100001001001001001001001100100"))
      JOptionPane.showMessageDialog(this.frame, "Congrats! The key is the word splled out on screen for you \n\nIf you're having trouble deciphering the word, email us a screenshot of your board or the colors for each tile.");
  }

  public void actionPerformed(ActionEvent e)
  {
    for (int i = 0; i < this.board.length; i++) {
      for (int j = 0; j < this.board[0].length; j++) {
          String key = "100001001001001001001001100100100001100100001100100100100100100001100100100001001001100100100100100100100001010001100100100100100100100001001001100100100100100100100100100001100100100100100100100100100100100100100100100001010001001001100100100100100100100100100100100100100001001001001001001001100100";
          this.board[i][j].colorID = (key.substring(i*3+j*30, i*3+j*30+3).indexOf("1"));
          System.out.println(this.board[i][j].colorID);
         // this.board[i][j].colorID = ((this.board[i][j].colorID + 1) % 3);
          this.board[i][j].button.setBackground(this.buttonColors[this.board[i][j].colorID]);
      }
    }

    check();
  }

  public static void main(String[] args) {
    new HarderSecure();
  }
}