// more information can be found on github https://github.com/DSA-COSC-251-FALL-2026-GROUP-5/assignment-2-stack-and-arrays
class Queue<T extends Comparable<T>> {
  private T arr[];
  // by convention, state that queueHead is the next index to push to
  int queueHead = 0;
  // also by convention, state that queueTail is the next index to pop out of
  int queueTail = 0;
  int occupied = 0;
  // the fact that queueHead == queueTail means that our circular queue is empty

  @SuppressWarnings("unchecked")
  public Queue(int arraySize) {
    // 7.a. this probably takes O(n) time to instantiate
    arr = (T[]) new Comparable[arraySize];
  }

  public void insert(T newItem) {
    // 7.b. this takes O(1) time because, we only just need to insert it to the
    // front and
    // update the queueHead index
    if (occupied == arr.length) {
      // resizing will make queueHead = 0, so the resizing logic is going to be a bit
      // different
      resize(CustomUtils.getNextResize(arr));
    }
    // so by convention, queueHead is already pointing at the next index we need to
    // insert
    arr[queueHead] = newItem;
    queueHead = (queueHead + 1 + arr.length) % arr.length;
    occupied++;
    // everytime we insert, we keep track of occupied to make the logic a bit easier
  }

  public T remove() {
    // 7.c. to remove, we just update one index and then access one item in the
    // array so it should take O(1)
    // by convention, queueTail contains the next index to pop
    if (occupied == 0) {
      return null;
    }
    T toPop = arr[queueTail];
    arr[queueTail] = null;
    queueTail = (queueTail + 1 + arr.length) % arr.length;
    occupied--;
    return toPop;
  }

  public T peekFront() {
    // 7.d this takes O(1) because it's just indexing an array
    if (occupied == 0) {
      return null;
    }
    // queueTail already contains the next index to pop so we can just return that
    return arr[queueTail];

  }

  public T peekRear() {
    // 7.e this takes O(1) also because it's just indexing an array

    if (occupied == 0) {
      return null;
    }

    // the queueHead contains the next index to insert, as such, to peekFront, we
    // must get the index one less than it
    // we add an arr.length because the modulo sometimes is negative for some reason
    // in java, so we'll be constantly positive
    int frontIndex = (queueHead - 1 + arr.length) % arr.length;
    return arr[frontIndex];

  }

  public String toString() {
    // 7.f. to string loops through every index of the array, as such it takes O(n)
    // time
    String returnString = "[";
    int ptr = queueTail;
    for (int i = 0; i < occupied; i++) {
      if (i != 0) {
        returnString = returnString.concat(" ");
      }
      returnString = returnString.concat(arr[ptr] == null ? "null" : arr[ptr].toString());
      if (i != occupied - 1) {
        returnString = returnString.concat(",");
      }
      ptr = (ptr + 1 + arr.length) % arr.length;
    }
    returnString = returnString.concat("]");
    return returnString;
  }

  public void display() {
    // 7.g. display calls toString which takes O(n) time, therefore, it's O(n) since
    // the other prints takes place in relatively constant time
    System.out.printf("Current Queue: %s\r\n", toString());
    System.out.printf("queueHead: %d\r\n", queueHead);
    System.out.printf("queueTail: %d\r\n", queueTail);
    System.out.printf("occupied: %d\r\n", occupied);
  }

  @SuppressWarnings("unchecked")
  private void resize(int newSize) {
    // resize. Assume that we always resize the queue to a bigger queue
    T newArr[] = (T[]) new Comparable[newSize];
    int ptr = queueTail;

    // make occupied be the source of truth of how big our queue is
    for (int i = 0; i < occupied; i++) {
      newArr[i] = arr[ptr];
      ptr = (ptr + 1 + arr.length) % arr.length;
    }

    arr = newArr;
    queueTail = 0;
    queueHead = occupied;
  }
}
