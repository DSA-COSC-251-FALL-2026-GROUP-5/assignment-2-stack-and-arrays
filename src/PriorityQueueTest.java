/**
 * Unit tests for PriorityQueue.java, based on the requirements in
 * assignment_details.pdf (section 8: PriorityQueue Class).
 *
 * Reminder from the assignment: a smaller priorityValue means higher
 * priority, and items must be removed based on priority.
 *
 * These tests use Java assert statements, so they must be run with
 * assertions enabled:
 *
 *   ./build && ./run_test PriorityQueueTest
 */
import java.util.Random;
import java.util.TreeMap;

class PriorityQueueTest {
  private static int passed = 0;
  private static int failed = 0;

  private static void check(boolean condition, String message) {
    assert condition : message;
  }

  private static void runTest(String name, Runnable test) {
    try {
      test.run();
      passed++;
      System.out.printf("PASS: %s%n", name);
    } catch (AssertionError | RuntimeException e) {
      failed++;
      System.out.printf("FAIL: %s -- %s%n", name, e.getMessage());
    }
  }

  public static void main(String[] args) {
    boolean assertsEnabled = false;
    assert assertsEnabled = true;
    if (!assertsEnabled) {
      System.out.println(
          "WARNING: assertions are disabled, every check will silently pass.");
      System.out.println("Run with: ./run_test PriorityQueueTest");
    }

    runTest("empty priority queue returns null",
        PriorityQueueTest::testEmptyReturnsNull);
    runTest("remove returns smallest priorityValue first",
        PriorityQueueTest::testRemoveSmallestPriorityFirst);
    runTest("peekFront and peekRear",
        PriorityQueueTest::testPeekFrontAndRear);
    runTest("peekFront and peekRear after removals",
        PriorityQueueTest::testPeekFrontRearAfterRemovals);
    runTest("insertion order does not matter",
        PriorityQueueTest::testInsertionOrderDoesNotMatter);
    runTest("ascending priority insertion",
        PriorityQueueTest::testAscendingPriorityInsertion);
    runTest("descending priority insertion",
        PriorityQueueTest::testDescendingPriorityInsertion);
    runTest("auto resize", PriorityQueueTest::testAutoResize);
    runTest("interleaved insert and remove",
        PriorityQueueTest::testInterleavedInsertRemove);
    runTest("alternating insert and remove",
        PriorityQueueTest::testAlternatingInsertRemove);
    runTest("single element", PriorityQueueTest::testSingleElement);
    runTest("duplicate priorities", PriorityQueueTest::testDuplicatePriorities);
    runTest("all identical priorities", PriorityQueueTest::testAllSamePriority);
    runTest("negative and zero priorities",
        PriorityQueueTest::testNegativeAndZeroPriorities);
    runTest("many elements with multiple resizes",
        PriorityQueueTest::testManyElements);
    runTest("large volume", PriorityQueueTest::testLargeVolume);
    runTest("reuse after emptying", PriorityQueueTest::testReuseAfterEmptying);
    runTest("toString", PriorityQueueTest::testToString);
    runTest("randomized test against a reference priority queue",
        PriorityQueueTest::testRandomizedAgainstOracle);
    runTest("generic with String items",
        PriorityQueueTest::testGenericWithStrings);
    runTest("display runs without errors", PriorityQueueTest::testDisplay);

    System.out.printf("%nPriorityQueueTest summary: %d passed, %d failed%n",
        passed, failed);
    if (failed > 0) {
      System.exit(1);
    }
  }

