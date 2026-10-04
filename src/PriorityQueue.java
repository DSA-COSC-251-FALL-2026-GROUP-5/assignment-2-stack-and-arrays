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
    // 8.b. insertion takes O(log(n)) time because although we're inserting into 2
    // heaps, O(2 log(n)) = O(log(n)). The reason why insertion into heaps cost
    // O(log(n)) time is because percolate up will swap at most d or so times where
    // d is the depth of the binary tree and d = log(n) as such, it takes O(d) =
    // O(log n)

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

  public T remove() {
    // 8.c removal takes O(log n) time because an arbitrary removal from a heap
    // takes O(log(n)) time as long as you know the index you want to remove from
    // beforehand
    // NOTE: if we want to remove in O(log n) time, and if we only want to remove
    // the item with the smallest priority, we need to know where that is stored in
    // the max heap too
    // to do this, we store a cross reference pointer from one heap to the other
    // heap

    // the first step is to simply store the return value as the first item in the
    // minHeap

    if (occupied == 0) {
      return null;
    }

    Entry<T> entryToRemove = minHeap[0];

    // here comes the hard part. Essentially, for the minHeap, we take the last
    // element just perform a siftDown from the root after
    minHeap[0] = minHeap[occupied - 1];
    maxHeap[minHeap[0].indexInOtherHeap].indexInOtherHeap = 0;
    minHeap[occupied - 1] = null;
    occupied--; // we subtract beforehand because we assume that siftDown will probably use it
                // as a stop condition
    siftDown(minHeap, maxHeap, minHeapComapreSign, 0);
    // however, now we need to syncrhonize it with the maxHeap. We can delete an
    // arbitrary element in a heap in O(log n) time as long as you know the index of
    // what to delete beforehand.

    int deletionIndexMaxHeap = entryToRemove.indexInOtherHeap;
    maxHeap[deletionIndexMaxHeap] = maxHeap[occupied];
    minHeap[maxHeap[deletionIndexMaxHeap].indexInOtherHeap].indexInOtherHeap = deletionIndexMaxHeap;
    maxHeap[occupied] = null;
    // now we check whether we should siftDown or percolateUp from here

    if (occupied <= 1) {
      return entryToRemove.item;
    }

    // now we need to repair the invariant on the maxHeap
    int parentPtr = (deletionIndexMaxHeap - 1) / 2;
    if (maxHeapCompareSign
        * Integer.compare(maxHeap[parentPtr].priorityValue, maxHeap[deletionIndexMaxHeap].priorityValue) < 0) {
      // if the element of the parent is less than the element we swapped, then we
      // need to
      // percolateUp because, if we assume that the heap was valid before the swap,
      // then what we currently have is greater than the siblings and everything below
      // it
      percolateUp(maxHeap, minHeap, maxHeapCompareSign, deletionIndexMaxHeap);
    } else {
      // if the element of the parent is greater or equals to it, then maybe siftDown,
      // just in case that its children is bigger than it
      siftDown(maxHeap, minHeap, maxHeapCompareSign, deletionIndexMaxHeap);
    }
    return entryToRemove.item;
  }

  public void siftDown(Entry<T>[] heap, Entry<T>[] otherHeap, int sign, int ptr) {
    // without loss of generally, assume that we have a maxHeap with positive sign
    // to reason about this
    // we stop when our leftChildPtr is greater than or equals to occupied
    while (true) {
      // so if this is a max heap, we want the bigger value in order to swap
      int leftChildPtr = 2 * ptr + 1;

      if (leftChildPtr >= occupied) {
        break;
      }

      int childToSwapPtr;
      if (leftChildPtr + 1 >= occupied) {
        childToSwapPtr = leftChildPtr;
      } else if (sign * Integer.compare(heap[leftChildPtr].priorityValue, heap[leftChildPtr + 1].priorityValue) > 0) {
        childToSwapPtr = leftChildPtr;
      } else {
        childToSwapPtr = leftChildPtr + 1;
      }

      if (sign * Integer.compare(heap[ptr].priorityValue, heap[childToSwapPtr].priorityValue) >= 0) {
        // if what we have as parent is bigger than or equals to the biggest of our
        // children, we're done
        break;
      }

      // otherwise, we're going to need to swap it

      // we'll first update cross-reference in the otherHeap
      otherHeap[heap[childToSwapPtr].indexInOtherHeap].indexInOtherHeap = ptr;
      otherHeap[heap[ptr].indexInOtherHeap].indexInOtherHeap = childToSwapPtr;
      swap(heap, childToSwapPtr, ptr);
      ptr = childToSwapPtr;
    }
  }

  public void swap(Entry<T>[] arr, int ptr1, int ptr2) {
    Entry<T> tmp = arr[ptr1];
    arr[ptr1] = arr[ptr2];
    arr[ptr2] = tmp;
  }

  public T peekFront() {
    // 8.d. this takes O(1) time because it's accessing an array through an index
    // one time
    // NOTE: assume that the front of the queue is the first thing we will pop
    if (occupied == 0) {
      return null;
    }
    return minHeap[0].item;
  }

  public T peekRear() {
    // 8.e. this also takes O(1) time similar to peekFront

    if (occupied == 0) {
      return null;
    }

    return maxHeap[0].item;
  }

  public String toString() {
    // 8.f. this takes O(n) time because it needs to loop through every element in
    // the
    // heap
    return String.format(
        "max heap: %s\r\nmin heap: %s\r\n",
        CustomUtils.arrToString(maxHeap, occupied - 1),
        CustomUtils.arrToString(minHeap, occupied - 1));
  }

  public void display() {
    // 8.g. this takes O(n) time because to string takes O(n) time
    System.out.println(toString());
  }

}
