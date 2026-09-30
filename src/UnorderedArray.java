public class UnorderedArray<T extends Comparable<T>> extends GenericArray<T> {
  UnorderedArray(int size) {
    super(size);
  }

  @Override
  void insert(T x) {
    // 3.1. insertion simply inserts it at the end, which should take O(1) because
    // we're keeping track of the occupiedAmount pointer, so we resize if it's going
    // to overflow
    if (occupiedAmount == capacity) {
      resize(nextResizeAmount(capacity));
    }
    arr[occupiedAmount] = x;
    occupiedAmount++;
  }

  @Override
  int find(T x) {
    // 5.1. find using an unordered array requires linear search, which requires
    // O(n) time complexity
    for (int i = 0; i < occupiedAmount; i++) {
      if (arr[i].compareTo(x) == 0) {
        // we find the first occurance
        return i;
      }
    }
    // if we've searched through the whole occupied space and haven't returned, then
    // we haven't found it.
    return -1;
  }
}