  // 8.c/8.d/8.e: remove(), peekFront() and peekRear() should return
  // null when the priority queue is empty
  private static void testEmptyReturnsNull() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(5);
    check(priorityQueue.remove() == null,
        "remove() should return null on an empty priority queue");
    check(priorityQueue.peekFront() == null,
        "peekFront() should return null on an empty priority queue");
    check(priorityQueue.peekRear() == null,
        "peekRear() should return null on an empty priority queue");
    check(priorityQueue.remove() == null,
        "remove() should still return null after repeated calls");
  }

  // section 8: a smaller priorityValue means higher priority, so
  // remove() must return the smallest priorityValue first
  private static void testRemoveSmallestPriorityFirst() {
    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(5);
    priorityQueue.insert("A", 5);
    priorityQueue.insert("B", 1);
    priorityQueue.insert("C", 3);
    check(priorityQueue.remove().equals("B"),
        "remove() should return the item with priorityValue 1 first");
    check(priorityQueue.remove().equals("C"),
        "remove() should return the item with priorityValue 3 next");
    check(priorityQueue.remove().equals("A"),
        "remove() should return the item with priorityValue 5 last");
    check(priorityQueue.remove() == null,
        "remove() should return null once the priority queue is empty");
  }

  // 8.d/8.e: peekFront() is the next item to be removed (smallest
  // priorityValue) and peekRear() is the last (largest priorityValue);
  // peeking must not remove anything
  private static void testPeekFrontAndRear() {
    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(5);
    priorityQueue.insert("low", 10);
    priorityQueue.insert("high", 1);
    priorityQueue.insert("mid", 5);
    check(priorityQueue.peekFront().equals("high"),
        "peekFront() should return the smallest priorityValue item");
    check(priorityQueue.peekRear().equals("low"),
        "peekRear() should return the largest priorityValue item");
    check(priorityQueue.peekFront().equals("high"),
        "peekFront() should not remove the item");
    check(priorityQueue.peekRear().equals("low"),
        "peekRear() should not remove the item");
    check(priorityQueue.remove().equals("high"),
        "remove() should still return the highest priority item after peeking");
    check(priorityQueue.remove().equals("mid"),
        "remove() should return the middle priority item next");
    check(priorityQueue.remove().equals("low"),
        "remove() should return the lowest priority item last");
  }

  // peekFront() and peekRear() must stay correct as items are removed
  // and new ones are inserted
  private static void testPeekFrontRearAfterRemovals() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(5);
    priorityQueue.insert(30, 30);
    priorityQueue.insert(10, 10);
    priorityQueue.insert(20, 20);
    priorityQueue.insert(40, 40);
    priorityQueue.remove(); // removes 10
    check(priorityQueue.peekFront() == 20,
        "peekFront() should return 20 after removing the smallest item");
    check(priorityQueue.peekRear() == 40,
        "peekRear() should still return 40");
    priorityQueue.remove(); // removes 20
    check(priorityQueue.peekFront() == 30,
        "peekFront() should return 30 after removing 20");
    check(priorityQueue.peekRear() == 40,
        "peekRear() should still return 40");
    priorityQueue.insert(5, 5);
    check(priorityQueue.peekFront() == 5,
        "peekFront() should return 5 after inserting a higher priority item");
    priorityQueue.insert(50, 50);
    check(priorityQueue.peekRear() == 50,
        "peekRear() should return 50 after inserting a lower priority item");
    priorityQueue.remove(); // removes 5
    priorityQueue.remove(); // removes 30
    priorityQueue.remove(); // removes 40
    check(priorityQueue.peekFront() == 50,
        "peekFront() should return 50 when only one item remains");
    check(priorityQueue.peekRear() == 50,
        "peekRear() should return 50 when only one item remains");
    priorityQueue.remove(); // removes 50
    check(priorityQueue.peekFront() == null,
        "peekFront() should return null when empty");
    check(priorityQueue.peekRear() == null,
        "peekRear() should return null when empty");
  }

  // removal order must depend on priority, not insertion order
  private static void testInsertionOrderDoesNotMatter() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(10);
    priorityQueue.insert(50, 5);
    priorityQueue.insert(10, 1);
    priorityQueue.insert(40, 4);
    priorityQueue.insert(20, 2);
    priorityQueue.insert(30, 3);
    for (int expected = 10; expected <= 50; expected += 10) {
      check(priorityQueue.remove() == expected,
          "remove() should return " + expected
              + " based on priority, not insertion order");
    }
  }

  private static void testAscendingPriorityInsertion() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(5);
    for (int i = 0; i < 10; i++) {
      priorityQueue.insert(i, i); // already sorted by priority
    }
    for (int expected = 0; expected < 10; expected++) {
      check(priorityQueue.remove() == expected,
          "ascending insertion test: remove() should return " + expected);
    }
    check(priorityQueue.remove() == null,
        "ascending insertion test: remove() should return null when empty");
  }

  private static void testDescendingPriorityInsertion() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(5);
    for (int i = 9; i >= 0; i--) {
      priorityQueue.insert(i, i); // inserted in reverse priority order
    }
    for (int expected = 0; expected < 10; expected++) {
      check(priorityQueue.remove() == expected,
          "descending insertion test: remove() should return " + expected);
    }
    check(priorityQueue.remove() == null,
        "descending insertion test: remove() should return null when empty");
  }

  // project requirement 3: the internal arrays should resize
  // automatically when they become full
  private static void testAutoResize() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(2);
    // item i gets priority 9 - i, so item 9 is the highest priority
    for (int i = 0; i < 10; i++) {
      priorityQueue.insert(i, 9 - i);
    }
    check(priorityQueue.peekFront() == 9,
        "peekFront() should return the smallest priorityValue item "
            + "after resizing");
    check(priorityQueue.peekRear() == 0,
        "peekRear() should return the largest priorityValue item "
            + "after resizing");
    for (int expected = 9; expected >= 0; expected--) {
      check(priorityQueue.remove() == expected,
          "remove() should return items in ascending priority order after "
              + "resizing (expected " + expected + ")");
    }
    check(priorityQueue.remove() == null,
        "remove() should return null after emptying a resized priority queue");
  }

  private static void testInterleavedInsertRemove() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);
    priorityQueue.insert(5, 5);
    priorityQueue.insert(1, 1);
    check(priorityQueue.remove() == 1,
        "remove() should return the item with priorityValue 1");
    priorityQueue.insert(0, 0);
    priorityQueue.insert(9, 9);
    check(priorityQueue.remove() == 0,
        "remove() should return the item with priorityValue 0");
    check(priorityQueue.remove() == 5,
        "remove() should return the item with priorityValue 5");
    check(priorityQueue.remove() == 9,
        "remove() should return the item with priorityValue 9");
    check(priorityQueue.remove() == null,
        "remove() should return null when empty");
  }

  private static void testAlternatingInsertRemove() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);
    priorityQueue.insert(10, 10);
    priorityQueue.insert(2, 2);
    check(priorityQueue.remove() == 2,
        "alternating test: remove() should return 2");
    priorityQueue.insert(1, 1);
    check(priorityQueue.remove() == 1,
        "alternating test: remove() should return 1");
    check(priorityQueue.remove() == 10,
        "alternating test: remove() should return 10");
    check(priorityQueue.remove() == null,
        "alternating test: remove() should return null when empty");
    priorityQueue.insert(7, 7);
    priorityQueue.insert(3, 3);
    check(priorityQueue.remove() == 3,
        "alternating test: remove() should return 3");
    priorityQueue.insert(2, 2);
    check(priorityQueue.remove() == 2,
        "alternating test: remove() should return 2");
    priorityQueue.insert(9, 9);
    check(priorityQueue.remove() == 7,
        "alternating test: remove() should return 7");
    check(priorityQueue.remove() == 9,
        "alternating test: remove() should return 9");
    check(priorityQueue.remove() == null,
        "alternating test: remove() should return null when empty");
  }

  private static void testSingleElement() {
    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(2);
    priorityQueue.insert("only", 42);
    check(priorityQueue.peekFront().equals("only"),
        "peekFront() should return the only item");
    check(priorityQueue.peekRear().equals("only"),
        "peekRear() should return the only item");
    check(priorityQueue.remove().equals("only"),
        "remove() should return the only item");
    check(priorityQueue.remove() == null,
        "remove() should return null after removing the only item");
    check(priorityQueue.peekFront() == null,
        "peekFront() should return null when empty");
    check(priorityQueue.peekRear() == null,
        "peekRear() should return null when empty");
  }

  // with duplicate priorityValues, any order among the duplicates is
  // acceptable, but the overall removal order must be non-decreasing
  // in priorityValue
  private static void testDuplicatePriorities() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);
    int[] priorities = {5, 3, 5, 1, 3, 1, 4};
    for (int priority : priorities) {
      priorityQueue.insert(priority, priority);
    }
    int removed = 0;
    int previous = Integer.MIN_VALUE;
    while (removed < priorities.length) {
      Integer item = priorityQueue.remove();
      check(item != null, "remove() should return every inserted item");
      if (item == null) {
        break;
      }
      check(item >= previous,
          "remove() should return items in non-decreasing priority order "
              + "even with duplicate priorities (got " + item
              + " after " + previous + ")");
      previous = item;
      removed++;
    }
    check(priorityQueue.remove() == null, "remove() should return null when empty");
  }

  // when every item has the same priority, every item must still be
  // returned exactly once, in any order
  private static void testAllSamePriority() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);
    int n = 10;
    for (int i = 0; i < n; i++) {
      priorityQueue.insert(i, 7);
    }
    boolean[] seen = new boolean[n];
    int removed = 0;
    while (removed < n) {
      Integer item = priorityQueue.remove();
      check(item != null,
          "all identical priorities test: remove() should return every "
              + "inserted item");
      if (item == null) {
        break;
      }
      check(item >= 0 && item < n,
          "all identical priorities test: remove() returned an unexpected "
              + "item " + item);
      check(!seen[item],
          "all identical priorities test: remove() returned item " + item
              + " more than once");
      seen[item] = true;
      removed++;
    }
    check(priorityQueue.remove() == null,
        "all identical priorities test: remove() should return null when empty");
  }

  private static void testNegativeAndZeroPriorities() {
    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(5);
    priorityQueue.insert("zero", 0);
    priorityQueue.insert("negative", -5);
    priorityQueue.insert("positive", 7);
    check(priorityQueue.remove().equals("negative"),
        "remove() should handle negative priorityValues");
    check(priorityQueue.remove().equals("zero"),
        "remove() should handle a priorityValue of 0");
    check(priorityQueue.remove().equals("positive"),
        "remove() should return the largest priorityValue last");
  }

  // inserting 25 items into an initial capacity of 3 forces several
  // resizes (3 -> 6 -> 12 -> 24 -> 48)
  private static void testManyElements() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);
    int n = 25;
    for (int i = 0; i < n; i++) {
      priorityQueue.insert(i, n - 1 - i); // descending priority order
    }
    for (int expected = n - 1; expected >= 0; expected--) {
      check(priorityQueue.remove() == expected,
          "remove() should return " + expected + " in ascending priority order");
    }
    check(priorityQueue.remove() == null, "remove() should return null when empty");
  }

  // stress the resize logic and the heap invariant with many items and
  // many resizes (3 -> 6 -> ... -> 1536)
  private static void testLargeVolume() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);
    int n = 1000;
    int[] priorities = new int[n];
    for (int i = 0; i < n; i++) {
      priorities[i] = i;
    }
    // shuffle so that the insertion order is unrelated to the priority
    Random random = new Random(42);
    for (int i = n - 1; i > 0; i--) {
      int j = random.nextInt(i + 1);
      int temp = priorities[i];
      priorities[i] = priorities[j];
      priorities[j] = temp;
    }
    for (int i = 0; i < n; i++) {
      priorityQueue.insert(priorities[i], priorities[i]);
    }
    for (int expected = 0; expected < n; expected++) {
      check(priorityQueue.remove() == expected,
          "large volume test: remove() should return " + expected
              + " in ascending priority order");
    }
    check(priorityQueue.remove() == null,
        "large volume test: remove() should return null when empty");
  }

  // the priority queue should behave correctly after being completely
  // emptied
  private static void testReuseAfterEmptying() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(2);
    priorityQueue.insert(2, 2);
    priorityQueue.insert(1, 1);
    check(priorityQueue.remove() == 1, "reuse test: remove() should return 1");
    check(priorityQueue.remove() == 2, "reuse test: remove() should return 2");
    check(priorityQueue.remove() == null,
        "reuse test: remove() should return null when empty");
    priorityQueue.insert(5, 5);
    priorityQueue.insert(3, 3);
    check(priorityQueue.peekFront() == 3,
        "reuse test: peekFront() should return 3 after reuse");
    check(priorityQueue.remove() == 3, "reuse test: remove() should return 3");
    check(priorityQueue.remove() == 5, "reuse test: remove() should return 5");
    check(priorityQueue.remove() == null,
        "reuse test: remove() should return null when empty");
  }

  // 8.f: toString should describe both heaps
  private static void testToString() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);
    priorityQueue.insert(7, 7);
    String result = priorityQueue.toString();
    check(result.contains("max heap:"),
        "toString() should describe the max heap");
    check(result.contains("min heap:"),
        "toString() should describe the min heap");
    check(result.contains("7"),
        "toString() should contain the inserted item");
  }

  // differential test: perform random insert/remove operations and
  // compare the results against a TreeMap (which keeps the items
  // sorted by priority) after every step. Priorities are unique, so
  // the expected order is unambiguous.
  private static void testRandomizedAgainstOracle() {
    TreeMap<Integer, Integer> oracle = new TreeMap<Integer, Integer>();
    Random random = new Random(2026);
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);
    // a shuffled range of unique priorities
    int[] priorities = new int[1000];
    for (int i = 0; i < priorities.length; i++) {
      priorities[i] = i;
    }
    for (int i = priorities.length - 1; i > 0; i--) {
      int j = random.nextInt(i + 1);
      int temp = priorities[i];
      priorities[i] = priorities[j];
      priorities[j] = temp;
    }
    int insertCount = 0;
    int nextValue = 0;
    for (int step = 0; step < 500; step++) {
      boolean insert = oracle.isEmpty() || random.nextInt(100) < 55;
      if (insert) {
        int priority = priorities[insertCount];
        insertCount++;
        oracle.put(priority, nextValue);
        priorityQueue.insert(nextValue, priority);
        nextValue++;
      } else {
        Integer expected = oracle.pollFirstEntry().getValue();
        Integer actual = priorityQueue.remove();
        check(actual != null && actual.equals(expected),
            "randomized test: remove() should return " + expected
                + " but returned " + actual);
      }
      if (oracle.isEmpty()) {
        check(priorityQueue.peekFront() == null,
            "randomized test: peekFront() should return null when empty");
        check(priorityQueue.peekRear() == null,
            "randomized test: peekRear() should return null when empty");
      } else {
        Integer expectedFront = oracle.firstEntry().getValue();
        Integer actualFront = priorityQueue.peekFront();
        check(actualFront != null && actualFront.equals(expectedFront),
            "randomized test: peekFront() should return " + expectedFront
                + " but returned " + actualFront);
        Integer expectedRear = oracle.lastEntry().getValue();
        Integer actualRear = priorityQueue.peekRear();
        check(actualRear != null && actualRear.equals(expectedRear),
            "randomized test: peekRear() should return " + expectedRear
                + " but returned " + actualRear);
      }
    }
    while (!oracle.isEmpty()) {
      Integer expected = oracle.pollFirstEntry().getValue();
      Integer actual = priorityQueue.remove();
      check(actual != null && actual.equals(expected),
          "randomized test: remove() should return " + expected
              + " but returned " + actual);
    }
    check(priorityQueue.remove() == null,
        "randomized test: remove() should return null when empty");
  }

  // project requirement 1: the class must be generic
  private static void testGenericWithStrings() {
    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(2);
    priorityQueue.insert("pear", 3);
    priorityQueue.insert("apple", 1);
    priorityQueue.insert("banana", 2); // forces a resize
    check(priorityQueue.peekFront().equals("apple"),
        "peekFront() should return \"apple\" (priorityValue 1)");
    check(priorityQueue.peekRear().equals("pear"),
        "peekRear() should return \"pear\" (priorityValue 3)");
    check(priorityQueue.remove().equals("apple"),
        "remove() should return \"apple\"");
    check(priorityQueue.remove().equals("banana"),
        "remove() should return \"banana\"");
    check(priorityQueue.remove().equals("pear"),
        "remove() should return \"pear\"");
    check(priorityQueue.remove() == null,
        "remove() should return null when empty");
  }

  // 8.g: display() should run without errors
  private static void testDisplay() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);
    priorityQueue.insert(2, 2);
    priorityQueue.insert(1, 1);
    priorityQueue.display();
    priorityQueue.remove();
    priorityQueue.display();
  }
}
