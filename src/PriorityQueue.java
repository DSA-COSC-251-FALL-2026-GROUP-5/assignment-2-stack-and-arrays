class PriorityQueue<T extends Comparable<T>> {

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
   * - deletion is probably O(n), although, it could be done in O(log n) time if
   * we take note of the smallest element when insertion
   *
   * So in terms of time-complexity, we profit in terms of insertion (because
   * shifting takes O(n) time)
   *
   */

  Entry<T>[] maxHeap;
  Entry<T>[] minHeap;
  static final int maxHeapCompareSign = 1;
  static final int minHeapComapreSign = -1;
  int occupied;

  private static class Entry<T> {
    T item;
    int priorityValue;
    int indexInOtherHeap;

    Entry(T item, int priorityValue, int indexInOtherHeap) {
      this.item = item;
      this.priorityValue = priorityValue;
      this.indexInOtherHeap = indexInOtherHeap;
    }

    @Override
    public String toString() {
      return "Entry[value=" + item +
          ", priority=" + priorityValue +
          ", indexInOtherHeap=" + indexInOtherHeap + "]";
    }
  }

  @SuppressWarnings("unchecked")
  public PriorityQueue(int arraySize) {
    // 8.a this probably takes O(n) time to initialize
    maxHeap = new Entry[arraySize];
    minHeap = new Entry[arraySize];
    occupied = 0;
  }

  public void insert(T newItem, int priorityValue) {
    // we assume that the array is already a heap
    if (occupied == maxHeap.length) {
      // ... assume that min heap and max heap has the same length, so just check one
      // of them if they're overflowing
      maxHeap = CustomUtils.resizeArray(maxHeap);
      minHeap = CustomUtils.resizeArray(minHeap);
    }
    maxHeap[occupied] = new Entry<T>(newItem, priorityValue, occupied);
    minHeap[occupied] = new Entry<T>(newItem, priorityValue, occupied);
    occupied++;

    if (occupied <= 1) {
      return;
    }
    percolateUp(maxHeap, minHeap, maxHeapCompareSign, occupied - 1);
    percolateUp(minHeap, maxHeap, minHeapComapreSign, occupied - 1);
  }

  public void percolateUp(Entry<T>[] heap, Entry<T>[] otherHeap, int sign, int ptr) {
    // without loss of generality, we assume that sign is positive for now, and we
    // want to build a max heap.

    // assume that from the range [0, occupied - 2], the heap invariant is already
    // preserved.

    int parentPtr = (ptr - 1) / 2;
    while (ptr > 0 && sign * Integer.compare(heap[parentPtr].priorityValue, heap[ptr].priorityValue) < 0) {
      // this means that while the parent is of a smaller value than the child, then
      // we swap the two
      // every time we perform a swap, we must update the heap index of the other heap
      otherHeap[heap[parentPtr].indexInOtherHeap].indexInOtherHeap = ptr;
      otherHeap[heap[ptr].indexInOtherHeap].indexInOtherHeap = parentPtr;
      swap(heap, parentPtr, ptr);
      ptr = parentPtr;
      parentPtr = (parentPtr - 1) / 2;
    }
  }

  /*
   * public T remove() {
   * // NOTE: if we want to remove in O(log n) time, and if we assume that we will
   * // always be removing the element that's of smallest priority, we need to
   * keep
   * // track of the index of the value of the smallest priority so that we can
   * // immediately know what to remove for the maxHeap
   * }
   */

  public void siftDown(Entry<T>[] heap, int sign) {

  }

  public void swap(Entry<T>[] arr, int ptr1, int ptr2) {
    Entry<T> tmp = arr[ptr1];
    arr[ptr1] = arr[ptr2];
    arr[ptr2] = tmp;
  }

  public String toString() {
    return String.format(
        "max heap: %s\r\nmin heap: %s\r\n",
        CustomUtils.arrToString(maxHeap, occupied - 1),
        CustomUtils.arrToString(minHeap, occupied - 1));
  }

  public void display() {
    System.out.println(toString());
  }

}
