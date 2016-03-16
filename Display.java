// Displays the board and row / col labels and reads user input.
import java.util.Scanner;

class Display
{
  private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
  private Validator validator = new Validator();

  private void drawColLabels(int colnum)
  {
    System.out.print("  ");
    for (int i = 0; i < colnum; i++) {
      System.out.print(i + 1);
    }
    System.out.println("");
  }

  private void drawRowLabel(int row)
  {
    if (row > alphabet.length) throw new Error("Row out of bounds");

    System.out.print("" + alphabet[row] + " ");
  }

  private void drawCell(Cell c)
  {
    if (c == null)  throw new Error("Cell was null");

    System.out.print(c.value());
  }

  private void drawRow(int row, Board board)
  {
    if (row >= board.rowNum()) throw new Error("Row out of bounds");

    drawRowLabel(row);

    for (int i = 0; i < board.colNum(); i++) {
      drawCell(board.cell(row, i));
    }

    System.out.println("");
  }

  void drawBoard(Board board)
  {
    if (board == null)  throw new Error("Board was null");

    drawColLabels(board.colNum());
    for (int i = 0; i < board.rowNum(); i++) {
      drawRow(i, board);
    }
  }

  void getInput(Board board)
  {
    if (board == null)  throw new Error("Board was null");

    Cell c = new Cell();
    Scanner input = new Scanner(System.in);

    do {
      System.out.print("It is Player " + board.turn().num() + "'s turn ("
                        + board.turn().mark() + ").  Enter your move: ");
      c = validator.validate(input.next(), board);
    }
    while (!validator.valid());
    board.step(c);
  }
}
