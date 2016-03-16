//Validates terminal input and returns pointer to the cell being referenced
//(if coordinates are valid - otherwise returns null)
class Validator
{
  private Tester t = new Tester();
  private boolean valid = false;

  boolean valid() { return valid; }

  Cell validate(String s, Board b)
  {
    if (isValidInput(s, b.rowNum(), b.colNum())
    &&  isBlankCell(b.grid()[row(s)][col(s)])) {
      valid = true;
      return b.grid()[row(s)][col(s)];
    }

    valid = false;
    return null;
  }

  private boolean isValidInput(String s, int rownum, int colnum)
  {
    if (isValidCol(s, colnum) && isValidRow(s, rownum) ) return true;

    System.out.println("Invalid move: format is <row><column>, such as 'a1'.");
    return false;
  }

  private boolean isBlankCell(Cell c)
  {
    if (c.value() == Player.NONE.mark()) return true;

    System.out.println("Invalid move: That cell is already taken.");
    return false;
  }

  //converts a letter to the corresponding row number
  private int row(String s)
  {
    if (s == null) throw new Error("Bad string");

    return s.charAt(0) - 'a';
  }

  private int col(String s)
  {
    if (s == null) throw new Error("Bad string");

    return Character.getNumericValue(s.charAt(1)) - 1;
  }

  private boolean isValidRow(String s, int rownum)
  {
    if (!isCorrectLength(s)) return false;

    char c = s.charAt(0);
    if (c - 'a' < rownum && Character.isLowerCase(c)) return true;

    return false;
  }

  private boolean isValidCol(String s, int colnum)
  {
    if (!isCorrectLength(s)) return false;

    char c = s.charAt(1);
    if (!Character.isDigit(c)) return false;

    int n = Character.getNumericValue(s.charAt(1));
    if (n <= colnum && n > 0) return true;

    return false;
  }

  private boolean isCorrectLength(String s)
  {
    if (s != null && s.length() == 2) return true;

    return false;
  }

  void test() {
    t.is(false, isCorrectLength("abc"));
    t.is(false, isCorrectLength("a"));
    t.is(true,  isCorrectLength("ab"));
    t.is(false, isValidCol("d4", 3));
    t.is(false, isValidCol("d0", 3));
    t.is(false, isValidCol("d13", 3));
    t.is(true,  isValidCol("a3", 3));
    t.is(true,  isValidCol("11", 3));
    t.is(true,  isValidCol("c1", 3));
    t.is(false, isValidRow("e3", 3));
    t.is(false, isValidRow("53", 3));
    t.is(false, isValidRow("a234", 3));
    t.is(true,  isValidRow("c1", 3));
    t.is(true,  isValidRow("b3", 3));

    t.results();
  }
}
