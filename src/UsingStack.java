import java.util.Arrays;

class UsingStack {
  public static void main(String[] args) {
    /*
     * Integer[] arr = new Integer[2];
     * Integer[] resizedArr = CustomUtils.resizeArray(arr);
     * System.out.println(Arrays.toString(arr));
     * System.out.println(Arrays.toString(resizedArr));
     */

    Stack<Integer> stack = new Stack<Integer>(2);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    stack.push(6);
    stack.push(7);
    stack.display();
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    stack.display();
  }
}
