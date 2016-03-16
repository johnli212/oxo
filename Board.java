// The Board object controls the gamestate: it tracks whose turn it is, checks
// if the game is over, and what cells in the board are filled and by whom.
class Board
{
  private int rownum = 3, colnum = 3, filledcells = 0;
  private Cell[][] grid = new Cell[colnum][rownum];
  private Player turn = Player.P1, winner = Player.NONE;
  private boolean gameover = false;
  Tester t = new Tester();
  
  Cell cell(int row, int col) { return grid[row][col]; }
  Cell[][] grid() { return grid; }
  int rowNum() { return rownum; }
  int colNum() { return colnum; }
  boolean gameOver() { return gameover; }
  Player winner() { return winner; }
  Player turn() { return turn; }
  
  // Moves the game forward by one turn.
  void step(Cell c)
  {
    if (c == null) throw new Error("null cell");
    
    c.update(turn.mark());
    filledcells++;
    changePlayer();
    trackGameState();
  }
  
  private void changePlayer()
  {
    if (turn != Player.P1 && turn != Player.P2) throw new Error("No-one's turn");
    
    if (turn == Player.P1) turn = Player.P2;
    else turn = Player.P1; 
  }
  
  // Checks to see if the game is over and who has won.
  private void trackGameState()
  {
    if (filledcells >= (rownum * colnum)) {
      gameover = true;
      return;
    }
    
    for (int i = 0; i < rownum; i++) {
      checkString(checkRow(i));
    }
    for (int i = 0; i < colnum; i++) {
      checkString(checkCol(i));
    }
    checkString(checkLDiag());
    checkString(checkRDiag());
  }
  
  // Checks to see if a row contains three Xs or Os
  private String checkRow(int row) 
  {
    if (row >= rownum  || row < 0) throw new Error("Row out of bounds");
    
    String s = "";
    for (int i = 0; i < colnum; i++) {
      s += grid[row][i].value();
    }
    return s;
  }
  
  // Checks to see if a column contains three Xs or Os
  private String checkCol(int col) 
  {
    if (col >= colnum  || col < 0) throw new Error("Col out of bounds");
    
    String s = "";
    for (int i = 0; i < rownum; i++) {
      s += grid[i][col].value();
    }
    return s;
  }
  
  // Checks the diagonal from top left to bottom right
  private String checkLDiag()
  {
    String s = "";
    int j = 0;
    for (int i = 0; i < rownum; i++) {
      s += grid[i][j].value();
      j++;
    }
    return s;
  }
  
  // Checks the diagonal from top right to bottom left
  private String checkRDiag()
  {
    String s = "";
    int j = rownum - 1;
    
    for (int i = 0; i < rownum; i++) {
      s += grid[i][j].value();
      j--;
    }
    return s;
  }
  
  private void checkString(String s)
  {
    if (s == null) throw new Error("bad string");
  
    if (s.equals("XXX")) {
      winner = Player.P1;
      gameover = true;
    }
    if (s.equals("OOO")) {
      winner = Player.P2;
      gameover = true;
    }
  }
  
  Board()
  {
    for (int i = 0; i < rownum; i++) {
      for (int j = 0; j < colnum; j++) {
        grid[j][i] = new Cell();
      }
    }
  }
  
  //Used to initialise the grid for testing
  private void testInit()
  {
    for (int i = 0; i < rownum; i++) {
      for (int j = 0; j < colnum; j++) {
        grid[j][i].update('O');
      }
    }
    grid[1][1].update('X');
    grid[2][2].update('X');
    grid[0][2].update('.');
  }
  
  void test() {
    testInit();
    
    t.is(checkRow(0), "OO.");
    t.is(checkRow(1), "OXO");
    t.is(checkRow(2), "OOX");
    t.is(checkCol(0), "OOO");
    t.is(checkCol(1), "OXO");
    t.is(checkCol(2), ".OX");
    t.is(checkLDiag(),"OXX");
    t.is(checkRDiag(),".XO");
    
    t.results();
  }
}