class PriorityQueue<T extends Comparable<T>> {

  // this array is only internally used
  private T arr[];
  private int popIndex;
  // popPtr by convention contains the next index to pop out of our stack
  // as such, if popPtr == arr.length - 1, our stack is full
  // we also want to resize the array too.

  /*
   * NOTE: for the priority queue, we will internally store a min-heap and a
   * max-heap, which has the following property:
   * - insertion can be done in O(log n) time (which is better than using an
   * ordered array which is O(n))
   * - peekFront can be done in O(1) time (just like an ordered array)
   * - peekRear can be done in O(1) time (just like an ordered array)
   *
   * So in terms of time-complexity, we profit in terms of insertion (because
   * shifting takes O(n) time)
   *
   */
  @SuppressWarnings("unchecked")
  public PriorityQueue(int arraySize) {
    // 8.a this probably takes O(n) time to initialize
    this.arr = (T[]) new Comparable[arraySize];
    popIndex = -1;
  }

  public void insert(T newItem, int priorityValue) {

  }

}
