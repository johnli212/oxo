public enum Player
{
  P1  ('X',1,"The winner is Player 1"), 
  P2  ('O',2,"The winner is Player 2"), 
  NONE('.',0,"The game was a draw...");
  
  private char mark;
  private int num;
  private String winmsg;
  
  public char mark() { return mark; }
  public int num() { return num; }
  public String winmsg() { return winmsg; }
  
  private Player(char mark, int num, String winmsg)
  {
    this.mark = mark;
    this.num = num;
    this.winmsg = winmsg;
  }
}