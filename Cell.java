// A cell represents a position on the board.
class Cell
{
  private char value;
  Tester t = new Tester();
  
  char value() { return value; }
  
  void update(char value) { this.value = value; }
  
  Cell()
  {
    value = Player.NONE.mark();
  }
  
  void test() {
    t.is(value(), '.');
    
    t.results();
  }
}