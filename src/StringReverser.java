class StringReverser {
  public static String reverse(String input) {
    // 9.a. this takes O(n) time where n is the size of the string becuase it takes
    // n amount of time to push to the stack one by one, and also takes n amount of
    // time to pop it and concat it one by one

    // we first create a stack of String
    Stack<Character> stringStack = new Stack<Character>(input.length());
    for (int i = 0; i < input.length(); i++) {
      // we push it into the stack one by one
      stringStack.push(input.charAt(i));
    }

    String reversedString = "";
    for (int i = 0; i < input.length(); i++) {
      // then we pop it one by one while concatenating it to our output
      reversedString = reversedString.concat(stringStack.pop().toString());
    }
    return reversedString;
  }
}
