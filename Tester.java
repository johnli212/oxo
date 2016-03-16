class Tester
{
  private int tests = 0, passed = 0, failed = 0;
  
  // Test whether two objects or primitive values are equal.  If a test fails,
  // it prints the line number and class, but doesn't interrupt testing.
  void is(Object x, Object y) 
  {
    tests++;
    if (x == y || (x != null && x.equals(y))) {
      passed++;
    }
    else {
      failed++;
      StackTraceElement l = new Exception().getStackTrace()[1];
      System.out.println("Test in class " + l.getClassName() + " at line " 
                         + l.getLineNumber() + " failed !");
    }
  }
  
  //Prints number of tests passed out of total, as well as class being tested
  void results() 
  {
    StackTraceElement l = new Exception().getStackTrace()[1];
    System.out.println(l.getClassName() + " class tests passed: " + passed + 
                       " out of " + tests);
  }
}