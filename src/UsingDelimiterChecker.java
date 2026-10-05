class UsingDelimiterChecker {
  public static void main(String[] args) {
    System.out.println(DelimiterChecker.check(""));
    System.out.println(DelimiterChecker.check("()"));
    System.out.println(DelimiterChecker.check("[]"));
    System.out.println(DelimiterChecker.check("{}"));
    System.out.println(DelimiterChecker.check("[()]"));
    System.out.println(DelimiterChecker.check("{[()]}"));

    System.out.println(DelimiterChecker.check("{[(hello)]}"));

    System.out.println(DelimiterChecker.check("{[fooo()]}"));
    System.out.println(DelimiterChecker.check("{[fooo()]}bar"));
    System.out.println(DelimiterChecker.check("{[(]}"));
    System.out.println(DelimiterChecker.check("{[("));
    System.out.println(DelimiterChecker.check("{"));
  }
}
