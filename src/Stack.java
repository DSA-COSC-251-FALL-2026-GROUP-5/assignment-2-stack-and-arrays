class Stack<T extends Comparable<T>> {
  // this array is only internally used
  private T arr[];
  private int popIndex;
  // popPtr by convention contains the next index to pop out of our stack
  // as such, if popPtr == arr.length - 1, our stack is full
  // we also want to resize the array too.

  @SuppressWarnings("unchecked")
  public Stack(int arraySize) {
    // 6.a this probably takes O(n) time to initialize
    this.arr = (T[]) new Comparable[arraySize];
    popIndex = -1;
  }

  public void push(T newItem) {
    // 6.b Without resizing, it clearly takes O(1) because we only need to index and
    // write to an array, and then increment a value, however, if we had to resize,
    // then the analysis
    // gets a bit more complicated
    //
    // if we resize twice the array size, then to push n elements,
    // it's approximately:
    //
    // O(n + 1 + 2 + 4 + 8 + ... + n)
    // So if we assume that n is a power of 2, then we have:
    //
    // n = 2^k
    //
    // and O(n + 1 + 2 + 4 + 8 + ... + 2^k)
    //
    // So get a closed form for k, define:
    //
    // A = 1 + 2 + 4 + 8 + ... + 2^k
    //
    // So:
    //
    // 2A = 2 + 4 + 8 + ... + 2^(k+1)
    //
    // Meaning:
    //
    // A = 2^(k + 1) - 1
    // A = 2n - 1
    //
    // that to push n elements, we need O(n + 2n - 1) = O(n)
    //
    // As such, to push one element, on average, it would still take O(1) time even
    // with resizing, or the techinical term for it is that it takes O(1) amortized
    if (popIndex == arr.length - 1) {
      arr = CustomUtils.resizeArray(arr);
    }
    popIndex++;
    arr[popIndex] = newItem;
  }

  public T pop() {
    // 6.c pop requires O(1) time because we just need to index the array and
    // decrement a value
    if (popIndex == -1) {
      return null;
    }
    T returnItem = arr[popIndex];
    arr[popIndex] = null;
    popIndex--;
    return returnItem;
  }

  public T peek() {
    // 6.d peek also requires O(1) time because it's just one indexing
    if (popIndex == -1) {
      return null;
    }
    T returnItem = arr[popIndex];
    return returnItem;
  }

  public String toString() {
    // 6.e this takes O(n) time because we loop through the array
    return CustomUtils.arrToString(arr, popIndex);
  }

  public void display() {
    // 6.f this takes O(n) time because it calls toString is O(n), and toString
    // takes o(n) time
    System.out.printf("Current stack: %s\r\n", toString());
    System.out.printf("popIndex: %d\r\n", popIndex);
    System.out.printf("arr.length: %d\r\n", arr.length);
  }
}
