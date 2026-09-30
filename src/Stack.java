class Stack<T extends Comparable<T>> {
  // this array is only internally used
  private T arr[];
  private int popIndex;
  // popPtr by convention contains the next index to pop out of our stack
  // as such, if popPtr == arr.length - 1, our stack is full
  // we also want to resize the array too.

  @SuppressWarnings("unchecked")
  public Stack(int arraySize) {
    // NOTE: the generic type T must extend the Comparable class
    this.arr = (T[]) new Comparable[arraySize];
    popIndex = -1;
  }

  public void push(T newItem) {
    // resize the array automatically when it becomes full to avoid an out of bound
    // error
    if (popIndex == arr.length - 1) {
      arr = CustomUtils.resizeArray(arr);
    }
    popIndex++;
    arr[popIndex] = newItem;
  }

  public T pop() {
    if (popIndex == -1) {
      return null;
    }
    T returnItem = arr[popIndex];
    popIndex--;
    return returnItem;
  }

  public T peek() {
    if (popIndex == -1) {
      return null;
    }
    T returnItem = arr[popIndex];
    return returnItem;
  }

  public String toString() {
    String returnString = "[";
    for (int i = 0; i <= popIndex; i++) {
      if (i != 0) {
        returnString = returnString.concat(" ");
      }
      returnString = returnString.concat(arr[i].toString());
      if (i != popIndex) {
        returnString = returnString.concat(",");
      }
    }
    returnString = returnString.concat("]");
    return returnString;
  }

  public void display() {
    System.out.printf("Current stack: %s\r\n", toString());
    System.out.printf("popIndex: %d\r\n", popIndex);
    System.out.printf("arr.length: %d\r\n", arr.length);
  }
}
