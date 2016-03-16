import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GDisplay extends JFrame
{
  private JPanel mainpanel = new JPanel();
  private JPanel buttonpanel = new JPanel();
  private JPanel statusbar = new JPanel();
  private JButton[] buttons = new JButton[9];
  private JLabel statuslabel = new JLabel();
  private int borders = 5;

  void init(Board board)
  {
    mainpanel.setBorder(BorderFactory.createEmptyBorder(borders, borders,
                        borders, borders));
    mainpanel.setLayout(new BorderLayout(borders, borders));

    initButtons(board);
    initStatus(board);

    setTitle("OXO");
    setSize(200,200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    add(mainpanel);
  }

  private void initStatus(Board board)
  {
    statusbar.setPreferredSize(new Dimension(mainpanel.getWidth(), 20));
    statuslabel.setText("Player " + board.turn().num() + "'s turn ("
                        + board.turn().mark() + ")");
    statusbar.add(statuslabel);
    mainpanel.add(statusbar, BorderLayout.SOUTH);
  }

  // Initaliases the grid of buttons and the events which occurr when they are
  // clicked.
  private void initButtons(Board board)
  {
    buttonpanel.setLayout(new GridLayout(board.rowNum(), board.colNum(),
                    borders, borders));

    for (int i = 0; i < (board.colNum() * board.rowNum()); i++) {
      buttonpanel.add(buttons[i]);
      setButtonActionOnClick(buttons[i], buttons, i, board);
    }
    mainpanel.add(buttonpanel);
  }
  
  private void setButtonActionOnClick(JButton button, JButton[] buttons, int n, 
                                      Board board)
  {
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button.setText("" + board.turn().mark());
        button.setEnabled(false);
        board.step(board.cell(n / board.rowNum(), n % board.rowNum()));
        statuslabel.setText("Player " + board.turn().num() + "'s turn ("
                            + board.turn().mark() + ")");
        if (board.gameOver()) {
          statuslabel.setText(board.winner().winmsg());
          for (int i = 0; i < (board.colNum() * board.rowNum()); i++) {
            buttons[i].setEnabled(false);
          }
        }
      }
    });
  }

  GDisplay(Board board)
  {
    for (int i = 0; i < (board.colNum() * board.rowNum()); i++) {
      buttons[i] = new JButton("");
    }
    init(board);
  }
}
