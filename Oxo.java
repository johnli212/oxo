// Noughts and Crosses.  
// Usage:
//  no flags     run in terminal
//  -t           run tests
//  -g           run in GUI mode
class Oxo
{
  private Board board = new Board();

  public static void main(String[] args)
  {
    Oxo program = new Oxo();
    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        if ("-t".equals(args[i])) program.test();
        else if ("-g".equals(args[i])) program.runGraphical();
      }
    }
    else program.run();
  }

  void run()
  {
    Display d = new Display();

    while(!board.gameOver()) {
      d.drawBoard(board);
      d.getInput(board);
    }
    d.drawBoard(board);
    System.out.println(board.winner().winmsg());
  }

  void runGraphical() {
      GDisplay g = new GDisplay(board);
      g.setVisible(true);
  }

  void test()
  {
    Cell c = new Cell();
    Board b = new Board();
    Validator v = new Validator();

    c.test();
    b.test();
    v.test();
  }
}
