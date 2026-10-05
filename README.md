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

### Array representation

A binary tree can be represented using pointers and nodes, or in the case of the heap, the most common way to represent it is by using an array. 

How we present it as follows, if we assume that the tree must always be filled in a way such that it's left to right and top down, then every time we add to the tree, we simply append it to the end of the array.

Now we must derive the relationship between the parents and its children.

Given that we have a parent, how do we get to its children? If we know this, then we can do the inverse in order to get from the children to the parent as well.

Suppose that we have an arbitrary node at index $i$ (0th index). Now, if we append it left to right and top to down, how many nodes must we pass until we get to the node that is guaranteed to be the children of $i$? Well, if $i$ is at depth $d$ where $d = 0$ is at the root, then we say that $i$ can be represented as such: $i = 2^0 + 2^1 + 2^2 + \cdots + 2^{d - 1} + k_1$ for $d > 0$. And also, in the case that $d = 0$, $i = 0$. Essentially, we say that the current index is the same as the amount of nodes that was previous to it.

Where $k_1$ is going to be the number of nodes at depth $d$ before it.

And we say that $k_2$ is going to be the number of nodes after that node at index $i$ until we reach the end of depth $d$, that is:

$$
k_1 + 1 + k_2  = 2^d
$$

Now, until we get to the children of the node $i$, we must go through $k_2$ nodes, and also go through all of $k_1$ children, which is simply $2k_1$ because we say they all of children until we get the children of node $i$.

Now, this means that its first child (left most child) will be at exactly node:

$$
i_{1} = i + 1 + k_2 + 2k_1
$$

Now we we just perform some algebraic manipulation:

$$
k_2  = 2^d - k_1 - 1
$$

So:

$$
i_1 = i + 1 + 2^d - k_1 - 1 + 2k_1
$$

$$
i_1 = i + 1 + 2^d - 1 + k_1
$$

$$
i_1 = i + 2^d + k_1
$$

Notice that:

$$
2^0 + 2^1 + 2^2 + \cdots + 2^{d - 1} = 2^{d} - 1
$$

Which can be derived either using the closed form of the geometric series, or by noticing that in binary, we essentially have:

$$
1111\dots1111_2 = 10_2^{n} - 1
$$

This means that because:

$$
i = 2^{d} - 1 + k_1
$$

Which implies:

$$
i_1 = i + i + 1 = 2i + 1
$$

And its second child:

$$
i_2 = 2i + 2
$$

Ok that's good, essentially, for any index, we now know a mapping from it to its children, moreover, from its children, we have a way of tracing back to its parent. Notice that if the child is a left child, it's odd, and if it's a right most child, it's even (it can be seen by the formulation mapping from the parent to the child).

As such, we can split it into two cases, if we're at index $i$, and we want to get to its parent, then if it's odd, then take $\frac{i - 1}{2}$ to get to its parent, if its even, take $\frac{i - 2}{2}$ to get to its parent.

It can be seen that this is equivalent to doing: $\text{floor}(\frac{i - 1}{2})$.

In order to avoid repetition, let's plan out what we need to do:

- insertion requires one "percolate up"
- remove requires you to repair it downwards

Since we don't have to build a heap from scratch, we do not need to implement a method to build a max heap in O(n) time, we can just implement insertion similar to how we don't need to sort an array if we just insert it such that it's sorted.


# AI usage

- AI was used to plan out the data structure
- AI wasn't used to write any of the code
- AI was used to generate some tests, whose session can be found [here in opencode_session.md](./opencode_session.md)


