# What to turn in

What to Turn In: This is a group assignment; each group should submit only once.

1. Your Stack Java class with all required methods and functionality. The file must be named Stack.java, and the class must be named Stack.

2. Your Queue Java class with all required methods and functionality. The file must be named Queue.java, and the class must be named Queue.

3. Your PriorityQueue Java class with all required methods and functionality. The file must be named PriorityQueue.java, and the class must be named PriorityQueue.

4. Your StringReverser Java class with all required methods and functionality. The file must be named StringReverser.java, and the class must be named StringReverser.

5. Your DelimiterChecker Java class with all required methods and functionality. The file must be named DelimiterChecker.java, and the class must be named DelimiterChecker.

6. Optional: any additional supporting Java classes.

7. Note: Do not submit a main class. The submitted classes must not include a main method.

# Project requirements

1. The Stack, Queue, and PriorityQueue classes must be declared as generic classes: Stack<T>, Queue<T>, and PriorityQueue<T>.

2. The Stack, Queue, and PriorityQueue classes must be implemented from scratch using arrays.

3. If the internal array becomes full, the class should automatically resize the array.

4. If pop(), remove(), peek(), peekFront(), or peekRear() is called when the structure is empty,
the method should return null.

5. Each required method must include a short comment stating its time complexity.

~~6. Stack Class (LIFO) Implement the following constructor and methods:~~

~~(a) public Stack(int arraySize)~~

~~(b) public void push(T newItem)~~

~~(c) public T pop()~~

~~(d) public T peek()~~

~~(e) public String toString()~~

~~(f) public void display()~~

7. Queue Class (FIFO) Implement the following constructor and methods:

~~(a) public Queue(int arraySize)~~

~~(b) public void insert(T newItem)~~

~~(c) public T remove()~~

~~(d) public T peekFront()~~

~~(e) public T peekRear()~~

~~(f) public String toString()~~

~~(g) public void display()~~

The Queue class may be implemented using a circular array or by shifting elements after removal.

8. PriorityQueue Class

Each item has an associated priorityValue. A smaller priorityValue means higher priority.
Items must be removed based on priority.

Implement the following constructor and methods:

~~(a) public PriorityQueue(int arraySize)~~

(b) public void insert(T newItem, int priorityValue)

(c) public T remove()

(d) public T peekFront()

(e) public T peekRear()

(f) public String toString()

(g) public void display()

9. StringReverser Class

Create a utility class that uses your Stack implementation and contains the following static method:

(a) public static String reverse(String input)

This method accepts a string and returns its reversed version.

10. DelimiterChecker Class

Create a utility class that uses your Stack implementation and contains the following static method:

(a) public static boolean check(String input)
This method validates the delimiters in a string and returns true if all delimiters match correctly.
Otherwise, it should return false. It should only handle the following delimiters: (), [], and {}.

# Notes

## Heaps

Heaps are binary trees that preserve the following invariants:

If we for a min-heap:

- the children of the parent must be of a value smaller than or equal to the parent

Similarly, for a max heap:

- the children of the parent must be of a value greater than or equal to the parent

Let's just talk about the min-heap, and then we can transfer that to the max-heap.

Let's also conceptually visualize it as a binary tree first, before implementing it as an array.

Suppose that we have an array. In order to turn it into a min-heap, we must perform an operation called "heapify" which goes like this:

- pop each one of the elements in the array one by one
- perform an insertion operation on each of those elements in the heap

Now that we've broken it down into a smaller operation called an insertion, we must talk about how to implement it.

To insert, we place the new element at the bottom-most, left-most open spot of the heap, and then perform something called percolate up.

Essentially, to do this, we compare whether the thing we've inserted is less than the parent, if yes, then we swap the parent and the child, and we continue this process until it's equals to or greater than the parent.

This essentially takes $O(log(n))$ time because, our heap is always balanced.

Now, if we want to pop the root element (which is guaranteed to be the minimum value of the whole structure), we swap the root element with the last element in the heap (if we're talking in the order to of top to down, left to right), and then we can safely pick out that minimum value. Next, we must now repair the invariant through what is essentially the reverse of the "percolate up" step, which means, we take teh root, and if it's bigger than one of the child, swap them, otherwise, do nothing and exit because the invariant is met.
