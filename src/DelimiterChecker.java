
class DelimiterChecker {
  public static boolean check(String input) {
    // create a stack of size 5 for now and trust that resizing works
    Stack<Character> delimiterStack = new Stack<Character>(5);
    for (int i = 0; i < input.length(); i++) {
      switch (input.charAt(i)) {
        // we push the expected closing paranthesis
        case '(':
          delimiterStack.push(')');
          break;
        case '[':
          delimiterStack.push(']');
          break;
        case '{':
          delimiterStack.push('}');
          break;

        // if the closing paranthesis isn't what we expect it to be, then it's not
        // balanced
        case ')':
          if (delimiterStack.pop() != ')') {
            return false;
          }
          break;
        case ']':
          if (delimiterStack.pop() != ']') {
            return false;
          }
          break;
        case '}':
          if (delimiterStack.pop() != '}') {
            return false;
          }
          break;
      }
    }

    // if the stack isn't empty, then it's not balanced
    return delimiterStack.peek() == null;
  }
}
