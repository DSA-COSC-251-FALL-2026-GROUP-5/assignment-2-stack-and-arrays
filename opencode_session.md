# Greeting

**Session ID:** ses_ef57b79f2ffeTjkXyfTNOx82wl
**Created:** 10/5/2026, 12:23:39 PM
**Updated:** 10/5/2026, 12:49:20 PM

---

## User

don't modify any of my code, just create some unit tests using asserts inside of like QueueTest.java, StackTest.java and PriorityQueueTest.java and test it on whatever is written in assignment.pdf (if you can read it) and make it such that it

---

## Assistant

_Thinking:_

The user wants me to create unit tests in QueueTest.java, StackTest.java, and PriorityQueueTest.java, based on what's written in assignment.pdf. Their message got cut off ("and make it such that it" — incomplete). I should first explore the project to understand the structure, read the assignment.pdf, and then ask for clarification on what they want "such that it" does.

Let me start by exploring the directory structure and reading the PDF.

Let me first explore the project structure and check the assignment PDF.

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays"
}
```

Read directory /home/k1mch1/Documents/assignment-2-stack-and-arrays, entries 1-8
.git/
out/
src/
assignment_details.pdf
build
README.md
run
run_test

**Tool: glob**

**Input:**
```json
{
  "pattern": "**/*.java"
}
```

/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/OrderedArray.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingDelimiterChecker.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/StringReverser.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/DelimiterChecker.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/Stack.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/CustomUtils.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/Queue.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/GenericArray.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingPriorityQueue.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingStack.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingStringReverser.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/PriorityQueue.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UnorderedArray.java
/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingQueue.java

**Tool: glob**

**Input:**
```json
{
  "pattern": "**/*.pdf"
}
```

/home/k1mch1/Documents/assignment-2-stack-and-arrays/assignment_details.pdf

---

## Assistant

_Thinking:_

Let me read the assignment PDF and the key source files to understand what needs to be tested.

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/assignment_details.pdf"
}
```

PDF read successfully
/home/k1mch1/Documents/assignment-2-stack-and-arrays/assignment_details.pdf

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/Stack.java"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/src/Stack.java, lines 1-89
1: class Stack<T extends Comparable<T>> {
2:   // this array is only internally used
3:   private T arr[];
4:   private int popIndex;
5:   // popPtr by convention contains the next index to pop out of our stack
6:   // as such, if popPtr == arr.length - 1, our stack is full
7:   // we also want to resize the array too.
8: 
9:   @SuppressWarnings("unchecked")
10:   public Stack(int arraySize) {
11:     // 6.a this probably takes O(n) time to initialize
12:     this.arr = (T[]) new Comparable[arraySize];
13:     popIndex = -1;
14:   }
15: 
16:   public void push(T newItem) {
17:     // 6.b Without resizing, it clearly takes O(1) because we only need to index and
18:     // write to an array, and then increment a value, however, if we had to resize,
19:     // then the analysis
20:     // gets a bit more complicated
21:     //
22:     // if we resize twice the array size, then to push n elements,
23:     // it's approximately:
24:     //
25:     // O(n + 1 + 2 + 4 + 8 + ... + n)
26:     // So if we assume that n is a power of 2, then we have:
27:     //
28:     // n = 2^k
29:     //
30:     // and O(n + 1 + 2 + 4 + 8 + ... + 2^k)
31:     //
32:     // So get a closed form for k, define:
33:     //
34:     // A = 1 + 2 + 4 + 8 + ... + 2^k
35:     //
36:     // So:
37:     //
38:     // 2A = 2 + 4 + 8 + ... + 2^(k+1)
39:     //
40:     // Meaning:
41:     //
42:     // A = 2^(k + 1) - 1
43:     // A = 2n - 1
44:     //
45:     // that to push n elements, we need O(n + 2n - 1) = O(n)
46:     //
47:     // As such, to push one element, on average, it would still take O(1) time even
48:     // with resizing, or the techinical term for it is that it takes O(1) amortized
49:     if (popIndex == arr.length - 1) {
50:       arr = CustomUtils.resizeArray(arr);
51:     }
52:     popIndex++;
53:     arr[popIndex] = newItem;
54:   }
55: 
56:   public T pop() {
57:     // 6.c pop requires O(1) time because we just need to index the array and
58:     // decrement a value
59:     if (popIndex == -1) {
60:       return null;
61:     }
62:     T returnItem = arr[popIndex];
63:     arr[popIndex] = null;
64:     popIndex--;
65:     return returnItem;
66:   }
67: 
68:   public T peek() {
69:     // 6.d peek also requires O(1) time because it's just one indexing
70:     if (popIndex == -1) {
71:       return null;
72:     }
73:     T returnItem = arr[popIndex];
74:     return returnItem;
75:   }
76: 
77:   public String toString() {
78:     // 6.e this takes O(n) time because we loop through the array
79:     return CustomUtils.arrToString(arr, popIndex);
80:   }
81: 
82:   public void display() {
83:     // 6.f this takes O(n) time because it calls toString is O(n), and toString
84:     // takes o(n) time
85:     System.out.printf("Current stack: %s\r\n", toString());
86:     System.out.printf("popIndex: %d\r\n", popIndex);
87:     System.out.printf("arr.length: %d\r\n", arr.length);
88:   }
89: }

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/Queue.java"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/src/Queue.java, lines 1-115
1: class Queue<T extends Comparable<T>> {
2:   private T arr[];
3:   // by convention, state that queueHead is the next index to push to
4:   int queueHead = 0;
5:   // also by convention, state that queueTail is the next index to pop out of
6:   int queueTail = 0;
7:   int occupied = 0;
8:   // the fact that queueHead == queueTail means that our circular queue is empty
9: 
10:   @SuppressWarnings("unchecked")
11:   public Queue(int arraySize) {
12:     // 7.a. this probably takes O(n) time to instantiate
13:     arr = (T[]) new Comparable[arraySize];
14:   }
15: 
16:   public void insert(T newItem) {
17:     // 7.b. this takes O(1) time because, we only just need to insert it to the
18:     // front and
19:     // update the queueHead index
20:     if (occupied == arr.length) {
21:       // resizing will make queueHead = 0, so the resizing logic is going to be a bit
22:       // different
23:       resize(CustomUtils.getNextResize(arr));
24:     }
25:     // so by convention, queueHead is already pointing at the next index we need to
26:     // insert
27:     arr[queueHead] = newItem;
28:     queueHead = (queueHead + 1) % arr.length;
29:     occupied++;
30:     // everytime we insert, we keep track of occupied to make the logic a bit easier
31:   }
32: 
33:   public T remove() {
34:     // 7.c. to remove, we just update one index and then access one item in the
35:     // array so it should take O(1)
36:     // by convention, queueTail contains the next index to pop
37:     if (occupied == 0) {
38:       return null;
39:     }
40:     T toPop = arr[queueTail];
41:     arr[queueTail] = null;
42:     queueTail = (queueTail + 1) % arr.length;
43:     occupied--;
44:     return toPop;
45:   }
46: 
47:   public T peekFront() {
48:     // 7.d this takes O(1) because it's just indexing an array
49:     if (occupied == 0) {
50:       return null;
51:     }
52:     // queueTail already contains the next index to pop so we can just return that
53:     return arr[queueTail];
54: 
55:   }
56: 
57:   public T peekRear() {
58:     // 7.e this takes O(1) also because it's just indexing an array
59: 
60:     if (occupied == 0) {
61:       return null;
62:     }
63: 
64:     // the queueHead contains the next index to insert, as such, to peekFront, we
65:     // must get the index one less than it
66:     int frontIndex = (queueHead - 1) % arr.length;
67:     return arr[frontIndex];
68: 
69:   }
70: 
71:   public String toString() {
72:     // 7.f. to string loops through every index of the array, as such it takes O(n)
73:     // time
74:     String returnString = "[";
75:     int ptr = queueTail;
76:     for (int i = 0; i < occupied; i++) {
77:       if (i != 0) {
78:         returnString = returnString.concat(" ");
79:       }
80:       returnString = returnString.concat(arr[ptr] == null ? "null" : arr[ptr].toString());
81:       if (i != occupied - 1) {
82:         returnString = returnString.concat(",");
83:       }
84:       ptr = (ptr + 1) % arr.length;
85:     }
86:     returnString = returnString.concat("]");
87:     return returnString;
88:   }
89: 
90:   public void display() {
91:     // 7.g. display calls toString which takes O(n) time, therefore, it's O(n) since
92:     // the other prints takes place in relatively constant time
93:     System.out.printf("Current Queue: %s\r\n", toString());
94:     System.out.printf("queueHead: %d\r\n", queueHead);
95:     System.out.printf("queueTail: %d\r\n", queueTail);
96:     System.out.printf("occupied: %d\r\n", occupied);
97:   }
98: 
99:   @SuppressWarnings("unchecked")
100:   private void resize(int newSize) {
101:     // resize. Assume that we always resize the queue to a bigger queue
102:     T newArr[] = (T[]) new Comparable[newSize];
103:     int ptr = queueTail;
104: 
105:     // make occupied be the source of truth of how big our queue is
106:     for (int i = 0; i < occupied; i++) {
107:       newArr[i] = arr[ptr];
108:       ptr = (ptr + 1) % arr.length;
109:     }
110: 
111:     arr = newArr;
112:     queueTail = 0;
113:     queueHead = occupied;
114:   }
115: }

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/PriorityQueue.java"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/src/PriorityQueue.java, lines 1-247
1: class PriorityQueue<T extends Comparable<T>> {
2: 
3:   // popPtr by convention contains the next index to pop out of our stack
4:   // as such, if popPtr == arr.length - 1, our stack is full
5:   // we also want to resize the array too.
6: 
7:   /*
8:    * NOTE: for the priority queue, we will internally store a min-heap and a
9:    * max-heap, which has the following property:
10:    * - insertion can be done in O(log n) time (which is better than using an
11:    * ordered array which is O(n))
12:    * - peekFront can be done in O(1) time (just like an ordered array)
13:    * - peekRear can be done in O(1) time (just like an ordered array)
14:    * - deletion is probably O(n), although, it could be done in O(log n) time if
15:    * know the index to delete beforehand
16:    *
17:    * So in terms of time-complexity, we profit in terms of insertion (because
18:    * shifting takes O(n) time)
19:    *
20:    */
21: 
22:   Entry<T>[] maxHeap;
23:   Entry<T>[] minHeap;
24:   static final int maxHeapCompareSign = 1;
25:   static final int minHeapComapreSign = -1;
26:   int occupied;
27: 
28:   private static class Entry<T> {
29:     T item;
30:     int priorityValue;
31:     int indexInOtherHeap;
32: 
33:     Entry(T item, int priorityValue, int indexInOtherHeap) {
34:       this.item = item;
35:       this.priorityValue = priorityValue;
36:       this.indexInOtherHeap = indexInOtherHeap;
37:     }
38: 
39:     @Override
40:     public String toString() {
41:       return "Entry[value=" + item +
42:           ", priority=" + priorityValue +
43:           ", indexInOtherHeap=" + indexInOtherHeap + "]";
44:     }
45:   }
46: 
47:   @SuppressWarnings("unchecked")
48:   public PriorityQueue(int arraySize) {
49:     // 8.a this probably takes O(n) time to initialize
50:     maxHeap = new Entry[arraySize];
51:     minHeap = new Entry[arraySize];
52:     occupied = 0;
53:   }
54: 
55:   public void insert(T newItem, int priorityValue) {
56:     // 8.b. insertion takes O(log(n)) time because although we're inserting into 2
57:     // heaps, O(2 log(n)) = O(log(n)). The reason why insertion into heaps cost
58:     // O(log(n)) time is because percolate up will swap at most d or so times where
59:     // d is the depth of the binary tree and d = log(n) as such, it takes O(d) =
60:     // O(log n)
61: 
62:     if (occupied == maxHeap.length) {
63:       // ... assume that min heap and max heap has the same length, so just check one
64:       // of them if they're overflowing
65:       maxHeap = CustomUtils.resizeArray(maxHeap);
66:       minHeap = CustomUtils.resizeArray(minHeap);
67:     }
68:     maxHeap[occupied] = new Entry<T>(newItem, priorityValue, occupied);
69:     minHeap[occupied] = new Entry<T>(newItem, priorityValue, occupied);
70:     occupied++;
71: 
72:     if (occupied <= 1) {
73:       return;
74:     }
75:     percolateUp(maxHeap, minHeap, maxHeapCompareSign, occupied - 1);
76:     percolateUp(minHeap, maxHeap, minHeapComapreSign, occupied - 1);
77:   }
78: 
79:   public void percolateUp(Entry<T>[] heap, Entry<T>[] otherHeap, int sign, int ptr) {
80:     // without loss of generality, we assume that sign is positive for now, and we
81:     // want to build a max heap.
82: 
83:     // assume that from the range [0, occupied - 2], the heap invariant is already
84:     // preserved.
85: 
86:     int parentPtr = (ptr - 1) / 2;
87:     while (ptr > 0 && sign * Integer.compare(heap[parentPtr].priorityValue, heap[ptr].priorityValue) < 0) {
88:       // this means that while the parent is of a smaller value than the child, then
89:       // we swap the two
90:       // every time we perform a swap, we must update the heap index of the other heap
91:       otherHeap[heap[parentPtr].indexInOtherHeap].indexInOtherHeap = ptr;
92:       otherHeap[heap[ptr].indexInOtherHeap].indexInOtherHeap = parentPtr;
93:       swap(heap, parentPtr, ptr);
94:       ptr = parentPtr;
95:       parentPtr = (parentPtr - 1) / 2;
96:     }
97:   }
98: 
99:   public T remove() {
100:     // 8.c removal takes O(log n) time because an arbitrary removal from a heap
101:     // takes O(log(n)) time as long as you know the index you want to remove from
102:     // beforehand
103:     // NOTE: if we want to remove in O(log n) time, and if we only want to remove
104:     // the item with the smallest priority, we need to know where that is stored in
105:     // the max heap too
106:     // to do this, we store a cross reference pointer from one heap to the other
107:     // heap
108: 
109:     // the first step is to simply store the return value as the first item in the
110:     // minHeap
111: 
112:     if (occupied == 0) {
113:       return null;
114:     }
115: 
116:     Entry<T> entryToRemove = minHeap[0];
117: 
118:     // here comes the hard part. Essentially, for the minHeap, we take the last
119:     // element just perform a siftDown from the root after
120:     minHeap[0] = minHeap[occupied - 1];
121:     maxHeap[minHeap[0].indexInOtherHeap].indexInOtherHeap = 0;
122:     minHeap[occupied - 1] = null;
123:     occupied--; // we subtract beforehand because we assume that siftDown will probably use it
124:                 // as a stop condition
125:     siftDown(minHeap, maxHeap, minHeapComapreSign, 0);
126:     // however, now we need to syncrhonize it with the maxHeap. We can delete an
127:     // arbitrary element in a heap in O(log n) time as long as you know the index of
128:     // what to delete beforehand.
129: 
130:     int deletionIndexMaxHeap = entryToRemove.indexInOtherHeap;
131: 
132:     if (deletionIndexMaxHeap == occupied) {
133:       // because if we've just deleted the end of the heap, there is no need to
134:       // percolate up or sift down, just delete it
135:       maxHeap[occupied] = null;
136:       return entryToRemove.item;
137:     }
138: 
139:     maxHeap[deletionIndexMaxHeap] = maxHeap[occupied];
140:     minHeap[maxHeap[deletionIndexMaxHeap].indexInOtherHeap].indexInOtherHeap = deletionIndexMaxHeap;
141:     maxHeap[occupied] = null;
142: 
143:     // now we check whether we should siftDown or percolateUp from here
144: 
145:     if (occupied <= 1) {
146:       return entryToRemove.item;
147:     }
148: 
149:     // now we need to repair the invariant on the maxHeap
150:     int parentPtr = (deletionIndexMaxHeap - 1) / 2;
151:     if (maxHeapCompareSign
152:         * Integer.compare(maxHeap[parentPtr].priorityValue, maxHeap[deletionIndexMaxHeap].priorityValue) < 0) {
153:       // if the element of the parent is less than the element we swapped, then we
154:       // need to
155:       // percolateUp because, if we assume that the heap was valid before the swap,
156:       // then what we currently have is greater than the siblings and everything below
157:       // it
158:       percolateUp(maxHeap, minHeap, maxHeapCompareSign, deletionIndexMaxHeap);
159:     } else {
160:       // if the element of the parent is greater or equals to it, then maybe siftDown,
161:       // just in case that its children is bigger than it
162:       siftDown(maxHeap, minHeap, maxHeapCompareSign, deletionIndexMaxHeap);
163:     }
164:     return entryToRemove.item;
165:   }
166: 
167:   public void siftDown(Entry<T>[] heap, Entry<T>[] otherHeap, int sign, int ptr) {
168:     // without loss of generally, assume that we have a maxHeap with positive sign
169:     // to reason about this
170:     // we stop when our leftChildPtr is greater than or equals to occupied
171:     while (true) {
172:       // so if this is a max heap, we want the bigger value in order to swap
173:       int leftChildPtr = 2 * ptr + 1;
174: 
175:       if (leftChildPtr >= occupied) {
176:         break;
177:       }
178: 
179:       int childToSwapPtr;
180:       if (leftChildPtr + 1 >= occupied) {
181:         childToSwapPtr = leftChildPtr;
182:       } else if (sign * Integer.compare(heap[leftChildPtr].priorityValue, heap[leftChildPtr + 1].priorityValue) > 0) {
183:         childToSwapPtr = leftChildPtr;
184:       } else {
185:         childToSwapPtr = leftChildPtr + 1;
186:       }
187: 
188:       if (sign * Integer.compare(heap[ptr].priorityValue, heap[childToSwapPtr].priorityValue) >= 0) {
189:         // if what we have as parent is bigger than or equals to the biggest of our
190:         // children, we're done
191:         break;
192:       }
193: 
194:       // otherwise, we're going to need to swap it
195: 
196:       // we'll first update cross-reference in the otherHeap
197:       otherHeap[heap[childToSwapPtr].indexInOtherHeap].indexInOtherHeap = ptr;
198:       otherHeap[heap[ptr].indexInOtherHeap].indexInOtherHeap = childToSwapPtr;
199:       swap(heap, childToSwapPtr, ptr);
200:       ptr = childToSwapPtr;
201:     }
202:   }
203: 
204:   public void swap(Entry<T>[] arr, int ptr1, int ptr2) {
205:     Entry<T> tmp = arr[ptr1];
206:     arr[ptr1] = arr[ptr2];
207:     arr[ptr2] = tmp;
208:   }
209: 
210:   public T peekFront() {
211:     // 8.d. this takes O(1) time because it's accessing an array through an index
212:     // one time
213:     // NOTE: assume that the front of the queue is the first thing we will pop
214:     if (occupied == 0) {
215:       return null;
216:     }
217:     return minHeap[0].item;
218:   }
219: 
220:   public T peekRear() {
221:     // 8.e. this also takes O(1) time similar to peekFront
222: 
223:     if (occupied == 0) {
224:       return null;
225:     }
226: 
227:     return maxHeap[0].item;
228:   }
229: 
230:   public String toString() {
231:     // 8.f. this takes O(n) time because it needs to loop through every element in
232:     // the
233:     // heap
234:     return String.format(
235:         "max heap: %s\r\nmin heap: %s\r\n",
236:         CustomUtils.arrToString(maxHeap, occupied - 1),
237:         CustomUtils.arrToString(minHeap, occupied - 1));
238:   }
239: 
240:   public void display() {
241:     // 8.g. this takes O(n) time because to string takes O(n) time
242:     System.out.print(toString());
243:     System.out.printf("peekFront: %s\r\n", peekFront());
244:     System.out.printf("peekRear: %s\r\n", peekRear());
245:   }
246: 
247: }

---

## Assistant

_Thinking:_

I couldn't read the PDF directly (model doesn't support PDF input). Let me try to extract text from the PDF using a shell command like `pdftotext` if available, or check the README and other files. Also, the user's message got cut off: "and make it such that it" — I need to ask them to complete that sentence. But first, let me gather context.

Let me check the README, the run scripts, and try to extract the PDF text.

I couldn't read the PDF directly (this model doesn't support PDF input), so let me try extracting its text with command-line tools, and also check the README and helper classes.

**Tool: shell**

**Input:**
```json
{
  "command": "which pdftotext pdfinfo python3 2>/dev/null; ls -la /home/k1mch1/Documents/assignment-2-stack-and-arrays"
}
```

/usr/bin/pdftotext
/usr/bin/pdfinfo
/usr/bin/python3
total 128
drwxr-xr-x  5 k1mch1 k1mch1  4096 Oct  5 10:05 .
drwxr-xr-x 16 k1mch1 k1mch1  4096 Sep 30 17:48 ..
-rw-r--r--  1 k1mch1 k1mch1 85441 Sep 30 10:59 assignment_details.pdf
-rwxr-xr-x  1 k1mch1 k1mch1    72 Sep 30 10:58 build
drwxr-xr-x  7 k1mch1 k1mch1  4096 Oct  5 11:29 .git
drwxr-xr-x  2 k1mch1 k1mch1  4096 Oct  5 11:23 out
-rw-r--r--  1 k1mch1 k1mch1  8482 Oct  5 10:05 README.md
-rwxr-xr-x  1 k1mch1 k1mch1    44 Sep 30 10:58 run
-rwxr-xr-x  1 k1mch1 k1mch1    48 Sep 30 10:58 run_test
drwxr-xr-x  2 k1mch1 k1mch1  4096 Oct  5 11:28 src

Command exited with code 0.

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/README.md"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/README.md, lines 1-227
1: # What to turn in
2: 
3: What to Turn In: This is a group assignment; each group should submit only once.
4: 
5: 1. Your Stack Java class with all required methods and functionality. The file must be named Stack.java, and the class must be named Stack.
6: 
7: 2. Your Queue Java class with all required methods and functionality. The file must be named Queue.java, and the class must be named Queue.
8: 
9: 3. Your PriorityQueue Java class with all required methods and functionality. The file must be named PriorityQueue.java, and the class must be named PriorityQueue.
10: 
11: 4. Your StringReverser Java class with all required methods and functionality. The file must be named StringReverser.java, and the class must be named StringReverser.
12: 
13: 5. Your DelimiterChecker Java class with all required methods and functionality. The file must be named DelimiterChecker.java, and the class must be named DelimiterChecker.
14: 
15: 6. Optional: any additional supporting Java classes.
16: 
17: 7. Note: Do not submit a main class. The submitted classes must not include a main method.
18: 
19: # Project requirements
20: 
21: 1. The Stack, Queue, and PriorityQueue classes must be declared as generic classes: Stack<T>, Queue<T>, and PriorityQueue<T>.
22: 
23: 2. The Stack, Queue, and PriorityQueue classes must be implemented from scratch using arrays.
24: 
25: 3. If the internal array becomes full, the class should automatically resize the array.
26: 
27: 4. If pop(), remove(), peek(), peekFront(), or peekRear() is called when the structure is empty,
28: the method should return null.
29: 
30: 5. Each required method must include a short comment stating its time complexity.
31: 
32: ~~6. Stack Class (LIFO) Implement the following constructor and methods:~~
33: 
34: ~~(a) public Stack(int arraySize)~~
35: 
36: ~~(b) public void push(T newItem)~~
37: 
38: ~~(c) public T pop()~~
39: 
40: ~~(d) public T peek()~~
41: 
42: ~~(e) public String toString()~~
43: 
44: ~~(f) public void display()~~
45: 
46: 7. Queue Class (FIFO) Implement the following constructor and methods:
47: 
48: ~~(a) public Queue(int arraySize)~~
49: 
50: ~~(b) public void insert(T newItem)~~
51: 
52: ~~(c) public T remove()~~
53: 
54: ~~(d) public T peekFront()~~
55: 
56: ~~(e) public T peekRear()~~
57: 
58: ~~(f) public String toString()~~
59: 
60: ~~(g) public void display()~~
61: 
62: The Queue class may be implemented using a circular array or by shifting elements after removal.
63: 
64: 8. PriorityQueue Class
65: 
66: Each item has an associated priorityValue. A smaller priorityValue means higher priority.
67: Items must be removed based on priority.
68: 
69: Implement the following constructor and methods:
70: 
71: ~~(a) public PriorityQueue(int arraySize)~~
72: 
73: (b) public void insert(T newItem, int priorityValue)
74: 
75: (c) public T remove()
76: 
77: (d) public T peekFront()
78: 
79: (e) public T peekRear()
80: 
81: (f) public String toString()
82: 
83: (g) public void display()
84: 
85: 9. StringReverser Class
86: 
87: Create a utility class that uses your Stack implementation and contains the following static method:
88: 
89: (a) public static String reverse(String input)
90: 
91: This method accepts a string and returns its reversed version.
92: 
93: 10. DelimiterChecker Class
94: 
95: Create a utility class that uses your Stack implementation and contains the following static method:
96: 
97: (a) public static boolean check(String input)
98: This method validates the delimiters in a string and returns true if all delimiters match correctly.
99: Otherwise, it should return false. It should only handle the following delimiters: (), [], and {}.
100: 
101: # Notes
102: 
103: ## Heaps
104: 
105: Heaps are binary trees that preserve the following invariants:
106: 
107: If we for a min-heap:
108: 
109: - the children of the parent must be of a value smaller than or equal to the parent
110: 
111: Similarly, for a max heap:
112: 
113: - the children of the parent must be of a value greater than or equal to the parent
114: 
115: Let's just talk about the min-heap, and then we can transfer that to the max-heap.
116: 
117: Let's also conceptually visualize it as a binary tree first, before implementing it as an array.
118: 
119: Suppose that we have an array. In order to turn it into a min-heap, we must perform an operation called "heapify" which goes like this:
120: 
121: - pop each one of the elements in the array one by one
122: - perform an insertion operation on each of those elements in the heap
123: 
124: Now that we've broken it down into a smaller operation called an insertion, we must talk about how to implement it.
125: 
126: To insert, we place the new element at the bottom-most, left-most open spot of the heap, and then perform something called percolate up.
127: 
128: Essentially, to do this, we compare whether the thing we've inserted is less than the parent, if yes, then we swap the parent and the child, and we continue this process until it's equals to or greater than the parent.
129: 
130: This essentially takes $O(log(n))$ time because, our heap is always balanced.
131: 
132: Now, if we want to pop the root element (which is guaranteed to be the minimum value of the whole structure), we swap the root element with the last element in the heap (if we're talking in the order to of top to down, left to right), and then we can safely pick out that minimum value. Next, we must now repair the invariant through what is essentially the reverse of the "percolate up" step, which means, we take teh root, and if it's bigger than one of the child, swap them, otherwise, do nothing and exit because the invariant is met.
133: 
134: ### Array representation
135: 
136: A binary tree can be represented using pointers and nodes, or in the case of the heap, the most common way to represent it is by using an array. 
137: 
138: How we present it as follows, if we assume that the tree must always be filled in a way such that it's left to right and top down, then every time we add to the tree, we simply append it to the end of the array.
139: 
140: Now we must derive the relationship between the parents and its children.
141: 
142: Given that we have a parent, how do we get to its children? If we know this, then we can do the inverse in order to get from the children to the parent as well.
143: 
144: Suppose that we have an arbitrary node at index $i$ (0th index). Now, if we append it left to right and top to down, how many nodes must we pass until we get to the node that is guaranteed to be the children of $i$? Well, if $i$ is at depth $d$ where $d = 0$ is at the root, then we say that $i$ can be represented as such: $i = 2^0 + 2^1 + 2^2 + \cdots + 2^{d - 1} + k_1$ for $d > 0$. And also, in the case that $d = 0$, $i = 0$. Essentially, we say that the current index is the same as the amount of nodes that was previous to it.
145: 
146: Where $k_1$ is going to be the number of nodes at depth $d$ before it.
147: 
148: And we say that $k_2$ is going to be the number of nodes after that node at index $i$ until we reach the end of depth $d$, that is:
149: 
150: $$
151: k_1 + 1 + k_2  = 2^d
152: $$
153: 
154: Now, until we get to the children of the node $i$, we must go through $k_2$ nodes, and also go through all of $k_1$ children, which is simply $2k_1$ because we say they all of children until we get the children of node $i$.
155: 
156: Now, this means that its first child (left most child) will be at exactly node:
157: 
158: $$
159: i_{1} = i + 1 + k_2 + 2k_1
160: $$
161: 
162: Now we we just perform some algebraic manipulation:
163: 
164: $$
165: k_2  = 2^d - k_1 - 1
166: $$
167: 
168: So:
169: 
170: $$
171: i_1 = i + 1 + 2^d - k_1 - 1 + 2k_1
172: $$
173: 
174: $$
175: i_1 = i + 1 + 2^d - 1 + k_1
176: $$
177: 
178: $$
179: i_1 = i + 2^d + k_1
180: $$
181: 
182: Notice that:
183: 
184: $$
185: 2^0 + 2^1 + 2^2 + \cdots + 2^{d - 1} = 2^{d} - 1
186: $$
187: 
188: Which can be derived either using the closed form of the geometric series, or by noticing that in binary, we essentially have:
189: 
190: $$
191: 1111\dots1111_2 = 10_2^{n} - 1
192: $$
193: 
194: This means that because:
195: 
196: $$
197: i = 2^{d} - 1 + k_1
198: $$
199: 
200: Which implies:
201: 
202: $$
203: i_1 = i + i + 1 = 2i + 1
204: $$
205: 
206: And its second child:
207: 
208: $$
209: i_2 = 2i + 2
210: $$
211: 
212: Ok that's good, essentially, for any index, we now know a mapping from it to its children, moreover, from its children, we have a way of tracing back to its parent. Notice that if the child is a left child, it's odd, and if it's a right most child, it's even (it can be seen by the formulation mapping from the parent to the child).
213: 
214: As such, we can split it into two cases, if we're at index $i$, and we want to get to its parent, then if it's odd, then take $\frac{i - 1}{2}$ to get to its parent, if its even, take $\frac{i - 2}{2}$ to get to its parent.
215: 
216: It can be seen that this is equivalent to doing: $\text{floor}(\frac{i - 1}{2})$.
217: 
218: In order to avoid repetition, let's plan out what we need to do:
219: 
220: - insertion requires one "percolate up"
221: - remove requires you to repair it downwards
222: 
223: Since we don't have to build a heap from scratch, we do not need to implement a method to build a max heap in O(n) time, we can just implement insertion similar to how we don't need to sort an array if we just insert it such that it's sorted.
224: 
225: 
226: 
227: 

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/CustomUtils.java"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/src/CustomUtils.java, lines 1-43
1: import java.util.Arrays;
2: 
3: public final class CustomUtils<T extends Comparable<T>> {
4:   public static <T> int getNextResize(T[] arr) {
5:     // specify the heuristics, which in our case, will just be doubling
6:     return 2 * arr.length;
7:   }
8: 
9:   public static <T> T[] resizeArray(T[] arr) {
10:     /*
11:      * if java was able to allow creation of arrays of generic types, then we could
12:      * do something like:
13:      *
14:      * T[] newArr = new T[getNextResize(arr)];
15:      *
16:      * for (int i = 0; i < arr.length; i++){
17:      * newArr[i] = arr[i];
18:      * }
19:      *
20:      * return newArr;
21:      *
22:      * The alternative to making something like this work is way too complicated and
23:      * hacky, so i'm just going to use Arrays.copyOf since it's more elegant. Note
24:      * that this will probably take O(n) time
25:      */
26:     return Arrays.copyOf(arr, getNextResize(arr));
27:   }
28: 
29:   public static <T> String arrToString(T arr[], int endIndex) {
30:     String returnString = "[";
31:     for (int i = 0; i <= endIndex; i++) {
32:       if (i != 0) {
33:         returnString = returnString.concat(" ");
34:       }
35:       returnString = returnString.concat(arr[i] == null ? "null" : arr[i].toString());
36:       if (i != endIndex) {
37:         returnString = returnString.concat(",");
38:       }
39:     }
40:     returnString = returnString.concat("]");
41:     return returnString;
42:   }
43: }

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/run_test"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/run_test, lines 1-2
1: #!/usr/bin/env bash
2: java -ea -cp "out:lib/*" $1

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/run"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/run, lines 1-2
1: #!/usr/bin/env bash
2: java -cp "out:lib/*" $1

---

## Assistant

_Thinking:_

I have pdftotext available. Let me extract the PDF text to verify the assignment requirements. Also, let me check the build script and the other source files (UsingStack, etc. — those are likely demo "main" classes). Also check the build script.

The user's message was cut off: "and make it such that it" — I should ask them to complete that. But first let me gather all the info so my question is well-informed. Let me extract the PDF text.

I have `pdftotext` available, so I can extract the PDF content. Let me also look at the build script and the demo classes

**Tool: shell**

**Input:**
```json
{
  "command": "pdftotext -layout /home/k1mch1/Documents/assignment-2-stack-and-arrays/assignment_details.pdf - 2>/dev/null | head -300"
}
```

               COSC 251 – Fall 2026: Assignment 2 — Stacks and Queues (50 points)
                                   Deadline: Oct 7, 11:59 PM
                       Prepared by: Visethboti Sin (v.sin@aupp.edu.kh)

Overview: In this assignment, you will implement the following classes: Stack, Queue, PriorityQueue,
StringReverser, and DelimiterChecker.
   When designing your solution, pay close attention to time complexity to earn full credit. The Stack,
Queue, and PriorityQueue classes must use generics and adhere to the principles and functionalities
covered in class.
Project Requirements:
  1. The Stack, Queue, and PriorityQueue classes must be declared as generic classes: Stack<T>,
     Queue<T>, and PriorityQueue<T>.
  2. The Stack, Queue, and PriorityQueue classes must be implemented from scratch using arrays.
  3. If the internal array becomes full, the class should automatically resize the array.
  4. If pop(), remove(), peek(), peekFront(), or peekRear() is called when the structure is empty,
     the method should return null.
  5. Each required method must include a short comment stating its time complexity.
  6. Stack Class (LIFO) Implement the following constructor and methods:
      (a) public Stack(int arraySize)
      (b) public void push(T newItem)
      (c) public T pop()
      (d) public T peek()
      (e) public String toString()
      (f) public void display()
  7. Queue Class (FIFO) Implement the following constructor and methods:
      (a) public Queue(int arraySize)
      (b) public void insert(T newItem)
      (c) public T remove()
      (d) public T peekFront()
      (e) public T peekRear()
      (f) public String toString()
      (g) public void display()
     The Queue class may be implemented using a circular array or by shifting elements after removal.
  8. PriorityQueue Class
     Each item has an associated priorityValue. A smaller priorityValue means higher priority.
     Items must be removed based on priority.
     Implement the following constructor and methods:
      (a) public PriorityQueue(int arraySize)
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


What to Turn In: This is a group assignment; each group should submit only once.

  1. Your Stack Java class with all required methods and functionality. The file must be named
     Stack.java, and the class must be named Stack.
  2. Your Queue Java class with all required methods and functionality. The file must be named
     Queue.java, and the class must be named Queue.
  3. Your PriorityQueue Java class with all required methods and functionality. The file must be
     named PriorityQueue.java, and the class must be named PriorityQueue.
  4. Your StringReverser Java class with all required methods and functionality. The file must be
     named StringReverser.java, and the class must be named StringReverser.
  5. Your DelimiterChecker Java class with all required methods and functionality. The file must be
     named DelimiterChecker.java, and the class must be named DelimiterChecker.
  6. Optional: any additional supporting Java classes.
  7. Note: Do not submit a main class. The submitted classes must not include a main method.


Grading:
  1. Program Design (10): The overall quality of your solution design.
  2. Program Execution (15): The program runs correctly without errors.
  3. Specification Satisfaction (15): The program fully meets the given requirements.
  4. Coding Style and Readability (10): Code is well-formatted, clear, and includes meaningful com-
     ments.




                                                Page 2

Command exited with code 0.

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/build"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/build, lines 1-2
1: #!/usr/bin/env bash
2: javac -cp "lib/*" -d out $(find src -name "*.java")

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingStack.java"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingStack.java, lines 1-27
1: import java.util.Arrays;
2: 
3: class UsingStack {
4:   public static void main(String[] args) {
5:     /*
6:      * Integer[] arr = new Integer[2];
7:      * Integer[] resizedArr = CustomUtils.resizeArray(arr);
8:      * System.out.println(Arrays.toString(arr));
9:      * System.out.println(Arrays.toString(resizedArr));
10:      */
11: 
12:     Stack<Integer> stack = new Stack<Integer>(2);
13:     stack.push(2);
14:     stack.push(3);
15:     stack.push(4);
16:     stack.push(5);
17:     stack.push(6);
18:     stack.push(7);
19:     stack.display();
20:     System.out.println(stack.pop());
21:     System.out.println(stack.pop());
22:     System.out.println(stack.pop());
23:     System.out.println(stack.pop());
24:     System.out.println(stack.pop());
25:     stack.display();
26:   }
27: }

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingQueue.java"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingQueue.java, lines 1-40
1: class UsingQueue {
2:   public static void main(String[] args) {
3:     // I'm just going to create a normal queue
4:     Queue<Integer> queue = new Queue<Integer>(5);
5:     queue.insert(1);
6:     queue.insert(2);
7:     queue.insert(3);
8:     queue.display();
9:     System.out.printf("removing: %s\r\n", queue.remove());
10:     System.out.printf("removing: %s\r\n", queue.remove());
11:     queue.insert(4);
12:     queue.insert(5);
13:     queue.insert(6);
14:     queue.insert(7);
15:     queue.display();
16:     System.out.printf("removing: %s\r\n", queue.remove());
17:     queue.display();
18:     queue.insert(8);
19:     queue.insert(9);
20:     queue.insert(10);
21:     queue.insert(11);
22:     queue.insert(12);
23:     queue.insert(13);
24:     queue.display();
25:     System.out.printf("removing: %s\r\n", queue.remove());
26:     System.out.printf("removing: %s\r\n", queue.remove());
27:     System.out.printf("removing: %s\r\n", queue.remove());
28:     System.out.printf("removing: %s\r\n", queue.remove());
29:     System.out.printf("removing: %s\r\n", queue.remove());
30:     System.out.printf("removing: %s\r\n", queue.remove());
31:     System.out.printf("removing: %s\r\n", queue.remove());
32:     System.out.printf("removing: %s\r\n", queue.remove());
33:     System.out.printf("removing: %s\r\n", queue.remove());
34:     System.out.printf("removing: %s\r\n", queue.remove());
35:     System.out.printf("removing: %s\r\n", queue.remove());
36:     queue.display();
37:     queue.insert(12);
38:     queue.display();
39:   }
40: }

**Tool: read**

**Input:**
```json
{
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingPriorityQueue.java"
}
```

Read file /home/k1mch1/Documents/assignment-2-stack-and-arrays/src/UsingPriorityQueue.java, lines 1-35
1: class UsingPriorityQueue {
2:   public static void main(String[] args) {
3:     PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(10);
4:     priorityQueue.insert(2, 2);
5:     priorityQueue.display();
6:     priorityQueue.insert(3, 3);
7:     priorityQueue.insert(10, 10);
8:     priorityQueue.insert(1, 1);
9:     priorityQueue.insert(4, 4);
10:     priorityQueue.insert(8, 8);
11:     priorityQueue.insert(1, 1);
12:     System.out.println(priorityQueue.remove());
13:     System.out.println(priorityQueue.remove());
14:     System.out.println(priorityQueue.remove());
15:     System.out.println(priorityQueue.remove());
16:     System.out.println(priorityQueue.remove());
17:     System.out.println(priorityQueue.remove());
18:     priorityQueue.display();
19:     priorityQueue.insert(100, 100);
20:     priorityQueue.display();
21:     System.out.println(priorityQueue.remove());
22:     System.out.println(priorityQueue.remove());
23:     priorityQueue.insert(100, 100);
24:     priorityQueue.insert(50, 50);
25:     priorityQueue.insert(25, 25);
26:     priorityQueue.insert(75, 75);
27:     priorityQueue.display();
28:     System.out.println(priorityQueue.remove());
29:     System.out.println(priorityQueue.remove());
30:     System.out.println(priorityQueue.remove());
31:     System.out.println(priorityQueue.remove());
32:     System.out.println(priorityQueue.remove());
33:     priorityQueue.display();
34:   }
35: }

---

## Assistant

_Thinking:_

Now I have the full picture:

**Assignment requirements (from PDF and README):**

Stack<T extends Comparable<T>>:
- `Stack(int arraySize)`
- `void push(T newItem)` — LIFO
- `T pop()` — returns null when empty
- `T peek()` — returns null when empty
- `String toString()`
- `void display()`
- Auto-resize when full

Queue<T extends Comparable<T>>:
- `Queue(int arraySize)`
- `void insert(T newItem)` — FIFO
- `T remove()` — null when empty
- `T peekFront()` — null when empty
- `T peekRear()` — null when empty
- `String toString()`
- `void display()`
- Auto-resize when full

PriorityQueue<T extends Comparable<T>>:
- `PriorityQueue(int arraySize)`
- `void insert(T newItem, int priorityValue)` — smaller priorityValue = higher priority
- `T remove()` — removes smallest priority value first; null when empty
- `T peekFront()` — null when empty
- `T peekRear()` — null when empty
- `String toString()`
- `void display()`

The user asked for: "create some unit tests using asserts inside of like QueueTest.java, StackTest.java and PriorityQueueTest.java and test it on whatever is written in assignment.pdf ... and make it such that it" — the message got cut off.

The user's sentence is incomplete: "and make it such that it". I should ask them to complete that requirement before proceeding... However, the core request is quite clear: create test files with asserts, testing the functionality described in the assignment PDF, without modifying existing code. The trailing "make it such that it" is ambiguous — it could be "make it such that it runs with the existing build/run scripts" or something else.

Given the ambiguity, I should ask a clarifying question. But maybe I can make a reasonable assumption and just do it, while noting the assumption. The user said "don't modify any of my code" — so the test files should be new files only.

Important considerations:
1. The classes (Stack, Queue, PriorityQueue) are package-private (no `public` modifier, no package declaration — default package). Test classes in the default package can access them.
2. The `run_test` script runs `java -ea -cp "out:lib/*" $1` — so tests would be run with `-ea` (assertions enabled) and take the class name as argument. This suggests tests should have a `main` method and use Java `assert` statements.
3. The build script compiles all files in src/ into out/.

So a sensible design: create `StackTest.java`, `QueueTest.java`, `PriorityQueueTest.java` in `src/` with a `main` method containing `assert` statements covering the assignment spec:
- empty pop/remove/peek returns null
- LIFO/FIFO ordering
- priority ordering (smaller priorityValue removed first)
- resize behavior (push more than arraySize)
- peek doesn't remove
- toString format maybe
- duplicate priorities
- interleaved insert/remove (wrap-around for circular queue)

The user's trailing sentence was cut off. I could ask what "make it such that it" means. The question tool is appropriate here. But also, the request is largely complete; asking one clarifying question is wise since the instruction was literally truncated mid-sentence. However, I can also proceed with a sensible default (plain Java asserts + main method, runnable via existing `build` and `run_test` scripts) and mention they can tell me more.

The guidance says to use the question tool to clarify ambiguous instructions. The truncated sentence is genuinely ambiguous. But it might be more helpful to do the bulk of the work and ask about the incomplete part. Let me think about what "make it such that it..." might be:
- "make it such that it covers edge cases"
- "make it such that it runs with ./run_test"
- "make it such that it doesn't use JUnit" (likely, since there's no lib dir with JUnit; run_test uses -ea flag which enables Java asserts)

The presence of `run_test` with `java -ea` strongly suggests plain Java asserts with a main method. The `lib/*` in classpath suggests maybe JUnit could be added, but there's no lib directory currently.

I'll ask a quick clarifying question about the truncated part, but I can also just proceed — the question tool blocks until the user answers. Given the user's message got cut off mid-sentence, it's polite and correct to ask. But the harness guidance also says avoid unnecessary questions... The truncation is real though: "and make it such that it" — I genuinely don't know what they want.

Actually, let me reconsider: maybe the best approach is to write the tests now (they're clearly requested) in a way that covers the assignment spec thoroughly, using plain `assert` in `main` methods runnable via the existing scripts, and then note that their sentence appeared cut off and ask them to finish it. That way I deliver value immediately and clarify. But the question tool would interrupt. Alternatively, ask the question and wait.

Hmm, the risk: if I guess wrong about "make it such that it [something]", I may have to redo. The core deliverable (tests with asserts) is unambiguous though. The truncated part is an additional constraint. A reasonable default that satisfies most plausible completions: plain Java asserts, main methods, works with `./build` and `./run_test StackTest` etc., no modification to existing files, no JUnit dependency.

I think the best move: ask the clarifying question first since the instruction was literally cut off mid-sentence, and the user explicitly had more to say. It's a single quick question. Actually — let me weigh once more. The question tool description says: "Use this tool when you need to ask the user questions during execution. This allows you to: 1. Gather user preferences... 2. Clarify ambiguous instructions..." This qualifies.

But also the instruction says "If you recommend a specific option, make that the first option". I could offer options like:
- "Run via the existing ./run_test script with Java asserts (Recommended)"
- "Use JUnit (requires adding JUnit jar to lib/)"
- "Something else" — they'll type it.

Actually the third option is automatically added ("Type your own answer"). Let me ask: "Your message got cut off at 'and make it such that it…'. What should the tests be like?" with options:
1. Plain Java assert statements + main(), runnable via ./build and ./run_test (Recommended)
2. JUnit tests (needs JUnit in lib/)

Hmm, but there's a subtlety: the classes are package-private in the default package. JUnit 5 would need the test classes in the same (default) package, which is possible (no package declaration). JUnit 4 also. But no lib directory exists, so JUnit isn't available. The run_test script's `-ea` flag is a strong signal for plain asserts.

Also consider: should tests print success message? Common pattern: after all asserts pass, print "All tests passed".

Let me also think about what tests to write, covering the assignment spec:

**StackTest.java:**
1. Empty stack: pop() returns null, peek() returns null.
2. Push then peek: peek returns item without removing.
3. LIFO order: push 1,2,3 → pop 3,2,1.
4. Resize: create size 2, push 5 items, pop all in LIFO order (checks auto-resize).
5. Interleaved push/pop.
6. toString format: "[1, 2, 3]" — after pushing 1,2,3 toString should be "[1, 2, 3]". Let me verify: CustomUtils.arrToString(arr, popIndex) → "[1, 2, 3]". Yes.
7. Generic with Strings.
8. pop on emptied stack returns null again.

**QueueTest.java:**
1. Empty: remove(), peekFront(), peekRear() return null.
2. FIFO order: insert 1,2,3 → remove 1,2,3.
3. peekFront/peekRear don't remove.
4. Circular wrap-around: small queue (size 3), insert/remove interleaved to wrap queueHead/queueTail.
5. Resize: insert more than initial capacity, verify FIFO order preserved.
6. toString format: "[1, 2, 3]" (comma-space separated, with commas... let me check: between elements " " and "," — looking at the code: for i != 0, concat " ", then element, then if i != occupied-1 concat ",". So "1, 2, 3"? Wait: element 0: "[", then "1", then ",". element 1: " " (i!=0), "2", ",". element 2: " ", "3". Result: "[1, 2, 3]". Yes.
7. Strings.

**PriorityQueueTest.java:**
1. Empty: remove(), peekFront(), peekRear() return null.
2. Smaller priorityValue removed first: insert items with priorities out of order, remove and check ascending priority order.
3. peekFront returns min-priority item, peekRear returns max-priority item (since maxHeap root is the largest priorityValue → lowest priority, i.e., "rear").
4. Ties: equal priorities — FIFO-ish? The implementation's behavior with ties may vary; better to just check that the removed item is one of the tied items, or use distinct priorities to avoid ambiguity. For duplicate priorities, remove order isn't strictly specified. I'll test that removing all items yields a non-decreasing sequence of priorities, which holds even with ties.
5. Resize: insert more than initial capacity.
6. Interleaved insert/remove.
7. toString contains both heaps — maybe skip exact format, or just call it to ensure no exception.

Also, one more consideration: PriorityQueue `insert` with occupied<=1 returns early — fine.

Wait, there's a potential bug in PriorityQueue remove: `minHeap[0] = minHeap[occupied - 1];` then `maxHeap[minHeap[0].indexInOtherHeap].indexInOtherHeap = 0;` — if occupied==1, minHeap[0] = minHeap[0], fine. Edge cases exist but tests should just verify spec behavior. Let me be careful: test with single element remove, two elements, etc. Actually, let me think about whether there are bugs that my tests would catch — that's fine, tests are supposed to catch bugs. The user wants tests; if they fail, that reveals bugs in their code which is valuable. But wait — the user said "test it on whatever is written in assignment.pdf" — they want tests of the spec. If the implementation is buggy, tests failing is correct behavior. Let me quickly sanity-check the implementation for obvious bugs so I know what to expect:

PriorityQueue remove with occupied == 1: entryToRemove = minHeap[0]. minHeap[0] = minHeap[0]. maxHeap[minHeap[0].indexInOtherHeap].indexInOtherHeap = 0 → maxHeap[0].indexInOtherHeap = 0. occupied-- → 0. siftDown with occupied=0: leftChildPtr=1 >= 0 → break. deletionIndexMaxHeap = entryToRemove.indexInOtherHeap = 0. deletionIndexMaxHeap == occupied (0==0) → maxHeap[0]=null, return. Good.

Two elements: insert A(p=5), B(p=3). maxHeap: [A(5), B(3)] — percolateUp: parent 0, 5 vs 3: sign*compare(5,3)=1*1=1 >0 → no swap. minHeap: [A(5), B(3)]: percolateUp with sign=-1: -1*compare(5,3) = -1 <0 → swap → minHeap: [B(3), A(5)], and cross-refs updated: otherHeap[heap[parentPtr].indexInOtherHeap].indexInOtherHeap = ptr etc.

Let me trace cross-refs for minHeap percolateUp: heap=minHeap, otherHeap=maxHeap, sign=-1, ptr=1. parentPtr=0. Condition: sign*compare(minHeap[0].priority=5, minHeap[1].priority=3) = -1*1 = -1 < 0 → enter. otherHeap[heap[parentPtr].indexInOtherHeap] = maxHeap[minHeap[0].indexInOtherHeap]. Initially when inserting: maxHeap[0]=A with indexInOtherHeap=0; maxHeap[1]=B with indexInOtherHeap=1; minHeap[0]=A indexInOtherHeap=0; minHeap[1]=B indexInOtherHeap=1. So: otherHeap[heap[0].indexInOtherHeap=0] = maxHeap[0] (A). A.indexInOtherHeap = 1. otherHeap[heap[1].indexInOtherHeap=1] = maxHeap[1] (B). B.indexInOtherHeap = 0. swap(minHeap,0,1) → minHeap=[B,A]. ptr=0, loop ends.

State: maxHeap=[A(p5,other=1), B(p3,other=0)], minHeap=[B(p3,other=0), A(p5,other=1)].

remove(): entryToRemove = minHeap[0] = B. minHeap[0] = minHeap[occupied-1] = minHeap[1] = A. maxHeap[minHeap[0].indexInOtherHeap=1] = maxHeap[1] = B; B.indexInOtherHeap = 0. minHeap[1] = null. occupied=1. siftDown(minHeap, ..., 0): leftChildPtr=1 >= occupied=1 → break. deletionIndexMaxHeap = B.indexInOtherHeap = 0. deletionIndexMaxHeap(0) == occupied(1)? No. maxHeap[0] = maxHeap[1] = B. minHeap[maxHeap[0].indexInOtherHeap=0] = minHeap[0] = A; A.indexInOtherHeap = 0. maxHeap[1] = null. occupied(1) <= 1 → return B. Correct: B had priority 3 < 5. 

The implementation looks correct for basic cases. There may be edge cases (e.g., peekRear after resize, ties), but tests will tell.

Now, about the truncated message. I think the right approach given the harness guidance is to ask the user to complete their thought, while I can simultaneously prepare. But tools don't let me both ask and proceed. Actually I could just write the files and then in my final message note the truncation and ask. That's efficient. The core ask is unambiguous enough: create StackTest.java, QueueTest.java, PriorityQueueTest.java with asserts, testing per the assignment PDF, without modifying existing code.

The most natural "make it such that it..." completions given the repo:
- "...runs with ./build and ./run_test" — my design satisfies this.
- "...it compiles with the build script" — satisfied (tests in src/ get compiled by `find src -name "*.java"`).
- "...uses JUnit" — not satisfied, but no JUnit lib present.

Hmm wait, there's a subtlety with the build script: `javac -cp "lib/*" -d out $(find src -name "*.java")`. If I add test files to src/, they get compiled together. The test classes have main methods — that's fine for compilation. The README says "Do not submit a main class" but that's about submission, and these test files are extra.

One concern: if the user submits all of src/, the test classes with main methods violate "Do not submit a main class". But the user explicitly asked for test files, so that's their call. I might mention it.

Also `run_test` takes `$1` as class name: `./run_test StackTest`. With -ea, asserts are enabled. 

Design decision: put tests in `src/` so `./build` compiles them. Each test file: package-private class (default package, matching the rest), `public static void main(String[] args)`, uses `assert` statements, prints "All StackTest tests passed." at the end. Use helper methods to reduce repetition. Also count assertions maybe.

Let me also double check the Queue.toString: elements joined with " " then ","... Actually looking carefully:

```java
for (int i = 0; i < occupied; i++) {
  if (i != 0) returnString = returnString.concat(" ");
  returnString = returnString.concat(arr[ptr] == null ? "null" : arr[ptr].toString());
  if (i != occupied - 1) returnString = returnString.concat(",");
  ptr = (ptr + 1) % arr.length;
}
```

For [1,2,3]: i=0: "1" + ","; i=1: " " + "2" + ","; i=2: " " + "3". → "[1, 2, 3]". Yes.

Stack.toString → CustomUtils.arrToString(arr, popIndex) → same format "[1, 2, 3]".

PriorityQueue.toString → "max heap: [...]\r\nmin heap: [...]\r\n". arrToString(maxHeap, occupied-1). Note: after removals, heap entries beyond occupied-1 are nulled, so arrToString over [0, occupied-1] shows valid entries. But wait — there's a subtlety: arrToString prints entries including Entry.toString() which is "Entry[value=..., priority=..., indexInOtherHeap=...]". I could test that toString contains the item values, but exact matching is fragile. I'll do a lighter check: toString() is non-null, contains "max heap:" and "min heap:". Actually for a fresh queue it's deterministic. Let me keep it simple: check contains "max heap:" and "min heap:".

Hmm, but actually, should I test toString exact format for Stack and Queue? It's implementation-defined but deterministic in their code. Testing it locks in the format. It's reasonable to test since the spec requires toString. I'll test the exact format "[1, 2, 3]" for both — it's deterministic and matches their implementation. If the user later changes format, they update the test.

Edge: empty stack toString → arrToString(arr, -1) → loop from 0 to -1 doesn't run → "[]". Queue empty → "[]". Nice edge cases to test.

Now the display() methods print to stdout — I can call them to ensure they don't throw, but asserting on stdout requires capture. I'll just call display() to ensure no exceptions (smoke test). Or skip. I'll include a call in a try/catch? No—just call it; if it throws, main crashes. Fine.

Let me now also handle: "don't modify any of my code" — I will only create the three new files. 

Now the truncated sentence. Options for the question tool:
- header: "Test setup"
- question: "Your message was cut off at 'and make it such that it…'. How should the tests be set up?"
- options:
  1. "Plain Java asserts + main (Recommended)" — "Runnable via ./build and ./run_test StackTest; no JUnit needed"
  2. "JUnit tests" — "Requires adding a JUnit jar to lib/"

Hmm, but maybe I should just proceed and ask at the end. The guidance: "Use this tool when you need to ask the user questions during execution." The truncated message genuinely needs completion. But asking blocks progress on an otherwise clear task. I think proceeding is better: the deliverable is clear, and my design choice (plain asserts + main) is the only sensible one given the repo's run_test script uses `java -ea`. Then in my summary, I'll note their sentence seemed cut off and invite them to finish it.

Actually, let me reconsider once more. The user's last words: "and make it such that it". Possibly they meant "make it such that it passes" (i.e., fix bugs?) — but they said don't modify my code. Or "make it such that it covers everything in the pdf". I'll cover everything thoroughly. Proceeding is right.

Plan:
1. Write src/StackTest.java
2. Write src/QueueTest.java
3. Write src/PriorityQueueTest.java
4. Run ./build
5. Run ./run_test StackTest, ./run_test QueueTest, ./run_test PriorityQueueTest (with -ea asserts enabled)
6. Report results; if any assert fails, report the failure (do NOT fix their code — they said don't modify; but I should tell them which tests fail and why).

Wait — important: if tests fail due to bugs in their code, what do I do? The user said don't modify code. So I report failures. Let me pre-verify the implementation mentally once more for the tricky parts to anticipate failures:

Queue circular wrap test: size 3. insert 1,2,3 → occupied=3, queueHead=0 (wrapped: after inserting at 0,1,2, queueHead=(2+1)%3=0), queueTail=0. remove() → 1, queueTail=1, occupied=2. insert 4 → arr[queueHead=0]=4, queueHead=1, occupied=3. remove() → 2 (queueTail=1), queueTail=2. remove() → 3, queueTail=0. remove() → 4, queueTail=1, occupied=0. FIFO order 1,2,3,4 ✓. This exercises wrap-around and also resize? occupied==arr.length triggers resize when inserting 4? occupied was 2 when inserting 4 (after one remove), so no resize. To test resize: insert 4 items into size-3 queue without removing: insert 1,2,3 (occupied=3), insert 4 → resize to 6, then arr[0..2] copied in order, queueTail=0, queueHead=3; insert at index 3, queueHead=4, occupied=4. remove → 1,2,3,4 ✓.

PriorityQueue resize test: size 2, insert several with distinct priorities, remove all, check ascending priority order. Trace: insert(A,5), insert(B,3), insert(C,8), insert(D,1) with initial size 2.
- insert A: occupied 0→1, maxHeap[0]=A(5), minHeap[0]=A(5). occupied<=1 return.
- insert B: occupied 1→2, maxHeap[1]=B(3), minHeap[1]=B(3). percolateUp maxHeap: parent 0: compare(5,3)=1, sign=1 → 1<0 false → no swap. percolateUp minHeap sign=-1: -1*compare(5,3)=-1<0 → swap → minHeap=[B,A], cross-refs: A.other=1, B.other=0.
- insert C: occupied==maxHeap.length(2) → resize both to 4. maxHeap=[A(5,other=1), B(3,other=0), null, null]; minHeap=[B(3,other=0), A(5,other=1), null, null]. occupied=3: maxHeap[2]=C(8,other=2), minHeap[2]=C(8,other=2). percolateUp maxHeap ptr=2: parent=0: compare(5,8)=-1, sign=1 → -1<0 → swap: cross updates: otherHeap=maxHeap's other is minHeap. otherHeap[heap[0].indexInOtherHeap=1]=minHeap[1]=A → A.other=2. otherHeap[heap[2].indexInOtherHeap=2]=minHeap[2]=C → C.other=0. swap(maxHeap,0,2) → maxHeap=[C(8,other=0), B(3,other=0), A(5,other=2)]. ptr=0 end. percolateUp minHeap sign=-1 ptr=2: parent=0: compare(minHeap[0].p=3, 8)=-1; -1*-1=1 <0 false → no swap.
- insert D(1): occupied 3→4 (occupied==length? 3==4 no). maxHeap[3]=D(1,other=3), minHeap[3]=D(1,other=3). occupied=4. percolateUp maxHeap ptr=3: parent=1: compare(3,1)=1 → no swap. percolateUp minHeap sign=-1 ptr=3: parent=1: compare(3,1)=1 → -1<0 → swap: otherHeap=maxHeap: maxHeap[minHeap[1].indexInOtherHeap=0]=C → C.other=3; maxHeap[minHeap[3].indexInOtherHeap=3]=D → D.other=1. swap(minHeap,1,3) → minHeap=[B(3,other=0), D(1,other=1), C(8,other=2), A(5,other=2)]... 

wait, minHeap[1] was A with other=2, now D goes there with other=1. Let me redo: before percolateUp, minHeap=[B(3,o=0), A(5,o=1), C(8,o=2), D(1,o=3)]. parentPtr=1. Swap condition: -1*compare(3,1) = -1 < 0 → enter. otherHeap[heap[parentPtr].indexInOtherHeap] = maxHeap[minHeap[1].indexInOtherHeap=1] = maxHeap[1] = B(3,o=0) → B.other = 3. otherHeap[heap[ptr].indexInOtherHeap] = maxHeap[minHeap[3].indexInOtherHeap=3] = maxHeap[3] = D → D.other = 1. swap → minHeap=[B(3,o=0), D(1,o=1), C(8,o=2), A(5,o=1)]. ptr=1, parentPtr=0. Condition: -1*compare(B.p=3, D.p=1) = -1 < 0 → enter. otherHeap[minHeap[0].o=0] = maxHeap[0] = C(8,o=0) → C.other = 1. otherHeap[minHeap[1].o=1] = maxHeap[1] = B(3,o=3) → B.other = 0. swap(minHeap,0,1) → minHeap=[D(1,o=1), B(3,o=0), C(8,o=2), A(5,o=1)]. ptr=0 end.

Final: maxHeap=[C(8,o=0), B(3,o=0), A(5,o=2), D(1,o=1)], minHeap=[D(1,o=1), B(3,o=0), C(8,o=2), A(5,o=1)].

Cross-ref check: A: in minHeap at 3, says o=1 → maxHeap[1]=B? ✗! A.indexInOtherHeap=1 but A is in maxHeap at index 2. Hmm wait, let me recheck. A was originally maxHeap[0], then swapped with C: maxHeap[0]=C, maxHeap[2]=A, and at that time A.other was set to 2. Then later in minHeap percolateUp, "otherHeap[minHeap[3].o=1] = maxHeap[1] = B → B.other=1" — wait I made an error above. Let me redo that step carefully.

State before insert D's percolateUp:
- maxHeap: [C(8,o=0), B(3,o=0), A(5,o=2), D(1,o=3)]

Hold on, C.other: during insert C's maxHeap percolateUp: otherHeap[heap[parentPtr].indexInOtherHeap] where heap=maxHeap, otherHeap=minHeap. minHeap[maxHeap[0].indexInOtherHeap]. At that time maxHeap[0]=A with A.other=1 (A was at minHeap[1] after B/A swap). So minHeap[1]=A → A.other=2 ✓ (A now at maxHeap[2]). And minHeap[maxHeap[2].indexInOtherHeap] = minHeap[C.other=2] = C → C.other=0 ✓ (C now at maxHeap[0]). So after insert C:
- maxHeap: [C(8,o=0), B(3,o=0), A(5,o=2), null]
- minHeap: [B(3,o=0), A(5,o=1), C(8,o=2), null]

Wait, B.other=0: B is at minHeap[0] and maxHeap[1]. B.other should be 1! Let me recheck insert B's percolateUp. Before: maxHeap=[A(5,o=0), B(3,o=1)], minHeap=[A(5,o=0), B(3,o=1)]. percolateUp(minHeap, maxHeap, -1, 1): parentPtr=0. otherHeap[heap[0].indexInOtherHeap] = maxHeap[A.other=0] = maxHeap[0] = A → A.other = ptr = 1 ✓ (A now at minHeap[1], maxHeap[0] → A.other=1 correct). otherHeap[heap[1].indexInOtherHeap] = maxHeap[B.other=1] = maxHeap[1] = B → B.other = parentPtr = 0 ✓ (B now at minHeap[0], maxHeap[1] → B.other=0 correct). swap → minHeap=[B(3,o=0), A(5,o=1)] ✓.

So after insert C: maxHeap=[C(8,o=0), B(3,o=0), A(5,o=2)], minHeap=[B(3,o=0), A(5,o=1), C(8,o=2)]. Cross-refs: C: minHeap[2], maxHeap[0], C.other=0 ✓. B: minHeap[0], maxHeap[1], B.other=0 ✗!! B.other should be 1!

Hmm wait, B.other=0 means "B's index in the other heap (maxHeap) is 0", but B is actually at maxHeap[1]. That's a bug?! Let me re-trace insert C's maxHeap percolateUp.

insert C: after placing C at maxHeap[2] with C.other=2 and minHeap[2] with C.other=2:
- percolateUp(maxHeap, minHeap, +1, 2): parentPtr = (2-1)/2 = 0. Condition: 1*compare(maxHeap[0].p=5, maxHeap[2].p=8) = -1 < 0 → enter.
  - otherHeap[heap[parentPtr].indexInOtherHeap].indexInOtherHeap = ptr → otherHeap = minHeap; heap[0] = A (maxHeap[0]=A); A.indexInOtherHeap = 1 → minHeap[1] = A → set A.other = 2. ✓ (A moves to maxHeap[2])
  - otherHeap[heap[ptr].indexInOtherHeap].indexInOtherHeap = parentPtr → heap[2] = C; C.other = 2 → minHeap[2] = C → set C.other = 0 ✓ (C moves to maxHeap[0])
  - swap(maxHeap, 0, 2) → maxHeap = [C(8,o=0), B(3,o=0), A(5,o=2)]
  - ptr = 0, loop ends.

B.other is still 0 — but B is at maxHeap[1] and minHeap[0]. B.other should be 1. Wait, was B.other ever correct? After insert B: B at minHeap[0], maxHeap[1], B.other=0. B.other=0 means B's index in maxHeap is 0 — but B is at maxHeap[1]! 

Hmm, that seems wrong. Let me recheck insert B again very carefully.

insert(B, 3):
- occupied: 1 → 2. maxHeap[1] = new Entry(B, 3, 1) — indexInOtherHeap = occupied = 1. minHeap[1] = new Entry(B, 3, 1).
- percolateUp(maxHeap, minHeap, +1, 1): parentPtr=0. Condition: compare(maxHeap[0].p=5, maxHeap[1].p=3) = 1; 1*1 = 1 < 0? No → no swap. OK.
- percolateUp(minHeap, maxHeap, -1, 1): parentPtr=0. Condition: -1 * compare(minHeap[0].p=5, minHeap[1].p=3) = -1*1 = -1 < 0 → enter.
  - otherHeap = maxHeap. heap[parentPtr] = minHeap[0] = A, A.other = 0. maxHeap[0] = A → set A.other = ptr = 1. ✓ (A will move to minHeap[1])
  - heap[ptr] = minHeap[1] = B, B.other = 1. maxHeap[1] = B → set B.other = parentPtr = 0. ✗?!

Wait — B is at maxHeap[1] and stays at maxHeap[1] (no swap in maxHeap happened). Setting B.other = 0 is WRONG. B should have other=1.

Hmm wait, no. Let me re-read the percolateUp logic:

```java
otherHeap[heap[parentPtr].indexInOtherHeap].indexInOtherHeap = ptr;
otherHeap[heap[ptr].indexInOtherHeap].indexInOtherHeap = parentPtr;
swap(heap, parentPtr, ptr);
```

The intent: after swapping heap[parentPtr] and heap[ptr], the entry formerly at heap[parentPtr] is now at index ptr in `heap`, so its index in the OTHER heap... no wait. Hmm. The cross-reference: each Entry appears in both heaps. Entry.indexInOtherHeap = its index in the other heap. When we swap two entries in `heap`, their positions in `heap` change, but their positions in `otherHeap` stay the same. So why update otherHeap entries' indexInOtherHeap?

Hmm, I think I'm misunderstanding. Let me re-think. Actually the cross-referencing here: when we swap heap[parentPtr] and heap[ptr] in `heap`, the entries' positions in `heap` change. The `otherHeap` contains the SAME Entry objects (same references). Entry.indexInOtherHeap is supposed to track the entry's index in `otherHeap`. Swapping within `heap` does NOT change positions in otherHeap. So why update?

Oh wait — I see. The update is: `otherHeap[heap[parentPtr].indexInOtherHeap].indexInOtherHeap = ptr`. This finds, in otherHeap, the entry that currently sits at the position where heap[parentPtr] used to be in otherHeap... no. Let me parse: heap[parentPtr] is entry X. X.indexInOtherHeap = i, meaning X sits at otherHeap[i]. otherHeap[i] IS X (same object). Setting otherHeap[i].indexInOtherHeap = ptr sets X's other-heap index to ptr. But X hasn't moved in otherHeap! X moved in `heap` (from parentPtr to ptr).

Hmm, this seems confused. Unless... the convention is different: maybe indexInOtherHeap for an entry in heap `H` means its index in the other heap, and when we swap in `heap`, we need to update the OTHER references. Let me think about what invariant should hold:

Invariant: for every item, the Entry object in maxHeap at position i and the Entry object in minHeap at position j are DIFFERENT objects (two Entry instances per item — see insert: `maxHeap[occupied] = new Entry<>(...)` and `minHeap[occupied] = new Entry<>(...)` — two separate Entry objects!). 

So each item has TWO Entry objects: one in maxHeap, one in minHeap. The maxHeap entry's indexInOtherHeap = index in minHeap where the minHeap entry for the same item lives, and vice versa.

Now when we swap entries in `heap` (say minHeap), the entries move within minHeap. Their indexInOtherHeap (position in maxHeap) doesn't change. BUT the maxHeap entries that point to them... no wait, the maxHeap entries' indexInOtherHeap point to positions in minHeap, and those positions now hold different entries.

Hmm, so actually when swapping minHeap[i] and minHeap[j]:
- Entry X (item x) moves from minHeap[i] to minHeap[j]. The maxHeap twin of x (call it X') has X'.indexInOtherHeap = i → must become j.
- Similarly for Y.

How does the code do it? `otherHeap[heap[parentPtr].indexInOtherHeap].indexInOtherHeap = ptr`:
- heap = minHeap, otherHeap = maxHeap.
- heap[parentPtr] = X (the minHeap entry of item x). X.indexInOtherHeap = position of X' in maxHeap, say p.
- otherHeap[p] = X' (the maxHeap twin). Setting X'.indexInOtherHeap = ptr = j. ✓ Correct! X' now points to j, which is where X will be after the swap.
- Second line: heap[ptr] = Y, Y.indexInOtherHeap = q (position of Y' in maxHeap). otherHeap[q] = Y' → Y'.indexInOtherHeap = parentPtr = i ✓.

So the logic is correct. My trace error: in insert B, percolateUp(minHeap, maxHeap, -1, 1):
- heap[parentPtr] = minHeap[0] = A. A.indexInOtherHeap = 0 (A's maxHeap twin at maxHeap[0]). otherHeap[0] = maxHeap[0] = A' → A'.indexInOtherHeap = 1. ✓ (A's minHeap twin will be at minHeap[1] after swap)
- heap[ptr] = minHeap[1] = B. B.indexInOtherHeap = 1 (B's twin at maxHeap[1]). otherHeap[1] = maxHeap[1] = B' → B'.indexInOtherHeap = 0 ✓ (B's minHeap twin will be at minHeap[0] after swap).
- swap(minHeap, 0, 1) → minHeap = [B, A].

So B'.indexInOtherHeap = 0 means B's maxHeap twin points to minHeap position 0, where B's minHeap twin now lives ✓. And A'.indexInOtherHeap = 1: A's maxHeap twin at maxHeap[0] points to minHeap[1] where A's twin lives ✓.

I confused myself earlier — B.other=0 is CORRECT because it means "the twin of B (in maxHeap) is at maxHeap... no wait. B in minHeap at position 0; B.indexInOtherHeap = 0 means B's twin is at maxHeap[0]? But B's twin is at maxHeap[1]!

Ugh, hold on. B (minHeap entry) has indexInOtherHeap = 0 after the update. That means: B's twin in the other heap (maxHeap) is at index 0. But B's twin is at maxHeap[1]! That's wrong?!

Wait, no. Let me recompute. Before percolateUp, maxHeap = [A'(5,o=0), B'(3,o=1)], minHeap = [A(5,o=0), B(3,o=1)]. Here A' is the maxHeap Entry for item A, A is the minHeap Entry for item A. A'.indexInOtherHeap = 0 → A' points to minHeap[0] = A ✓. A.indexInOtherHeap = 0 → A points to maxHeap[0] = A' ✓. B'.indexInOtherHeap = 1 → B' points to minHeap[1] = B ✓. B.indexInOtherHeap = 1 → B points to maxHeap[1] = B' ✓.

percolateUp(minHeap, maxHeap, -1, 1):
- Line 1: otherHeap[heap[parentPtr].indexInOtherHeap].indexInOtherHeap = ptr.
  - heap[parentPtr] = minHeap[0] = A. A.indexInOtherHeap = 0. otherHeap[0] = maxHeap[0] = A'. A'.indexInOtherHeap = 1.
  - Now A' points to minHeap[1]. After the swap, minHeap[1] = A ✓.
- Line 2: otherHeap[heap[ptr].indexInOtherHeap].indexInOtherHeap = parentPtr.
  - heap[ptr] = minHeap[1] = B. B.indexInOtherHeap = 1. otherHeap[1] = maxHeap[1] = B'. B'.indexInOtherHeap = 0.
  - B' now points to minHeap[0]. After swap, minHeap[0] = B ✓.
- swap(minHeap, 0, 1): minHeap = [B(o=1), A(o=0)].

Wait — the Entry objects themselves are swapped! So minHeap[0] is now the B object, whose indexInOtherHeap field is still 1 (it was set to 1 at insertion and NOT modified by percolateUp — percolateUp modified A' and B', the maxHeap twins, not A and B themselves!).

Oh! I see — I conflated. The percolateUp updates the TWINS in otherHeap, not the entries being swapped. The entries being swapped (A and B in minHeap) keep their own indexInOtherHeap values, which still correctly point to their twins' positions in maxHeap (A→maxHeap[0] ✓, B→maxHeap[1] ✓). And the twins A' and B' got updated to point to the new minHeap positions (A'→minHeap[1] ✓, B'→minHeap[0] ✓).

So after insert B: minHeap = [B(o=1), A(o=0)], maxHeap = [A'(o=1), B'(o=0)].

Now my earlier trace of insert C: percolateUp(maxHeap, minHeap, +1, 2):
- heap[parentPtr] = maxHeap[0] = A'. A'.indexInOtherHeap = 1 → minHeap[1] = A → A.indexInOtherHeap = 2 ✓ (A will be at maxHeap[2] after swap — wait, A.indexInOtherHeap points to maxHeap position; A will move to maxHeap[2] ✓).
- heap[ptr] = maxHeap[2] = C'. C'.indexInOtherHeap = 2 → minHeap[2] = C → C.indexInOtherHeap = 0 ✓.
- swap(maxHeap, 0, 2) → maxHeap = [C'(o=0), B'(o=0), A'(o=2)].

Hmm, C'.indexInOtherHeap = 0 means C' points to minHeap[0]. But C's minHeap twin is at minHeap[2], not minHeap[0]! ✗

Wait, I need to redo this. Line 2 sets `otherHeap[heap[ptr].indexInOtherHeap].indexInOtherHeap = parentPtr`. heap[ptr] = C' (maxHeap entry). C'.indexInOtherHeap = 2 (C' points to minHeap[2] = C). otherHeap[2] = minHeap[2] = C. So C.indexInOtherHeap = parentPtr = 0. C is the minHeap entry; C.indexInOtherHeap = 0 means C's twin is at maxHeap[0]. After the swap, maxHeap[0] = C' ✓. Correct!

And line 1: A'.indexInOtherHeap was 1 (points to minHeap[1] = A). otherHeap[1] = A. A.indexInOtherHeap = ptr = 2. A is minHeap entry; A.indexInOtherHeap = 2 means A's twin at maxHeap[2]. After swap, maxHeap[2] = A' ✓.

So after insert C: maxHeap = [C'(o=0), B'(o=0), A'(o=2)], minHeap = [B(o=1), A(o=0), C(o=2)].

Check all cross-refs:
- maxHeap[0] = C', C'.o=0 → minHeap[0] = B ✗!! C' should point to C which is at minHeap[2]!

Hmm! C'.indexInOtherHeap = 0 — but C's minHeap twin is at minHeap[2]. That's wrong!

Wait, did I mess up? Let me redo. At insert C, before percolateUp: maxHeap = [A'(o=1), B'(o=0), C'(o=2)], minHeap = [B(o=1), A(o=0), C(o=2)].

Check: A'.o=1 → minHeap[1] = A ✓. B'.o=0 → minHeap[0] = B ✓. C'.o=2 → minHeap[2] = C ✓. B.o=1 → maxHeap[1] = B' ✓. A.o=0 → maxHeap[0] = A' ✓. C.o=2 → maxHeap[2] = C' ✓. All consistent.

percolateUp(maxHeap, minHeap, +1, ptr=2): parentPtr = 0. Condition: +1 * compare(A'.p=5, C'.p=8) = -1 < 0 → enter.
- Line 1: otherHeap[heap[parentPtr].indexInOtherHeap].indexInOtherHeap = ptr.
  - heap[parentPtr] = maxHeap[0] = A'. A'.indexInOtherHeap = 1. otherHeap[1] = minHeap[1] = A. A.indexInOtherHeap = 2.
  - A (minHeap entry) now points to maxHeap[2]. After swap, maxHeap[2] = A' ✓.
- Line 2: otherHeap[heap[ptr].indexInOtherHeap].indexInOtherHeap = parentPtr.
  - heap[ptr] = maxHeap[2] = C'. C'.indexInOtherHeap = 2. otherHeap[2] = minHeap[2] = C. C.indexInOtherHeap = 0.
  - C (minHeap entry) now points to maxHeap[0]. After swap, maxHeap[0] = C' ✓.
- swap(maxHeap, 0, 2) → maxHeap = [C'(o=2), B'(o=0), A'(o=1)].

The swap swaps the Entry objects! So C' (with its indexInOtherHeap field = 2) moves to maxHeap[0]. C'.o=2 → minHeap[2] = C ✓. A' moves to maxHeap[2], A'.o=1 → minHeap[1] = A ✓. B' stays at maxHeap[1], B'.o=0 → minHeap[0] = B ✓.

I made an error before by writing maxHeap = [C'(o=0), ...] — the C' object retains o=2, not 0. The o=0 was written to C (the minHeap twin). 

So after insert C: maxHeap = [C'(o=2), B'(o=0), A'(o=1)], minHeap = [B(o=1), A(o=0), C(o=2)]. All consistent ✓.

OK so the implementation's cross-referencing is correct. My mental trace was error-prone; good thing I'll just run the tests.

Continue insert D(1): occupied=3 < 4, no resize. maxHeap[3] = D'(o=3), minHeap[3] = D(o=3). occupied=4.
- percolateUp(maxHeap, minHeap, +1, 3): parentPtr=1. compare(B'.p=3, D'.p=1) = 1 → 1*1 = 1 < 0? No → no swap.
- percolateUp(minHeap, maxHeap, -1, 3): parentPtr=1. compare(minHeap[1].p = A.p = 5, D.p=1) = 1 → -1 < 0 → enter.
  - Line 1: heap[parentPtr] = minHeap[1] = A. A.o=1 → otherHeap[1] = maxHeap[1] = B'. B'.o = 3.
  - Line 2: heap[ptr] = minHeap[3] = D. D.o=3 → otherHeap[3] = maxHeap[3] = D'. D'.o = 1.
  - swap(minHeap, 1, 3) → minHeap = [B(o=1), D(o=3), C(o=2), A(o=0)]. ptr=1.
  - parentPtr=0. compare(minHeap[0].p=B.p=3, minHeap[1].p=D.p=1) = 1 → -1 < 0 → enter.
  - Line 1: heap[0] = B. B.o=1 → maxHeap[1] = B' → B'.o = 1. Wait, B.o is B's index in other heap (maxHeap) = 1. otherHeap[1] = maxHeap[1] = B'. B'.indexInOtherHeap = ptr = 1. Hmm, B'.o was 3, now set to 1. B' points to minHeap[1]. After swap, minHeap[1] = B ✓.
  - Line 2: heap[ptr] = minHeap[1] = D. D.o = 3 → maxHeap[3] = D'. D'.o = 0. D' points to minHeap[0]. After swap, minHeap[0] = D ✓.
  - swap(minHeap, 0, 1) → minHeap = [D(o=3), B(o=1), C(o=2), A(o=0)]. ptr=0. End.

Final state: maxHeap = [C'(o=2), B'(o=1), A'(o=1), D'(o=1)], minHeap = [D(o=3), B(o=1), C(o=2), A(o=0)].

Check consistency:
- maxHeap[0] = C', C'.o=2 → minHeap[2] = C ✓; C.o=2 → maxHeap[2] = A' ✗!! C.o should point to C' at maxHeap[0], but C.o=2 → maxHeap[2] = A'. BUG?

Hmm wait. C.o was set to 0 during insert C's percolateUp line 2: "C.indexInOtherHeap = parentPtr = 0". Then later, during insert D's percolateUp... did anything touch C? Line 1 of second iteration: heap[parentPtr] = minHeap[1] = D (after first swap, minHeap[1] = D). Not C. Hmm, so where did C.o become 2?

Let me redo. After insert C: minHeap = [B(o=1), A(o=0), C(o=2)]. C.o = 2 → maxHeap[2] = A'?? That's wrong already!

Wait, I think I messed up again. After insert C's percolateUp line 2: C.indexInOtherHeap = parentPtr = 0. So C.o = 0, pointing to maxHeap[0] = C' ✓. I wrote "minHeap = [B(o=1), A(o=0), C(o=2)]" — that's wrong; C.o should be 0.

Ugh, my manual tracing is too error-prone. The objects' fields: C (minHeap entry) has indexInOtherHeap = 0 after the update. The swap only moves objects around; fields stay with objects. So minHeap = [B(o=1), A(o=0), C(o=0)]? But wait, A.o: during insert C line 1, A.indexInOtherHeap = 2. So minHeap = [B(o=1), A(o=2), C(o=0)].

Check: A.o=2 → maxHeap[2] = A' ✓. C.o=0 → maxHeap[0] = C' ✓. B.o=1 → maxHeap[1] = B' ✓. maxHeap = [C'(o=2), B'(o=0), A'(o=1)]: C'.o=2 → minHeap[2] = C ✓. B'.o=0 → minHeap[0] = B ✓. A'.o=1 → minHeap[1] = A ✓. All consistent ✓.

OK. I clearly can't reliably hand-trace this. The tests will be the judge. Let me just write comprehensive tests and run them. If failures occur, I'll report them to the user (without modifying their code).

One more thing to verify: PriorityQueue.peekRear returns maxHeap[0].item — the item with the LARGEST priorityValue (lowest priority). Is that the intended "rear"? The assignment says "peekRear" — presumably the last item to be removed, i.e., the one with the lowest priority (largest priorityValue). The implementation's comment says "assume that the front of the queue is the first thing we will pop". So rear = last to be popped = max priorityValue ✓.

For duplicate priorityValues in PriorityQueue: remove order among ties is unspecified by the assignment. My tests should use distinct priorities for exact-order assertions, and for tie cases just assert the multiset of items or the non-decreasing priority sequence. To check the sequence of priorities, I need to know priorities of removed items — I can use distinguishable item values mapped to priorities, e.g., insert strings with priorities, and assert the removal order of items where priorities are distinct. For ties, use items like "A" and "B" both priority 5 — either order is valid; assert that the removed item is one of them, and that the overall priority sequence is non-decreasing. Simpler: test with distinct priorities only for order, plus a tie test that removes all and checks the sequence of priorities is non-decreasing (using a small custom struct... but T must be Comparable; I can use Integer items where item value encodes priority? No — item and priority are separate. I could use String items and a parallel map... simpler: use Integer items and priorities where item == priority. Then removal order should be exactly sorted ascending. That tests both ties and distinct values: insert (3,3), (1,1), (2,2), (1,1)... with ties, the sequence must be non-decreasing but not uniquely determined. For items equal to priorities and distinct priorities, exact order = sorted. For ties, just assert non-decreasing.)

Also test: insert into PriorityQueue beyond capacity (resize), interleaved insert/remove, remove until empty then null, single element.

Also — potential real bug: PriorityQueue.remove when occupied becomes 0 mid-sequence... covered by "remove until empty".

Another potential bug area: Queue.peekRear when queueHead == 0: frontIndex = (queueHead - 1) % arr.length → (0-1) % 5 = -1 in Java! Negative index → ArrayIndexOutOfBoundsException! Let me check: peekRear: `int frontIndex = (queueHead - 1) % arr.length;` If queueHead == 0 (wrapped around), (0-1) % length = -1 → arr[-1] → AIOOBE. This is a real bug when the queue is non-empty and queueHead has wrapped to 0!

When does queueHead become 0? After filling the queue completely (occupied == arr.length) without resize... but resize triggers when occupied == arr.length on insert. So queueHead == 0 with occupied < arr.length: e.g., size 5, insert 5 items → occupied=5, queueHead=0. But wait, the 5th insert: occupied was 4 before, 4 != 5, so no resize; after insert, occupied=5, queueHead=(4+1)%5=0. Now peekRear: occupied=5 != 0, frontIndex = (0-1)%5 = -1 → AIOOBE! 

So a full queue (exactly at capacity, before the next insert triggers resize) crashes on peekRear. My tests should catch this! Let me include: Queue<Integer>(3); insert 1,2,3 (fills exactly); peekFront()==1; peekRear() → crash. Hmm, this is a genuine bug in their code. The test will fail with an exception. I should include this test because the spec says peekRear must work; the failure reveals a bug. I'll report it to the user without fixing (they said don't modify my code).

Hmm, but wait — should I include tests that I know will fail due to bugs? Yes — that's the point of testing against the spec. The user asked to test "whatever is written in assignment.pdf". The spec requires peekRear to work. If it crashes, the test fails and the user learns about the bug. I must NOT fix their code per instructions, but I should clearly report.

Let me also check Stack for similar issues — Stack looks straightforward. resizeArray uses Arrays.copyOf ✓.

Queue resize: resize(getNextResize(arr)) = double. In resize, newArr copies occupied elements starting from queueTail, sets queueTail=0, queueHead=occupied ✓. Then insert proceeds: arr[queueHead] = newItem ✓.

Queue toString after wrap: fine.

PriorityQueue remove edge: when occupied==1 after decrement and deletionIndexMaxHeap != occupied... covered.

Another PriorityQueue edge: remove() when occupied==2. Let me trust tests.

Also potential bug in PriorityQueue.remove: `minHeap[0] = minHeap[occupied - 1];` then `maxHeap[minHeap[0].indexInOtherHeap].indexInOtherHeap = 0;` — if occupied==1, minHeap[0] = itself, and its twin's indexInOtherHeap set to 0 ✓ fine.

But there's a subtle issue: after moving last element to root in minHeap, the twin in maxHeap gets indexInOtherHeap = 0. Then siftDown(minHeap, ...) may swap in minHeap and update maxHeap twins' cross-refs ✓. Then the removal from maxHeap: maxHeap[deletionIndexMaxHeap] = maxHeap[occupied]; minHeap[maxHeap[deletionIndexMaxHeap].indexInOtherHeap].indexInOtherHeap = deletionIndexMaxHeap; then percolateUp or siftDown on maxHeap ✓. Looks right.

Edge: what if the item removed is at maxHeap[occupied] (last position)? Handled by the early return ✓.

What if occupied == 2 and we remove? entryToRemove = minHeap[0]. minHeap[0] = minHeap[1]. maxHeap[minHeap[0].o].o = 0. occupied=1. siftDown: leftChild=1 >= 1 → break. deletionIndexMaxHeap = entryToRemove.o. If deletionIndexMaxHeap == 1 (== occupied): maxHeap[1] = null, return ✓. Else deletionIndexMaxHeap == 0: maxHeap[0] = maxHeap[1]; minHeap[maxHeap[0].o].o = 0; maxHeap[1] = null; occupied(1) <= 1 → return ✓.

OK. Now let me also think: does `PriorityQueue.insert` handle occupied==maxHeap.length resize BEFORE placing? Yes ✓.

Alright, let me now also read the remaining source files quickly (StringReverser, DelimiterChecker, UsingStringReverser, UsingDelimiterChecker, GenericArray, OrderedArray, UnorderedArray) — not strictly needed for the three test files requested, but StringReverser/DelimiterChecker use Stack; the user only asked for the three test files. Skip.

Now, the test file design. Since classes are in the default package and package-private, test classes in default package can access them. Test class names: StackTest, QueueTest, PriorityQueueTest. Each with main method. Use `assert` with messages? Java assert supports messages: `assert condition : "message"`. With -ea, failures throw AssertionError with the message. Good for debugging.

I'll also add a simple test counter and print a summary. Keep style consistent with the codebase (2-space indent, Google-ish Java style).

Note: the build script compiles everything in src/, so test files must compile cleanly with the existing code. Since Stack/Queue/PriorityQueue are package-private classes in default package, StackTest in default package can instantiate them ✓.

Let me write the tests:

```java
class StackTest {
  private static int testsRun = 0;

  private static void check(boolean condition, String message) {
    testsRun++;
    assert condition : "FAILED: " + message;
  }

  public static void main(String[] args) {
    testEmptyStackReturnsNull();
    testPushPeekDoesNotRemove();
    testLifoOrder();
    testInterleavedPushPop();
    testAutoResize();
    testToString();
    testGenericStringStack();
    testDisplayDoesNotThrow();

    System.out.printf("StackTest: all %d checks passed.%n", testsRun);
  }
  ...
}
```

Hmm, one design question: `check` increments testsRun then asserts. If assert disabled (run without -ea), tests silently pass — that's why run_test uses -ea. I could also make check throw explicitly... but user asked for asserts. I'll use assert and note that -ea is required (run_test already does that). Actually, to be safe, maybe assert directly plus a throw? No — keep it pure assert as requested. But I'll mention in the summary to use ./run_test (which has -ea).

Alternatively, make check do: `if (!condition) throw new AssertionError(...)`. But user explicitly said "using asserts". Use `assert`.

Test details:

StackTest:
1. testEmptyStackReturnsNull: new Stack<Integer>(5); assert pop() == null; assert peek() == null; assert toString().equals("[]").
2. testPushPeek: push 42; peek()==42; peek again ==42 (still there); pop()==42; then peek()==null.
3. testLifoOrder: push 1,2,3; pop 3,2,1; pop null.
4. testInterleaved: push 1,2; pop==2; push 3; pop==3; pop==1.
5. testAutoResize: new Stack<Integer>(2); push 1..10; pop 10..1 in order.
6. testToString: push 1,2,3 → "[1, 2, 3]"; pop one → "[1, 2]"; pop all → "[]".
7. testGenericStringStack: Stack<String>(3); push "a","b","c","d" (resize); peek "d"; pop d,c,b,a.
8. testResizePreservesPeek: size 1, push 1, peek 1, push 2, peek 2, pop 2, pop 1.
9. display smoke: call display() (prints) — just ensure no exception. Maybe skip display to keep output clean? It's fine, it prints. I'll include it in one test.

QueueTest:
1. testEmptyQueueReturnsNull: remove()==null, peekFront()==null, peekRear()==null, toString()=="[]".
2. testFifoOrder: insert 1,2,3; remove 1,2,3; remove null.
3. testPeekFrontAndRear: insert 1,2,3; peekFront==1; peekRear==3; still remove 1,2,3 (peeks don't remove).
4. testCircularWrapAround: size 3; insert 1,2,3; remove 1; insert 4; remove 2,3,4. (queueTail wraps to 1, queueHead wraps)
   - Actually after remove 1: queueTail=1, occupied=2. insert 4: occupied(2) != 3, no resize; arr[queueHead=0]=4, queueHead=1, occupied=3. remove → arr[1]=2 ✓. remove → arr[2]=3 ✓. remove → arr[0]=4 ✓. 
5. testAutoResize: size 2; insert 1,2,3,4,5; remove 1,2,3,4,5 in order. (resize triggers on 3rd insert)
6. testResizeAfterWrap: size 3; insert 1,2,3; remove 1,2 (queueTail=2, queueHead=0); insert 4,5,6,7 → insert 4: occupied=1... wait after removing 2, occupied=1, queueTail=2, queueHead=0. insert 4: arr[0]=4, queueHead=1, occupied=2. insert 5: arr[1]=5, queueHead=2, occupied=3. insert 6: occupied==3 → resize to 6: copies from queueTail=2: arr[2]=3, arr[0]=4, arr[1]=5 → newArr=[3,4,5], queueTail=0, queueHead=3. Then arr[3]=6, queueHead=4, occupied=4. insert 7: arr[4]=7, occupied=5. remove → 3,4,5,6,7 ✓. This exercises resize with a wrapped queue — good test of their resize logic.
7. testToString: insert 1,2,3 → "[1, 2, 3]"; remove one → "[2, 3]".
8. testFullQueuePeekRear: size 3, insert 1,2,3 → peekRear — this will crash with AIOOBE due to the (queueHead-1)%length = -1 bug. Hmm. Should I include it? It's a legit spec test. If I include it, the test suite fails. The user wants to find bugs presumably. But it makes the whole suite fail at that point (exception propagates, remaining tests don't run). I could catch the exception and assert false with a message... but then it's still a failure. I think I should include it — it's a genuine spec violation. But maybe put it near the end so other tests run first. And in my summary, clearly explain the bug: peekRear crashes when the queue is exactly full (queueHead wrapped to 0), because (queueHead - 1) % arr.length == -1 in Java.

Actually wait — let me double check the bug. Queue(size 3), insert 1,2,3:
- insert 1: occupied 0→1, arr[0]=1, queueHead=1.
- insert 2: arr[1]=2, queueHead=2, occupied=2.
- insert 3: occupied 2 != 3 → no resize. arr[2]=3, queueHead=(2+1)%3=0, occupied=3.
- peekRear: occupied=3 ≠ 0. frontIndex = (queueHead - 1) % arr.length = (0-1) % 3 = -1 % 3 = -1 (Java). arr[-1] → ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 3.

Yes, confirmed bug. The correct formula would be (queueHead - 1 + arr.length) % arr.length.

Also peekRear after remove-then-empty... covered by empty test.

Also what about peekRear when queueHead=0 but queue not full? Can that happen? queueHead=0 with occupied < length: e.g., after resize, queueHead=occupied which is < new length... queueHead=0 only via wrap when fully full, or... insert always sets queueHead=(queueHead+1)%length; queueHead=0 requires previous queueHead=length-1, meaning it was full before insert... but resize prevents inserting when full — resize happens when occupied==length, and resize sets queueHead=occupied=length (old), then insert does arr[queueHead]=..., queueHead=(old+1)%newLength. Hmm, so queueHead=0 can also occur when newLength == old occupied+1? newLength = 2*oldLength > oldLength = old occupied ≥ old occupied+1... no, 2*oldLength > oldLength+1 for oldLength>1. So queueHead=0 only when the queue is exactly full (occupied == length). OK.

So the bug manifests exactly when the queue is full. Test: fill to capacity, peekRear should return last inserted. I'll include it and report the bug.

PriorityQueueTest:
1. testEmptyReturnsNull: remove/peekFront/peekRear null.
2. testRemoveSmallestPriorityFirst: insert (A,5),(B,1),(C,3); remove → B(1), C(3), A(5).
3. testPeekFrontAndRear: insert (A,5),(B,1),(C,3): peekFront==B (smallest priorityValue); peekRear==A (largest priorityValue); peeks don't remove: remove B, C, A.
4. testInsertOrderIndependence: insert in different order, same removal sequence.
5. testAutoResize: size 2, insert 6 items with distinct priorities, remove in ascending priority order.
6. testInterleavedInsertRemove: insert A(2), B(1); remove B; insert C(0), D(5); remove C(0), A(2), D(5); remove null.
7. testSingleElement: insert X(10); peekFront=X; peekRear=X; remove X; remove null.
8. testDuplicatePriorities: insert items with same priority; remove all; assert priorities non-decreasing. Using Integer items where value==priority: insert (3,3),(1,1),(2,2),(1,1),(2,2): remove all 5, collect values, assert sequence is sorted non-decreasing. With ties, the heap order among equal priorities isn't specified, so only assert non-decreasing.
9. testToStringContainsHeaps: toString contains "max heap:" and "min heap:".
10. testManyElements: size 4, insert 0..19 with shuffled priorities (e.g., priority = (i*7)%20 distinct), remove all, assert ascending.
11. testNegativeAndZeroPriorities: priorities can be negative/zero: insert (A,0),(B,-5),(C,10) → remove B, A, C.

For test 10, priorities distinct: use priority = 19 - i for i in 0..19 (descending insert, ascending remove).

Also for PriorityQueue, items must be Comparable — Integer/String fine.

Now, about ties in remove order — the implementation might actually return them in some order; my non-decreasing assertion is safe.

One more check on PriorityQueue.remove with ties — could the cross-ref bookkeeping break with equal priorities? percolateUp condition uses strict <, so equal priorities don't swap ✓. siftDown: `sign * compare(...) >= 0 → break`, so equals don't swap ✓. Removal with ties: the twin updates are structural, not priority-dependent ✓.

Let me also think about whether `remove()` on PriorityQueue when there are 2 elements with the max one being removed... covered by tests.

Now — the run_test script: `java -ea -cp "out:lib/*" $1`. So `./run_test StackTest`. Build first: `./build` → compiles all src/*.java to out/. There's also an `out/` dir already present with possibly stale classes; build will overwrite.

Let me check what's in out/ currently — not important, build will regenerate.

Also note: `lib/` doesn't exist; classpath entry "out:lib/*" — javac/java tolerate nonexistent classpath entries? For java, a nonexistent classpath entry is fine (ignored). The existing scripts presumably work.

Edge: Java version? Let me check `java -version` when running. The code uses `\r\n` in printf — fine.

Now write the files. Style: match existing code — 2-space indentation, class without public modifier, no package. Add comments referencing assignment spec items (like 6.b, 7.c etc.) — nice touch consistent with codebase style which references "6.a", "7.b" etc.

Let me write StackTest.java:

```java
/**
 * Unit tests for the Stack class, based on the requirements in
 * assignment_details.pdf (section 6).
 *
 * Run with assertions enabled, e.g.:
 *   ./build && ./run_test StackTest
 */
class StackTest {
  private static int testsRun = 0;

  private static void check(boolean condition, String message) {
    testsRun++;
    assert condition : message;
  }

  public static void main(String[] args) {
    testPopAndPeekOnEmptyStackReturnNull();
    testPushAndPeek();
    testLifoOrder();
    testInterleavedPushAndPop();
    testAutoResize();
    testToString();
    testGenericWithStringItems();
    testDisplayRuns();

    System.out.printf("StackTest: all %d checks passed.%n", testsRun);
  }

  // 6.c pop() on an empty stack should return null
  private static void testPopAndPeekOnEmptyStackReturnNull() {
    Stack<Integer> stack = new Stack<Integer>(5);
    check(stack.pop() == null, "pop() on an empty stack should return null");
    check(stack.peek() == null, "peek() on an empty stack should return null");
    // popping again should still return null
    check(stack.pop() == null, "pop() should keep returning null when empty");
  }

  // 6.b/6.d push then peek, peek must not remove the item
  private static void testPushAndPeek() {
    Stack<Integer> stack = new Stack<Integer>(5);
    stack.push(42);
    check(stack.peek() == 42, "peek() should return the last pushed item");
    check(stack.peek() == 42, "peek() should not remove the item");
    check(stack.pop() == 42, "pop() should return the item that peek() showed");
    check(stack.peek() == null, "peek() should return null after popping the only item");
  }
  ...
}
```

Wait — `stack.peek() == 42` with Integer autoboxing: Integer(42) == int 42 → unboxes, fine. But comparing two Integers with == would be reference comparison — avoid `stack.peek() == stack.peek()`. Use .equals or compare to literals. `check(stack.peek().equals(42), ...)` — but if peek returns null, NPE. In tests where non-null expected, .equals is fine. Or use `int x = stack.peek(); check(x == 42, ...)` — unboxing, NPE if null but that's a test failure anyway. I'll use direct == against int literals (unboxing), which is clean.

For null checks, `== null` fine.

Queue toString test: after insert 1,2,3 → "[1, 2, 3]". After removing 1 → "[2, 3]".

PriorityQueue toString: format is "max heap: [...]\r\nmin heap: [...]\r\n" where entries are Entry[...] strings. For a fresh insert of (A,5) only: "max heap: [Entry[value=A, priority=5, indexInOtherHeap=0]]\r\nmin heap: [Entry[value=A, priority=5, indexInOtherHeap=0]]\r\n". I'll just check contains "max heap:" and "min heap:" and the item value. Keep it loose.

Now, one concern about test 8 in QueueTest (full queue peekRear bug): the exception will abort main. I'll structure tests so each test method is called from main; the AIOOBE will propagate and kill the run. To let other tests run and still report the failure, I could wrap in try/catch and check(false, ...) on exception. That way the suite completes and reports all failures... but assert throws at the first failing check anyway (AssertionError). So the suite stops at the first failure regardless. Hmm — with asserts, the first failure aborts. To see ALL failures, I'd need to catch AssertionError per test and continue. That's a nicer test harness:

```java
private static void runTest(Runnable test) { ... }
```

But catching AssertionError and continuing, then rethrowing at end? Let me design:

```java
private static int testsRun = 0;
private static int testsFailed = 0;

private static void check(boolean condition, String message) {
  testsRun++;
  assert condition : message;
}
```

With -ea, first failed assert throws AssertionError with message, aborting main. Simple, standard. The user can see exactly which check failed via the message. I think that's fine and simplest. The output will show the failing test method name in the stack trace.

Alternatively, catch per test:

```java
public static void main(String[] args) {
  run(StackTest::testX); // Java 8+ method refs
}
private static void run(Runnable r) {
  try { r.run(); }
  catch (AssertionError e) { failed++; System.out.println("FAILED: " + e.getMessage()); }
}
```

This reports all failures at once — much better for the user. And exit with nonzero code if any failed (System.exit(1)). This is a nicer harness. But is it over-engineering? It's genuinely useful. I'll include it — it makes the tests report all failures including exceptions (catch AssertionError; also catch RuntimeException? e.g., AIOOBE from peekRear — that's not an AssertionError. Hmm. If a test throws AIOOBE, I'd want to catch it and report as failure too, and continue. Catch Throwable? That's heavy but pragmatic for a test harness. I'll catch AssertionError and RuntimeException separately, or just Exception+AssertionError... AssertionError is an Error, not Exception. Catch (AssertionError | RuntimeException e). AIOOBE is a RuntimeException ✓.

Hmm, but then "asserts" requirement — still using asserts inside check(). The harness catches them to continue. I think this is good design. And at the end: if failures > 0, print summary and System.exit(1) so scripts show failure; else print success.

Wait, but should the test class call System.exit? If run via ./run_test StackTest, exit code 1 shows as error. Fine.

Actually, let me reconsider — keep it simpler? The user asked "some unit tests using asserts". A per-test catch-all harness is slightly more code but much more usable. I'll go with it but keep it compact.

Design:

```java
class StackTest {
  private static int passed = 0;
  private static int failed = 0;

  private static void check(boolean condition, String message) {
    assert condition : message;
  }

  private static void runTest(Runnable test) {
    try {
      test.run();
      passed++;
    } catch (AssertionError | RuntimeException e) {
      failed++;
      System.out.printf("FAILED: %s (%s)%n", test, e.getMessage());
    }
  }
  ...
}
```

Hmm, `test` toString for a lambda/method-ref gives like "StackTest::testLifoOrder" — nice. Actually method reference toString gives "StackTest::testLifoOrder"? For a method reference, toString typically returns something like "StackTest$$Lambda..." Hmm, actually Runnable from method reference: toString is Object.toString → "StackTest$$Lambda/1234@hash". Not pretty. Pass a name string:

```java
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
```

Print PASS lines too — gives visibility. Then summary + exit code.

Hmm, is this too much? It's a reasonable, compact harness (~15 lines). I'll do it.

Wait, one more consideration: `assert condition : message;` inside check — when asserts disabled, check does nothing and everything "passes". I'll print a warning if assertions are disabled? Can detect: `boolean assertsEnabled = false; assert assertsEnabled = true;` — classic trick. If disabled, print a warning "assertions are disabled; run with -ea (./run_test enables them)". Nice touch. I'll include it in main.

Now the individual tests. Let me be careful with Integer == comparisons: `stack.pop() == 3` — pop returns Integer, 3 is int → unboxing → value comparison ✓. Null check first where needed.

QueueTest full-queue peekRear test:

```java
private static void testPeekRearWhenQueueIsExactlyFull() {
  Queue<Integer> queue = new Queue<Integer>(3);
  queue.insert(1);
  queue.insert(2);
  queue.insert(3);
  check(queue.peekFront() == 1, ...);
  check(queue.peekRear() == 3, "peekRear() should return the last inserted item when the queue is exactly full");
}
```

This will throw AIOOBE → caught → FAIL. Good.

Also, another subtle Queue check: after resize, continue FIFO. Covered in testResizeAfterWrap.

PriorityQueue tests as planned.

Also — should tests cover display()? display prints to stdout. Calling it is a smoke test. I'll include one call per class in a "testDisplay" that just calls display() (wrapped in runTest so exceptions caught). Output noise is fine.

Now, about "don't modify any of my code": I'm only creating 3 new files in src/. The build script compiles all of src/ — my new files get compiled automatically. No existing file touched ✓.

Also the user's truncated sentence — I'll note it at the end and ask them to finish it. Also mention: if they meant something else (e.g., JUnit), I can adjust.

Let me also double-check `check` with assert message: `assert condition : message;` — message must be an expression; string fine.

One more: PriorityQueue test with String items and Integer priorities: `priorityQueue.insert("A", 5)` ✓.

testDuplicatePriorities with Integer items equal to priorities:

```java
PriorityQueue<Integer> pq = new PriorityQueue<Integer>(4);
int[] priorities = {5, 3, 5, 1, 3, 1, 4};
for (int p : priorities) pq.insert(p, p);
int removed = 0;
int last = Integer.MIN_VALUE;
while (true) {
  Integer item = pq.remove();
  if (item == null) break;
  check(item >= last, "remove() should return items in non-decreasing priority order even with duplicate priorities");
  last = item;
  removed++;
}
check(removed == priorities.length, "should remove every inserted item");
```

✓.

testManyElements:

```java
PriorityQueue<Integer> pq = new PriorityQueue<Integer>(3);
int n = 25;
for (int i = 0; i < n; i++) {
  pq.insert(i, (n - 1 - i)); // insert in descending priority order
}
for (int i = 0; i < n; i++) {
  check(pq.remove() == i, "remove() should return items in ascending priority order");
}
```

This exercises multiple resizes (3 → 6 → 12 → 24 → 48). ✓

Stack testAutoResize:

```java
Stack<Integer> stack = new Stack<Integer>(2);
for (int i = 1; i <= 10; i++) stack.push(i);
check(stack.peek() == 10, ...);
for (int i = 10; i >= 1; i--) check(stack.pop() == i, "pop() should preserve LIFO order after resizing");
check(stack.pop() == null, ...);
```

Queue testAutoResize:

```java
Queue<Integer> queue = new Queue<Integer>(2);
for (int i = 1; i <= 10; i++) queue.insert(i);
for (int i = 1; i <= 10; i++) check(queue.remove() == i, "remove() should preserve FIFO order after resizing");
check(queue.remove() == null, ...);
```

testResizeAfterWrap (queue size 3):

```java
Queue<Integer> queue = new Queue<Integer>(3);
queue.insert(1); queue.insert(2); queue.insert(3);
check(queue.remove() == 1, ...);
check(queue.remove() == 2, ...);
// queueTail = 2, queueHead = 0 (wrapped)
queue.insert(4); queue.insert(5); queue.insert(6); // triggers resize mid-wrap
queue.insert(7);
check(queue.remove() == 3, ...);
check(queue.remove() == 4, ...);
check(queue.remove() == 5, ...);
check(queue.remove() == 6, ...);
check(queue.remove() == 7, ...);
check(queue.remove() == null, ...);
```

Wait, let me verify the resize path here: after removing 1 and 2: occupied=1, queueTail=2, queueHead=0, arr=[_,_,3]. insert 4: occupied(1)!=3 → arr[0]=4, queueHead=1, occupied=2. insert 5: arr[1]=5, queueHead=2, occupied=3. insert 6: occupied==3 → resize(6): copy from queueTail=2: [3,4,5], queueTail=0, queueHead=3. Then arr[3]=6, queueHead=4, occupied=4. insert 7: arr[4]=7, queueHead=5, occupied=5. remove → 3 (tail=1), 4 (tail=2), 5 (tail=3), 6 (tail=4), 7 (tail=5). ✓ All good.

Circular wrap test (size 3):

```java
Queue<Integer> queue = new Queue<Integer>(3);
queue.insert(1); queue.insert(2);
check(queue.remove() == 1, ...); // queueTail=1
queue.insert(3); // arr[2]=3, queueHead=0... 
```

wait: after insert 1: queueHead=1; insert 2: queueHead=2; remove 1: queueTail=1; insert 3: arr[2]=3, queueHead=0, occupied=3? occupied was 2 → 3. Hmm occupied==arr.length=3 now. queueHead=0 (wrapped). Then:
- peekFront → arr[queueTail=1] = 2 ✓
- peekRear → (0-1)%3 = -1 → CRASH. So don't call peekRear here! Just remove: remove → 2 (tail=2), remove → 3 (tail=0), insert 4: occupied=1... wait after removing 2 and 3: occupied=1? Let me recount: after insert 3, occupied=3. remove → 2, occupied=2, tail=2. remove → 3, occupied=1, tail=0. insert 4: arr[queueHead=0]=4, queueHead=1, occupied=2. remove → 4 ✓. This exercises wrap-around of both pointers without triggering peekRear bug. 

Let me also double check testPeekFrontAndRear (size 5, insert 1,2,3): queueHead=3, peekRear → (3-1)%5=2 → arr[2]=3 ✓ no crash. peekFront → arr[0]=1 ✓.

For Stack toString: after push 1,2,3: arr=[1,2,3,...], popIndex=2 → "[1, 2, 3]" ✓. pop → popIndex=1 → "[1, 2]" ✓. pop all → popIndex=-1 → "[]" ✓.

Queue toString: "[1, 2, 3]" then after remove → "[2, 3]" ✓.

Empty stack toString: arrToString(arr, -1) → "[]" ✓.

PriorityQueue toString: check contains "max heap:" and "min heap:". Also after inserting ("A",5), contains "A" and "priority=5". Loose checks fine.

Now write the three files. Also compile and run.

Also, hmm: `check(queue.remove() == null, ...)` — remove returns Integer; `== null` fine.

One more subtlety: in testPeekFrontAndRear for PriorityQueue with duplicate... use distinct priorities.

PriorityQueue testPeekFrontAndRear:

```java
PriorityQueue<String> pq = new PriorityQueue<String>(5);
pq.insert("low", 10);
pq.insert("high", 1);
pq.insert("mid", 5);
check("high".equals(pq.peekFront()), "peekFront() should return the item with the smallest priorityValue");
check("low".equals(pq.peekRear()), "peekRear() should return the item with the largest priorityValue");
check("high".equals(pq.peekFront()), "peekFront() should not remove the item");
check(pq.remove().equals("high"), ...);
check(pq.remove().equals("mid"), ...);
check(pq.remove().equals("low"), ...);
```

Note: `pq.remove() == "high"` would be reference comparison — Strings from literals are interned so == might work, but .equals is correct. Use .equals for Strings. For Integers, == against int literal unboxes ✓ (safe for values -128..127 anyway due to cache, but unboxing makes it always safe).

Also testInsertOrderIndependence:

```java
PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10);
// insert in an order unrelated to priority
pq.insert(50, 5);
pq.insert(10, 1);
pq.insert(40, 4);
pq.insert(20, 2);
pq.insert(30, 3);
for (int expected = 10; expected <= 50; expected += 10) {
  check(pq.remove() == expected, "remove() should return items by priority regardless of insertion order");
}
```

✓.

testNegativeAndZeroPriorities:

```java
pq.insert("zero", 0); pq.insert("negative", -5); pq.insert("positive", 7);
remove → "negative", "zero", "positive" ✓
```

testInterleaved:

```java
PriorityQueue<Integer> pq = new PriorityQueue<Integer>(3);
pq.insert(5, 5);
pq.insert(1, 1);
check(pq.remove() == 1, ...);
pq.insert(0, 0);
pq.insert(9, 9);
check(pq.remove() == 0, ...);
check(pq.remove() == 5, ...);
check(pq.remove() == 9, ...);
check(pq.remove() == null, ...);
```

Trace quickly: size 3. insert(5,5): occupied=1. insert(1,1): occupied=2; maxHeap=[5,1]: percolateUp max: compare(5,1)=1 → no swap. minHeap=[5,1]: percolateUp min sign=-1: -1*compare(5,1)=-1<0 → swap → minHeap=[1,5], cross-refs updated. remove: entryToRemove=minHeap[0]=1. minHeap[0]=minHeap[1]=5. maxHeap[5twin.o].o=0. occupied=1. siftDown: leftChild=1>=1 break. deletionIndexMaxHeap = 1's twin's o. 1's twin in maxHeap: initially maxHeap[1]=1' with o=1; after minHeap percolateUp, maxHeap[1'... let me not trace; tests will tell.

testSingleElement:

```java
PriorityQueue<String> pq = new PriorityQueue<String>(2);
pq.insert("only", 42);
check(pq.peekFront().equals("only"), ...);
check(pq.peekRear().equals("only"), ...);
check(pq.remove().equals("only"), ...);
check(pq.remove() == null, ...);
check(pq.peekFront() == null, ...);
check(pq.peekRear() == null, ...);
```

✓.

testToString for PriorityQueue:

```java
PriorityQueue<Integer> pq = new PriorityQueue<Integer>(4);
pq.insert(7, 7);
String s = pq.toString();
check(s.contains("max heap:"), ...);
check(s.contains("min heap:"), ...);
check(s.contains("7"), ...);
```

✓.

Also test that insert beyond capacity then peekFront/peekRear correct:

Covered by testAutoResize: after inserting 6 items into size-2 PQ, check peekFront is min priority item and peekRear is max priority item before removing.

Let me now also double-check the Stack display test — display prints "Current stack: ...". Fine.

Alright — also need to think about whether `runTest` catching RuntimeException could mask real test-harness bugs... acceptable.

Exit: `if (failed > 0) { System.out.printf(...); System.exit(1); }`.

Also the asserts-enabled detection:

```java
boolean assertsEnabled = false;
assert assertsEnabled = true;
if (!assertsEnabled) {
  System.out.println("WARNING: assertions are disabled. Run via ./run_test <TestName> (or java -ea) or all checks will silently pass.");
}
```

Put this at the top of main.

Now — file placement: src/StackTest.java etc. Build compiles them. run_test StackTest runs them.

Let me also confirm java version supports everything (method references need Java 8+). Check java -version at runtime.

Let me write the files now.

Actually, one more thought on the truncated user message: "and make it such that it" — I'll ask at the end. Possibly they wanted "make it such that it runs with ./run_test" — my design does that. I'll mention it.

Also should I create a combined AllTests? Not requested. Skip. Maybe mention how to run all three.

Let me write the files.

StackTest.java:

```java
/**
 * Unit tests for Stack.java, based on the requirements in
 * assignment_details.pdf (section 6: Stack Class (LIFO)).
 *
 * These tests use Java assert statements, so run them with assertions
 * enabled:
 *
 *   ./build && ./run_test StackTest
 */
class StackTest {
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
      System.out.println("Run with: ./run_test StackTest");
    }

    runTest("empty stack returns null on pop/peek", StackTest::testEmptyReturnsNull);
    runTest("push then peek", StackTest::testPushAndPeek);
    runTest("LIFO order", StackTest::testLifoOrder);
    runTest("interleaved push and pop", StackTest::testInterleavedPushPop);
    runTest("auto resize", StackTest::testAutoResize);
    runTest("toString", StackTest::testToString);
    runTest("generic with String items", StackTest::testGenericWithStrings);
    runTest("display runs without errors", StackTest::testDisplay);

    System.out.printf("%nStackTest summary: %d passed, %d failed%n", passed, failed);
    if (failed > 0) {
      System.exit(1);
    }
  }

  // 6.c/6.d: pop() and peek() should return null when the stack is empty
  private static void testEmptyReturnsNull() {
    Stack<Integer> stack = new Stack<Integer>(5);
    check(stack.pop() == null, "pop() should return null on an empty stack");
    check(stack.peek() == null, "peek() should return null on an empty stack");
    check(stack.pop() == null, "pop() should still return null after repeated calls");
  }

  // 6.b/6.d: peek() returns the top item without removing it
  private static void testPushAndPeek() {
    Stack<Integer> stack = new Stack<Integer>(5);
    stack.push(42);
    check(stack.peek() == 42, "peek() should return the last pushed item");
    check(stack.peek() == 42, "peek() should not remove the item");
    check(stack.pop() == 42, "pop() should return the item seen by peek()");
    check(stack.peek() == null, "peek() should return null after the stack is emptied");
  }

  // 6.b/6.c: last in, first out
  private static void testLifoOrder() {
    Stack<Integer> stack = new Stack<Integer>(5);
    stack.push(1);
    stack.push(2);
    stack.push(3);
    check(stack.pop() == 3, "pop() should return 3 (last pushed) first");
    check(stack.pop() == 2, "pop() should return 2 next");
    check(stack.pop() == 1, "pop() should return 1 (first pushed) last");
    check(stack.pop() == null, "pop() should return null once the stack is empty");
  }

  private static void testInterleavedPushPop() {
    Stack<Integer> stack = new Stack<Integer>(5);
    stack.push(1);
    stack.push(2);
    check(stack.pop() == 2, "pop() should return the most recently pushed item");
    stack.push(3);
    check(stack.pop() == 3, "pop() should return the newly pushed item");
    check(stack.pop() == 1, "pop() should return the remaining item");
    check(stack.pop() == null, "pop() should return null when empty");
  }

  // requirement 3: the internal array should resize automatically when full
  private static void testAutoResize() {
    Stack<Integer> stack = new Stack<Integer>(2);
    for (int i = 1; i <= 10; i++) {
      stack.push(i);
    }
    check(stack.peek() == 10, "peek() should return the last pushed item after resizing");
    for (int i = 10; i >= 1; i--) {
      check(stack.pop() == i, "pop() should preserve LIFO order after resizing (expected " + i + ")");
    }
    check(stack.pop() == null, "pop() should return null after emptying a resized stack");
  }

  // 6.e: toString should show the stack contents from bottom to top
  private static void testToString() {
    Stack<Integer> stack = new Stack<Integer>(5);
    check(stack.toString().equals("[]"), "toString() of an empty stack should be \"[]\"");
    stack.push(1);
    stack.push(2);
    stack.push(3);
    check(stack.toString().equals("[1, 2, 3]"),
        "toString() should be \"[1, 2, 3]\" but was \"" + stack.toString() + "\"");
    stack.pop();
    check(stack.toString().equals("[1, 2]"),
        "toString() should be \"[1, 2]\" after one pop but was \"" + stack.toString() + "\"");
  }

  // requirement 1: the class must be generic
  private static void testGenericWithStrings() {
    Stack<String> stack = new Stack<String>(2);
    stack.push("a");
    stack.push("b");
    stack.push("c"); // forces a resize
    check(stack.peek().equals("c"), "peek() should return \"c\"");
    check(stack.pop().equals("c"), "pop() should return \"c\"");
    check(stack.pop().equals("b"), "pop() should return \"b\"");
    check(stack.pop().equals("a"), "pop() should return \"a\"");
    check(stack.pop() == null, "pop() should return null when empty");
  }

  // 6.f: display() should run without errors
  private static void testDisplay() {
    Stack<Integer> stack = new Stack<Integer>(2);
    stack.push(1);
    stack.push(2);
    stack.display();
    stack.pop();
    stack.display();
  }
}
```

QueueTest.java similar with queue-specific tests. PriorityQueueTest.java similar.

For QueueTest, the tests:

```java
class QueueTest {
  ... same harness ...

  public static void main(String[] args) {
    ... asserts check ...
    runTest("empty queue returns null on remove/peekFront/peekRear", QueueTest::testEmptyReturnsNull);
    runTest("FIFO order", QueueTest::testFifoOrder);
    runTest("peekFront and peekRear", QueueTest::testPeekFrontAndRear);
    runTest("circular wrap-around", QueueTest::testCircularWrapAround);
    runTest("auto resize", QueueTest::testAutoResize);
    runTest("resize after wrap-around", QueueTest::testResizeAfterWrapAround);
    runTest("toString", QueueTest::testToString);
    runTest("peekRear when queue is exactly full", QueueTest::testPeekRearWhenFull);
    runTest("generic with String items", QueueTest::testGenericWithStrings);
    runTest("display runs without errors", QueueTest::testDisplay);
    ...
  }

  private static void testEmptyReturnsNull() {
    Queue<Integer> queue = new Queue<Integer>(5);
    check(queue.remove() == null, "remove() should return null on an empty queue");
    check(queue.peekFront() == null, "peekFront() should return null on an empty queue");
    check(queue.peekRear() == null, "peekRear() should return null on an empty queue");
    check(queue.remove() == null, "remove() should still return null after repeated calls");
  }

  private static void testFifoOrder() {
    Queue<Integer> queue = new Queue<Integer>(5);
    queue.insert(1);
    queue.insert(2);
    queue.insert(3);
    check(queue.remove() == 1, "remove() should return 1 (first inserted) first");
    check(queue.remove() == 2, "remove() should return 2 next");
    check(queue.remove() == 3, "remove() should return 3 last");
    check(queue.remove() == null, "remove() should return null once the queue is empty");
  }

  private static void testPeekFrontAndRear() {
    Queue<Integer> queue = new Queue<Integer>(5);
    queue.insert(1);
    queue.insert(2);
    queue.insert(3);
    check(queue.peekFront() == 1, "peekFront() should return the first inserted item");
    check(queue.peekRear() == 3, "peekRear() should return the last inserted item");
    check(queue.peekFront() == 1, "peekFront() should not remove the item");
    check(queue.peekRear() == 3, "peekRear() should not remove the item");
    check(queue.remove() == 1, "remove() should still return items in FIFO order after peeking");
    check(queue.remove() == 2, ...);
    check(queue.remove() == 3, ...);
  }

  private static void testCircularWrapAround() {
    Queue<Integer> queue = new Queue<Integer>(3);
    queue.insert(1);
    queue.insert(2);
    check(queue.remove() == 1, "remove() should return 1");
    queue.insert(3); // queueHead wraps around to index 0
    check(queue.peekFront() == 2, "peekFront() should return 2 after wrap-around");
    check(queue.remove() == 2, "remove() should return 2 after wrap-around");
    check(queue.remove() == 3, "remove() should return 3 after wrap-around");
    queue.insert(4); // queueTail wraps around to index 0
    check(queue.remove() == 4, "remove() should return 4 after both indices wrapped");
    check(queue.remove() == null, "remove() should return null when empty");
  }

  private static void testAutoResize() {
    Queue<Integer> queue = new Queue<Integer>(2);
    for (int i = 1; i <= 10; i++) {
      queue.insert(i);
    }
    check(queue.peekFront() == 1, "peekFront() should return 1 after resizing");
    check(queue.peekRear() == 10, "peekRear() should return 10 after resizing");
    for (int i = 1; i <= 10; i++) {
      check(queue.remove() == i, "remove() should preserve FIFO order after resizing (expected " + i + ")");
    }
    check(queue.remove() == null, "remove() should return null after emptying a resized queue");
  }

  private static void testResizeAfterWrapAround() {
    Queue<Integer> queue = new Queue<Integer>(3);
    queue.insert(1);
    queue.insert(2);
    queue.insert(3);
    check(queue.remove() == 1, "remove() should return 1");
    check(queue.remove() == 2, "remove() should return 2");
    // queueTail = 2 and queueHead = 0, so the next insert triggers a
    // resize while the elements are wrapped around the end of the array
    queue.insert(4);
    queue.insert(5);
    queue.insert(6); // triggers resize from 3 to 6
    queue.insert(7);
    for (int i = 3; i <= 7; i++) {
      check(queue.remove() == i, "remove() should preserve FIFO order across a wrap-around resize (expected " + i + ")");
    }
    check(queue.remove() == null, "remove() should return null when empty");
  }

  private static void testToString() {
    Queue<Integer> queue = new Queue<Integer>(5);
    check(queue.toString().equals("[]"), "toString() of an empty queue should be \"[]\"");
    queue.insert(1);
    queue.insert(2);
    queue.insert(3);
    check(queue.toString().equals("[1, 2, 3]"), "toString() should be \"[1, 2, 3]\" but was \"" + queue.toString() + "\"");
    queue.remove();
    check(queue.toString().equals("[2, 3]"), "toString() should be \"[2, 3]\" after one removal but was \"" + queue.toString() + "\"");
  }

  private static void testPeekRearWhenFull() {
    Queue<Integer> queue = new Queue<Integer>(3);
    queue.insert(1);
    queue.insert(2);
    queue.insert(3); // queue is now exactly full and queueHead wraps to 0
    check(queue.peekFront() == 1, "peekFront() should return 1 on a full queue");
    check(queue.peekRear() == 3, "peekRear() should return the last inserted item when the queue is exactly full");
  }

  private static void testGenericWithStrings() {
    Queue<String> queue = new Queue<String>(2);
    queue.insert("a");
    queue.insert("b");
    queue.insert("c"); // forces a resize
    check(queue.peekFront().equals("a"), "peekFront() should return \"a\"");
    check(queue.peekRear().equals("c"), "peekRear() should return \"c\"");
    check(queue.remove().equals("a"), "remove() should return \"a\"");
    check(queue.remove().equals("b"), "remove() should return \"b\"");
    check(queue.remove().equals("c"), "remove() should return \"c\"");
    check(queue.remove() == null, "remove() should return null when empty");
  }

  private static void testDisplay() {
    Queue<Integer> queue = new Queue<Integer>(3);
    queue.insert(1);
    queue.insert(2);
    queue.display();
    queue.remove();
    queue.display();
  }
}
```

Wait — in testCircularWrapAround, after inserting 3 (queueHead wraps to 0, occupied=3 == arr.length). Then peekFront → arr[queueTail=1] = 2 ✓. Then remove → 2, tail=2, occupied=2. remove → 3, tail=0, occupied=1. insert 4: occupied(1) != 3 → arr[queueHead=0]=4, queueHead=1, occupied=2. remove → 4 ✓. Good — but note I don't call peekRear while full here (that's the separate known-bug test). ✓

In testAutoResize (queue): insert 1..10 into size 2. Insert 3 triggers resize: occupied==2 → resize(4): copy from tail=0: [1,2], tail=0, head=2. insert at 2 → head=3, occupied=3. insert 4: occupied(3)!=4 → arr[3]=4, head=0 (3+1)%4=0, occupied=4. insert 5: occupied==4 → resize(8): copy from tail=0: [1,2,3,4], tail=0, head=4. insert arr[4]=5, head=5, occupied=5. ... continues fine. peekRear: head=10 after 10 inserts? Let's see: after resize to 8 at insert 5, head=5. insert 6: head=6, occ=6. insert 7: head=7, occ=7. insert 8: occ==8 → resize(16): copy [1..7], tail=0, head=7. insert arr[7]=8, head=8, occ=8. insert 9: head=9, occ=9. insert 10: head=10, occ=10. peekRear: (10-1)%16=9 → arr[9]=10 ✓. peekFront: arr[0]=1 ✓. removes 1..10 ✓.

PriorityQueueTest.java:

```java
class PriorityQueueTest {
  ... harness ...

  public static void main(String[] args) {
    ... asserts check ...
    runTest("empty priority queue returns null", PriorityQueueTest::testEmptyReturnsNull);
    runTest("remove returns smallest priorityValue first", PriorityQueueTest::testRemoveSmallestPriorityFirst);
    runTest("peekFront and peekRear", PriorityQueueTest::testPeekFrontAndRear);
    runTest("insertion order does not matter", PriorityQueueTest::testInsertionOrderDoesNotMatter);
    runTest("auto resize", PriorityQueueTest::testAutoResize);
    runTest("interleaved insert and remove", PriorityQueueTest::testInterleavedInsertRemove);
    runTest("single element", PriorityQueueTest::testSingleElement);
    runTest("duplicate priorities", PriorityQueueTest::testDuplicatePriorities);
    runTest("negative and zero priorities", PriorityQueueTest::testNegativeAndZeroPriorities);
    runTest("many elements with multiple resizes", PriorityQueueTest::testManyElements);
    runTest("toString", PriorityQueueTest::testToString);
    runTest("generic with String items", PriorityQueueTest::testGenericWithStrings);
    runTest("display runs without errors", PriorityQueueTest::testDisplay);
    ...
  }

  private static void testEmptyReturnsNull() {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(5);
    check(pq.remove() == null, "remove() should return null on an empty priority queue");
    check(pq.peekFront() == null, "peekFront() should return null on an empty priority queue");
    check(pq.peekRear() == null, "peekRear() should return null on an empty priority queue");
  }

  // section 8: a smaller priorityValue means higher priority
  private static void testRemoveSmallestPriorityFirst() {
    PriorityQueue<String> pq = new PriorityQueue<String>(5);
    pq.insert("A", 5);
    pq.insert("B", 1);
    pq.insert("C", 3);
    check(pq.remove().equals("B"), "remove() should return the item with priorityValue 1 first");
    check(pq.remove().equals("C"), "remove() should return the item with priorityValue 3 next");
    check(pq.remove().equals("A"), "remove() should return the item with priorityValue 5 last");
    check(pq.remove() == null, "remove() should return null once the priority queue is empty");
  }

  private static void testPeekFrontAndRear() {
    PriorityQueue<String> pq = new PriorityQueue<String>(5);
    pq.insert("low", 10);
    pq.insert("high", 1);
    pq.insert("mid", 5);
    check(pq.peekFront().equals("high"), "peekFront() should return the highest priority (smallest priorityValue) item");
    check(pq.peekRear().equals("low"), "peekRear() should return the lowest priority (largest priorityValue) item");
    check(pq.peekFront().equals("high"), "peekFront() should not remove the item");
    check(pq.peekRear().equals("low"), "peekRear() should not remove the item");
    check(pq.remove().equals("high"), "remove() should still return the highest priority item after peeking");
    check(pq.remove().equals("mid"), "remove() should return the middle priority item next");
    check(pq.remove().equals("low"), "remove() should return the lowest priority item last");
  }

  private static void testInsertionOrderDoesNotMatter() {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10);
    pq.insert(50, 5);
    pq.insert(10, 1);
    pq.insert(40, 4);
    pq.insert(20, 2);
    pq.insert(30, 3);
    for (int expected = 10; expected <= 50; expected += 10) {
      check(pq.remove() == expected, "remove() should return " + expected + " based on priority, not insertion order");
    }
  }

  private static void testAutoResize() {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(2);
    for (int i = 0; i < 10; i++) {
      pq.insert(i, 9 - i); // priorities 9, 8, ..., 0
    }
    check(pq.peekFront() == 9, "peekFront() should return the item with the smallest priorityValue after resizing");
    check(pq.peekRear() == 0, "peekRear() should return the item with the largest priorityValue after resizing");
    for (int i = 0; i < 10; i++) {
      check(pq.remove() == (9 - i), "remove() should return items in ascending priority order after resizing (expected " + (9 - i) + ")");
    }
    check(pq.remove() == null, "remove() should return null after emptying a resized priority queue");
  }
```

Wait: insert(i, 9-i) for i=0..9: item i has priority 9-i. Items: (0,p9),(1,p8),...,(9,p0). peekFront should return item with smallest priority = item 9 (p0). peekRear → item 0 (p9). Remove order: 9,8,7,...,0. Let me fix:

```java
for (int i = 0; i < 10; i++) {
  pq.insert(i, 9 - i);
}
check(pq.peekFront() == 9, ...); // item 9 has priority 0 (smallest)
check(pq.peekRear() == 0, ...);  // item 0 has priority 9 (largest)
for (int expected = 9; expected >= 0; expected--) {
  check(pq.remove() == expected, ...);
}
```

✓.

```java
  private static void testInterleavedInsertRemove() {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(3);
    pq.insert(5, 5);
    pq.insert(1, 1);
    check(pq.remove() == 1, "remove() should return the item with priorityValue 1");
    pq.insert(0, 0);
    pq.insert(9, 9);
    check(pq.remove() == 0, "remove() should return the item with priorityValue 0");
    check(pq.remove() == 5, "remove() should return the item with priorityValue 5");
    check(pq.remove() == 9, "remove() should return the item with priorityValue 9");
    check(pq.remove() == null, "remove() should return null when empty");
  }

  private static void testSingleElement() {
    PriorityQueue<String> pq = new PriorityQueue<String>(2);
    pq.insert("only", 42);
    check(pq.peekFront().equals("only"), "peekFront() should return the only item");
    check(pq.peekRear().equals("only"), "peekRear() should return the only item");
    check(pq.remove().equals("only"), "remove() should return the only item");
    check(pq.remove() == null, "remove() should return null after removing the only item");
    check(pq.peekFront() == null, "peekFront() should return null when empty");
    check(pq.peekRear() == null, "peekRear() should return null when empty");
  }

  private static void testDuplicatePriorities() {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(4);
    int[] priorities = {5, 3, 5, 1, 3, 1, 4};
    for (int priority : priorities) {
      pq.insert(priority, priority);
    }
    int removed = 0;
    int previous = Integer.MIN_VALUE;
    while (removed < priorities.length) {
      Integer item = pq.remove();
      check(item != null, "remove() should return every inserted item");
      if (item == null) break;
      check(item >= previous,
          "remove() should return items in non-decreasing priority order even with duplicate priorities (got " + item + " after " + previous + ")");
      previous = item;
      removed++;
    }
    check(pq.remove() == null, "remove() should return null when empty");
  }

  private static void testNegativeAndZeroPriorities() {
    PriorityQueue<String> pq = new PriorityQueue<String>(5);
    pq.insert("zero", 0);
    pq.insert("negative", -5);
    pq.insert("positive", 7);
    check(pq.remove().equals("negative"), "remove() should handle negative priorityValues");
    check(pq.remove().equals("zero"), "remove() should handle a priorityValue of 0");
    check(pq.remove().equals("positive"), "remove() should return the largest priorityValue last");
  }

  private static void testManyElements() {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(3);
    int n = 25;
    for (int i = 0; i < n; i++) {
      pq.insert(i, n - 1 - i); // insert in descending priority order
    }
    for (int expected = n - 1; expected >= 0; expected--) {
      check(pq.remove() == expected, "remove() should return " + expected + " in ascending priority order");
    }
    check(pq.remove() == null, "remove() should return null when empty");
  }

  private static void testToString() {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(4);
    pq.insert(7, 7);
    String result = pq.toString();
    check(result.contains("max heap:"), "toString() should describe the max heap");
    check(result.contains("min heap:"), "toString() should describe the min heap");
    check(result.contains("7"), "toString() should contain the inserted item");
  }

  private static void testGenericWithStrings() {
    PriorityQueue<String> pq = new PriorityQueue<String>(2);
    pq.insert("pear", 3);
    pq.insert("apple", 1);
    pq.insert("banana", 2); // forces a resize
    check(pq.peekFront().equals("apple"), "peekFront() should return \"apple\" (priorityValue 1)");
    check(pq.peekRear().equals("pear"), "peekRear() should return \"pear\" (priorityValue 3)");
    check(pq.remove().equals("apple"), "remove() should return \"apple\"");
    check(pq.remove().equals("banana"), "remove() should return \"banana\"");
    check(pq.remove().equals("pear"), "remove() should return \"pear\"");
    check(pq.remove() == null, "remove() should return null when empty");
  }

  private static void testDisplay() {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(4);
    pq.insert(2, 2);
    pq.insert(1, 1);
    pq.display();
    pq.remove();
    pq.display();
  }
}
```

Wait, testManyElements: insert(i, n-1-i): item i has priority n-1-i. Smallest priority = item n-1 (priority 0). Remove order: n-1, n-2, ..., 0. My loop `for (expected = n-1; expected >= 0; expected--)` ✓.

testDuplicatePriorities: items are Integers equal to their priorities. Removal must be non-decreasing. ✓

Now compile and run. Potential compile issues: none expected — all APIs used exist. `Runnable` method references `QueueTest::testFifoOrder` — private static methods, method reference within same class ✓.

One concern: `catch (AssertionError | RuntimeException e)` — multi-catch ✓ Java 7+.

Also `System.exit(1)` — fine.

Let me also double check the harness's `passed` counting: counts per test method, not per check. Summary says "X test groups passed". Fine — I'll word it as "tests".

Now, about running: `./build` then `./run_test StackTest` etc. Let me execute.

Also — check java version first to ensure -ea and method refs work (Java 8+ virtually certain).

After running, if failures appear (I expect QueueTest::testPeekRearWhenFull to FAIL due to the (queueHead-1)%length bug), I report to the user clearly, without modifying their code.

Also note: out/ directory exists with old compiled classes; ./build regenerates. Fine.

Let me also quickly glance at the remaining files I haven't read (StringReverser, DelimiterChecker) — not needed for this task. Skip.

Plan:
1. Write the three test files.
2. ./build
3. ./run_test StackTest, QueueTest, PriorityQueueTest
4. Report results, note the truncated sentence, mention the peekRear bug if it fails.

Let me write files now. I'll write all three in parallel.

I have the full picture now. I extracted the assignment PDF with `pdftotext` — it specifies the exact API for `Stack`, `Queue`, and `PriorityQueue` (LIFO/FIFO semantics, auto-resize, null on empty pop/remove/peek, priority ordering where smaller `priorityValue` = higher priority). Your `run_test` script already runs with `-ea`, so plain Java `assert` statements with a `main` method are the natural fit — no JUnit needed, and nothing existing gets modified.

Note: your message got cut off at "and make it such that it…" — I'll finish the tests first and you can tell me the rest.

Let me create the three test files:


**Tool: write**

**Input:**
```json
{
  "content": "/**\n * Unit tests for Stack.java, based on the requirements in\n * assignment_details.pdf (section 6: Stack Class (LIFO)).\n *\n * These tests use Java assert statements, so they must be run with\n * assertions enabled:\n *\n *   ./build && ./run_test StackTest\n */\nclass StackTest {\n  private static int passed = 0;\n  private static int failed = 0;\n\n  private static void check(boolean condition, String message) {\n    assert condition : message;\n  }\n\n  private static void runTest(String name, Runnable test) {\n    try {\n      test.run();\n      passed++;\n      System.out.printf(\"PASS: %s%n\", name);\n    } catch (AssertionError | RuntimeException e) {\n      failed++;\n      System.out.printf(\"FAIL: %s -- %s%n\", name, e.getMessage());\n    }\n  }\n\n  public static void main(String[] args) {\n    boolean assertsEnabled = false;\n    assert assertsEnabled = true;\n    if (!assertsEnabled) {\n      System.out.println(\n          \"WARNING: assertions are disabled, every check will silently pass.\");\n      System.out.println(\"Run with: ./run_test StackTest\");\n    }\n\n    runTest(\"empty stack returns null on pop/peek\",\n        StackTest::testEmptyReturnsNull);\n    runTest(\"push then peek\", StackTest::testPushAndPeek);\n    runTest(\"LIFO order\", StackTest::testLifoOrder);\n    runTest(\"interleaved push and pop\", StackTest::testInterleavedPushPop);\n    runTest(\"auto resize\", StackTest::testAutoResize);\n    runTest(\"toString\", StackTest::testToString);\n    runTest(\"generic with String items\", StackTest::testGenericWithStrings);\n    runTest(\"display runs without errors\", StackTest::testDisplay);\n\n    System.out.printf(\"%nStackTest summary: %d passed, %d failed%n\",\n        passed, failed);\n    if (failed > 0) {\n      System.exit(1);\n    }\n  }\n\n  // 6.c/6.d: pop() and peek() should return null when the stack is empty\n  private static void testEmptyReturnsNull() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    check(stack.pop() == null, \"pop() should return null on an empty stack\");\n    check(stack.peek() == null, \"peek() should return null on an empty stack\");\n    check(stack.pop() == null,\n        \"pop() should still return null after repeated calls\");\n  }\n\n  // 6.b/6.d: peek() returns the top item without removing it\n  private static void testPushAndPeek() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    stack.push(42);\n    check(stack.peek() == 42, \"peek() should return the last pushed item\");\n    check(stack.peek() == 42, \"peek() should not remove the item\");\n    check(stack.pop() == 42, \"pop() should return the item seen by peek()\");\n    check(stack.peek() == null,\n        \"peek() should return null after the stack is emptied\");\n  }\n\n  // 6.b/6.c: last in, first out\n  private static void testLifoOrder() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    stack.push(1);\n    stack.push(2);\n    stack.push(3);\n    check(stack.pop() == 3, \"pop() should return 3 (last pushed) first\");\n    check(stack.pop() == 2, \"pop() should return 2 next\");\n    check(stack.pop() == 1, \"pop() should return 1 (first pushed) last\");\n    check(stack.pop() == null,\n        \"pop() should return null once the stack is empty\");\n  }\n\n  private static void testInterleavedPushPop() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    stack.push(1);\n    stack.push(2);\n    check(stack.pop() == 2, \"pop() should return the most recently pushed item\");\n    stack.push(3);\n    check(stack.pop() == 3, \"pop() should return the newly pushed item\");\n    check(stack.pop() == 1, \"pop() should return the remaining item\");\n    check(stack.pop() == null, \"pop() should return null when empty\");\n  }\n\n  // project requirement 3: the internal array should resize automatically\n  // when it becomes full\n  private static void testAutoResize() {\n    Stack<Integer> stack = new Stack<Integer>(2);\n    for (int i = 1; i <= 10; i++) {\n      stack.push(i);\n    }\n    check(stack.peek() == 10,\n        \"peek() should return the last pushed item after resizing\");\n    for (int i = 10; i >= 1; i--) {\n      check(stack.pop() == i,\n          \"pop() should preserve LIFO order after resizing (expected \" + i + \")\");\n    }\n    check(stack.pop() == null,\n        \"pop() should return null after emptying a resized stack\");\n  }\n\n  // 6.e: toString should show the stack contents from bottom to top\n  private static void testToString() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    check(stack.toString().equals(\"[]\"),\n        \"toString() of an empty stack should be \\\"[]\\\"\");\n    stack.push(1);\n    stack.push(2);\n    stack.push(3);\n    check(stack.toString().equals(\"[1, 2, 3]\"),\n        \"toString() should be \\\"[1, 2, 3]\\\" but was \\\"\" + stack.toString() + \"\\\"\");\n    stack.pop();\n    check(stack.toString().equals(\"[1, 2]\"),\n        \"toString() should be \\\"[1, 2]\\\" after one pop but was \\\"\"\n            + stack.toString() + \"\\\"\");\n  }\n\n  // project requirement 1: the class must be generic\n  private static void testGenericWithStrings() {\n    Stack<String> stack = new Stack<String>(2);\n    stack.push(\"a\");\n    stack.push(\"b\");\n    stack.push(\"c\"); // forces a resize\n    check(stack.peek().equals(\"c\"), \"peek() should return \\\"c\\\"\");\n    check(stack.pop().equals(\"c\"), \"pop() should return \\\"c\\\"\");\n    check(stack.pop().equals(\"b\"), \"pop() should return \\\"b\\\"\");\n    check(stack.pop().equals(\"a\"), \"pop() should return \\\"a\\\"\");\n    check(stack.pop() == null, \"pop() should return null when empty\");\n  }\n\n  // 6.f: display() should run without errors\n  private static void testDisplay() {\n    Stack<Integer> stack = new Stack<Integer>(2);\n    stack.push(1);\n    stack.push(2);\n    stack.display();\n    stack.pop();\n    stack.display();\n  }\n}\n",
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/StackTest.java"
}
```

Created file successfully: src/StackTest.java

**Tool: write**

**Input:**
```json
{
  "content": "/**\n * Unit tests for Queue.java, based on the requirements in\n * assignment_details.pdf (section 7: Queue Class (FIFO)).\n *\n * These tests use Java assert statements, so they must be run with\n * assertions enabled:\n *\n *   ./build && ./run_test QueueTest\n */\nclass QueueTest {\n  private static int passed = 0;\n  private static int failed = 0;\n\n  private static void check(boolean condition, String message) {\n    assert condition : message;\n  }\n\n  private static void runTest(String name, Runnable test) {\n    try {\n      test.run();\n      passed++;\n      System.out.printf(\"PASS: %s%n\", name);\n    } catch (AssertionError | RuntimeException e) {\n      failed++;\n      System.out.printf(\"FAIL: %s -- %s%n\", name, e.getMessage());\n    }\n  }\n\n  public static void main(String[] args) {\n    boolean assertsEnabled = false;\n    assert assertsEnabled = true;\n    if (!assertsEnabled) {\n      System.out.println(\n          \"WARNING: assertions are disabled, every check will silently pass.\");\n      System.out.println(\"Run with: ./run_test QueueTest\");\n    }\n\n    runTest(\"empty queue returns null on remove/peekFront/peekRear\",\n        QueueTest::testEmptyReturnsNull);\n    runTest(\"FIFO order\", QueueTest::testFifoOrder);\n    runTest(\"peekFront and peekRear\", QueueTest::testPeekFrontAndRear);\n    runTest(\"circular wrap-around\", QueueTest::testCircularWrapAround);\n    runTest(\"auto resize\", QueueTest::testAutoResize);\n    runTest(\"resize after wrap-around\", QueueTest::testResizeAfterWrapAround);\n    runTest(\"toString\", QueueTest::testToString);\n    runTest(\"peekRear when queue is exactly full\", QueueTest::testPeekRearWhenFull);\n    runTest(\"generic with String items\", QueueTest::testGenericWithStrings);\n    runTest(\"display runs without errors\", QueueTest::testDisplay);\n\n    System.out.printf(\"%nQueueTest summary: %d passed, %d failed%n\",\n        passed, failed);\n    if (failed > 0) {\n      System.exit(1);\n    }\n  }\n\n  // 7.c/7.d/7.e: remove(), peekFront() and peekRear() should return null\n  // when the queue is empty\n  private static void testEmptyReturnsNull() {\n    Queue<Integer> queue = new Queue<Integer>(5);\n    check(queue.remove() == null, \"remove() should return null on an empty queue\");\n    check(queue.peekFront() == null,\n        \"peekFront() should return null on an empty queue\");\n    check(queue.peekRear() == null,\n        \"peekRear() should return null on an empty queue\");\n    check(queue.remove() == null,\n        \"remove() should still return null after repeated calls\");\n  }\n\n  // 7.b/7.c: first in, first out\n  private static void testFifoOrder() {\n    Queue<Integer> queue = new Queue<Integer>(5);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3);\n    check(queue.remove() == 1, \"remove() should return 1 (first inserted) first\");\n    check(queue.remove() == 2, \"remove() should return 2 next\");\n    check(queue.remove() == 3, \"remove() should return 3 last\");\n    check(queue.remove() == null,\n        \"remove() should return null once the queue is empty\");\n  }\n\n  // 7.d/7.e: peeking should not remove anything\n  private static void testPeekFrontAndRear() {\n    Queue<Integer> queue = new Queue<Integer>(5);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3);\n    check(queue.peekFront() == 1,\n        \"peekFront() should return the first inserted item\");\n    check(queue.peekRear() == 3,\n        \"peekRear() should return the last inserted item\");\n    check(queue.peekFront() == 1, \"peekFront() should not remove the item\");\n    check(queue.peekRear() == 3, \"peekRear() should not remove the item\");\n    check(queue.remove() == 1,\n        \"remove() should still return items in FIFO order after peeking\");\n    check(queue.remove() == 2, \"remove() should return 2 next\");\n    check(queue.remove() == 3, \"remove() should return 3 last\");\n  }\n\n  // the queue is implemented as a circular array, so both indices must\n  // wrap around correctly\n  private static void testCircularWrapAround() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    check(queue.remove() == 1, \"remove() should return 1\");\n    queue.insert(3); // queueHead wraps around to index 0\n    check(queue.peekFront() == 2,\n        \"peekFront() should return 2 after the head wrapped around\");\n    check(queue.remove() == 2,\n        \"remove() should return 2 after the head wrapped around\");\n    check(queue.remove() == 3,\n        \"remove() should return 3 after the head wrapped around\");\n    queue.insert(4); // queueTail wraps around to index 0\n    check(queue.remove() == 4,\n        \"remove() should return 4 after both indices wrapped around\");\n    check(queue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  // project requirement 3: the internal array should resize automatically\n  // when it becomes full\n  private static void testAutoResize() {\n    Queue<Integer> queue = new Queue<Integer>(2);\n    for (int i = 1; i <= 10; i++) {\n      queue.insert(i);\n    }\n    check(queue.peekFront() == 1,\n        \"peekFront() should return 1 after resizing\");\n    check(queue.peekRear() == 10,\n        \"peekRear() should return 10 after resizing\");\n    for (int i = 1; i <= 10; i++) {\n      check(queue.remove() == i,\n          \"remove() should preserve FIFO order after resizing (expected \" + i + \")\");\n    }\n    check(queue.remove() == null,\n        \"remove() should return null after emptying a resized queue\");\n  }\n\n  // resizing must preserve the FIFO order even when the elements are\n  // wrapped around the end of the internal array\n  private static void testResizeAfterWrapAround() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3);\n    check(queue.remove() == 1, \"remove() should return 1\");\n    check(queue.remove() == 2, \"remove() should return 2\");\n    // queueTail = 2 and queueHead = 0, so the next insert triggers a\n    // resize while the remaining elements are wrapped around the array\n    queue.insert(4);\n    queue.insert(5);\n    queue.insert(6); // triggers a resize from 3 to 6 elements\n    queue.insert(7);\n    for (int i = 3; i <= 7; i++) {\n      check(queue.remove() == i,\n          \"remove() should preserve FIFO order across a wrap-around resize \"\n              + \"(expected \" + i + \")\");\n    }\n    check(queue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  // 7.f: toString should show the queue contents from front to rear\n  private static void testToString() {\n    Queue<Integer> queue = new Queue<Integer>(5);\n    check(queue.toString().equals(\"[]\"),\n        \"toString() of an empty queue should be \\\"[]\\\"\");\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3);\n    check(queue.toString().equals(\"[1, 2, 3]\"),\n        \"toString() should be \\\"[1, 2, 3]\\\" but was \\\"\" + queue.toString() + \"\\\"\");\n    queue.remove();\n    check(queue.toString().equals(\"[2, 3]\"),\n        \"toString() should be \\\"[2, 3]\\\" after one removal but was \\\"\"\n            + queue.toString() + \"\\\"\");\n  }\n\n  // peekRear() must work even when the queue is exactly at capacity\n  private static void testPeekRearWhenFull() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3); // queue is now exactly full and queueHead wraps to 0\n    check(queue.peekFront() == 1, \"peekFront() should return 1 on a full queue\");\n    check(queue.peekRear() == 3,\n        \"peekRear() should return the last inserted item when the queue is \"\n            + \"exactly full\");\n  }\n\n  // project requirement 1: the class must be generic\n  private static void testGenericWithStrings() {\n    Queue<String> queue = new Queue<String>(2);\n    queue.insert(\"a\");\n    queue.insert(\"b\");\n    queue.insert(\"c\"); // forces a resize\n    check(queue.peekFront().equals(\"a\"), \"peekFront() should return \\\"a\\\"\");\n    check(queue.peekRear().equals(\"c\"), \"peekRear() should return \\\"c\\\"\");\n    check(queue.remove().equals(\"a\"), \"remove() should return \\\"a\\\"\");\n    check(queue.remove().equals(\"b\"), \"remove() should return \\\"b\\\"\");\n    check(queue.remove().equals(\"c\"), \"remove() should return \\\"c\\\"\");\n    check(queue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  // 7.g: display() should run without errors\n  private static void testDisplay() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    queue.display();\n    queue.remove();\n    queue.display();\n  }\n}\n",
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/QueueTest.java"
}
```

Created file successfully: src/QueueTest.java

**Tool: write**

**Input:**
```json
{
  "content": "/**\n * Unit tests for PriorityQueue.java, based on the requirements in\n * assignment_details.pdf (section 8: PriorityQueue Class).\n *\n * Reminder from the assignment: a smaller priorityValue means higher\n * priority, and items must be removed based on priority.\n *\n * These tests use Java assert statements, so they must be run with\n * assertions enabled:\n *\n *   ./build && ./run_test PriorityQueueTest\n */\nclass PriorityQueueTest {\n  private static int passed = 0;\n  private static int failed = 0;\n\n  private static void check(boolean condition, String message) {\n    assert condition : message;\n  }\n\n  private static void runTest(String name, Runnable test) {\n    try {\n      test.run();\n      passed++;\n      System.out.printf(\"PASS: %s%n\", name);\n    } catch (AssertionError | RuntimeException e) {\n      failed++;\n      System.out.printf(\"FAIL: %s -- %s%n\", name, e.getMessage());\n    }\n  }\n\n  public static void main(String[] args) {\n    boolean assertsEnabled = false;\n    assert"
}
```

Invalid arguments for tool "write":
- path: Missing key

Arguments provided:
{
  "content": "/**\n * Unit tests for PriorityQueue.java, based on the requirements in\n * assignment_details.pdf (section 8: PriorityQueue Class).\n *\n * Reminder from the assignment: a smaller priorityValue means higher\n * priority, and items must be removed based on priority.\n *\n * These tests use Java assert statements, so they must be run with\n * assertions enabled:\n *\n *   ./build && ./run_test PriorityQueueTest\n */\nclass PriorityQueueTest {\n  private static int passed = 0;\n  private static int failed = 0;\n\n  private static void check(boolean condition, String message) {\n    assert condition : message;\n  }\n\n  private static void runTest(String name, Runnable test) {\n    try {\n      test.run();\n      passed++;\n      System.out.printf(\"PASS: %s%n\", name);\n    } catch (AssertionError | RuntimeException e) {\n      failed++;\n      System.out.printf(\"FAIL: %s -- %s%n\", name, e.getMessage());\n    }\n  }\n\n  public static void main(String[] args) {\n    boolean assertsEnabled = false;\n    assert"
}

Update the arguments and call the tool again.

---

## Assistant

_Thinking:_

The PriorityQueueTest.java write got truncated/malformed. Let me write the full file again.

Stack and Queue tests were created, but the PriorityQueueTest write was cut off. Let me write the full PriorityQueueTest.java file

**Tool: write**

**Input:**
```json
{
  "content": "/**\n * Unit tests for PriorityQueue.java, based on the requirements in\n * assignment_details.pdf (section 8: PriorityQueue Class).\n *\n * Reminder from the assignment: a smaller priorityValue means higher\n * priority, and items must be removed based on priority.\n *\n * These tests use Java assert statements, so they must be run with\n * assertions enabled:\n *\n *   ./build && ./run_test PriorityQueueTest\n */\nclass PriorityQueueTest {\n  private static int passed = 0;\n  private static int failed = 0;\n\n  private static void check(boolean condition, String message) {\n    assert condition : message;\n  }\n\n  private static void runTest(String name, Runnable test) {\n    try {\n      test.run();\n      passed++;\n      System.out.printf(\"PASS: %s%n\", name);\n    } catch (AssertionError | RuntimeException e) {\n      failed++;\n      System.out.printf(\"FAIL: %s -- %s%n\", name, e.getMessage());\n    }\n  }\n\n  public static void main(String[] args) {\n    boolean assertsEnabled = false;\n    assert assertsEnabled = true;\n    if (!assertsEnabled) {\n      System.out.println(\n          \"WARNING: assertions are disabled, every check will silently pass.\");\n      System.out.println(\"Run with: ./run_test PriorityQueueTest\");\n    }\n\n    runTest(\"empty priority queue returns null\",\n        PriorityQueueTest::testEmptyReturnsNull);\n    runTest(\"remove returns smallest priorityValue first\",\n        PriorityQueueTest::testRemoveSmallestPriorityFirst);\n    runTest(\"peekFront and peekRear\",\n        PriorityQueueTest::testPeekFrontAndRear);\n    runTest(\"insertion order does not matter\",\n        PriorityQueueTest::testInsertionOrderDoesNotMatter);\n    runTest(\"auto resize\", PriorityQueueTest::testAutoResize);\n    runTest(\"interleaved insert and remove\",\n        PriorityQueueTest::testInterleavedInsertRemove);\n    runTest(\"single element\", PriorityQueueTest::testSingleElement);\n    runTest(\"duplicate priorities\", PriorityQueueTest::testDuplicatePriorities);\n    runTest(\"negative and zero priorities\",\n        PriorityQueueTest::testNegativeAndZeroPriorities);\n    runTest(\"many elements with multiple resizes\",\n        PriorityQueueTest::testManyElements);\n    runTest(\"toString\", PriorityQueueTest::testToString);\n    runTest(\"generic with String items\",\n        PriorityQueueTest::testGenericWithStrings);\n    runTest(\"display runs without errors\", PriorityQueueTest::testDisplay);\n\n    System.out.printf(\"%nPriorityQueueTest summary: %d passed, %d failed%n\",\n        passed, failed);\n    if (failed > 0) {\n      System.exit(1);\n    }\n  }\n\n  // 8.c/8.d/8.e: remove(), peekFront() and peekRear() should return\n  // null when the priority queue is empty\n  private static void testEmptyReturnsNull() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(5);\n    check(priorityQueue.remove() == null,\n        \"remove() should return null on an empty priority queue\");\n    check(priorityQueue.peekFront() == null,\n        \"peekFront() should return null on an empty priority queue\");\n    check(priorityQueue.peekRear() == null,\n        \"peekRear() should return null on an empty priority queue\");\n    check(priorityQueue.remove() == null,\n        \"remove() should still return null after repeated calls\");\n  }\n\n  // section 8: a smaller priorityValue means higher priority, so\n  // remove() must return the smallest priorityValue first\n  private static void testRemoveSmallestPriorityFirst() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(5);\n    priorityQueue.insert(\"A\", 5);\n    priorityQueue.insert(\"B\", 1);\n    priorityQueue.insert(\"C\", 3);\n    check(priorityQueue.remove().equals(\"B\"),\n        \"remove() should return the item with priorityValue 1 first\");\n    check(priorityQueue.remove().equals(\"C\"),\n        \"remove() should return the item with priorityValue 3 next\");\n    check(priorityQueue.remove().equals(\"A\"),\n        \"remove() should return the item with priorityValue 5 last\");\n    check(priorityQueue.remove() == null,\n        \"remove() should return null once the priority queue is empty\");\n  }\n\n  // 8.d/8.e: peekFront() is the next item to be removed (smallest\n  // priorityValue) and peekRear() is the last (largest priorityValue);\n  // peeking must not remove anything\n  private static void testPeekFrontAndRear() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(5);\n    priorityQueue.insert(\"low\", 10);\n    priorityQueue.insert(\"high\", 1);\n    priorityQueue.insert(\"mid\", 5);\n    check(priorityQueue.peekFront().equals(\"high\"),\n        \"peekFront() should return the smallest priorityValue item\");\n    check(priorityQueue.peekRear().equals(\"low\"),\n        \"peekRear() should return the largest priorityValue item\");\n    check(priorityQueue.peekFront().equals(\"high\"),\n        \"peekFront() should not remove the item\");\n    check(priorityQueue.peekRear().equals(\"low\"),\n        \"peekRear() should not remove the item\");\n    check(priorityQueue.remove().equals(\"high\"),\n        \"remove() should still return the highest priority item after peeking\");\n    check(priorityQueue.remove().equals(\"mid\"),\n        \"remove() should return the middle priority item next\");\n    check(priorityQueue.remove().equals(\"low\"),\n        \"remove() should return the lowest priority item last\");\n  }\n\n  // removal order must depend on priority, not insertion order\n  private static void testInsertionOrderDoesNotMatter() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(10);\n    priorityQueue.insert(50, 5);\n    priorityQueue.insert(10, 1);\n    priorityQueue.insert(40, 4);\n    priorityQueue.insert(20, 2);\n    priorityQueue.insert(30, 3);\n    for (int expected = 10; expected <= 50; expected += 10) {\n      check(priorityQueue.remove() == expected,\n          \"remove() should return \" + expected\n              + \" based on priority, not insertion order\");\n    }\n  }\n\n  // project requirement 3: the internal arrays should resize\n  // automatically when they become full\n  private static void testAutoResize() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(2);\n    // item i gets priority 9 - i, so item 9 is the highest priority\n    for (int i = 0; i < 10; i++) {\n      priorityQueue.insert(i, 9 - i);\n    }\n    check(priorityQueue.peekFront() == 9,\n        \"peekFront() should return the smallest priorityValue item \"\n            + \"after resizing\");\n    check(priorityQueue.peekRear() == 0,\n        \"peekRear() should return the largest priorityValue item \"\n            + \"after resizing\");\n    for (int expected = 9; expected >= 0; expected--) {\n      check(priorityQueue.remove() == expected,\n          \"remove() should return items in ascending priority order after \"\n              + \"resizing (expected \" + expected + \")\");\n    }\n    check(priorityQueue.remove() == null,\n        \"remove() should return null after emptying a resized priority queue\");\n  }\n\n  private static void testInterleavedInsertRemove() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);\n    priorityQueue.insert(5, 5);\n    priorityQueue.insert(1, 1);\n    check(priorityQueue.remove() == 1,\n        \"remove() should return the item with priorityValue 1\");\n    priorityQueue.insert(0, 0);\n    priorityQueue.insert(9, 9);\n    check(priorityQueue.remove() == 0,\n        \"remove() should return the item with priorityValue 0\");\n    check(priorityQueue.remove() == 5,\n        \"remove() should return the item with priorityValue 5\");\n    check(priorityQueue.remove() == 9,\n        \"remove() should return the item with priorityValue 9\");\n    check(priorityQueue.remove() == null,\n        \"remove() should return null when empty\");\n  }\n\n  private static void testSingleElement() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(2);\n    priorityQueue.insert(\"only\", 42);\n    check(priorityQueue.peekFront().equals(\"only\"),\n        \"peekFront() should return the only item\");\n    check(priorityQueue.peekRear().equals(\"only\"),\n        \"peekRear() should return the only item\");\n    check(priorityQueue.remove().equals(\"only\"),\n        \"remove() should return the only item\");\n    check(priorityQueue.remove() == null,\n        \"remove() should return null after removing the only item\");\n    check(priorityQueue.peekFront() == null,\n        \"peekFront() should return null when empty\");\n    check(priorityQueue.peekRear() == null,\n        \"peekRear() should return null when empty\");\n  }\n\n  // with duplicate priorityValues, any order among the duplicates is\n  // acceptable, but the overall removal order must be non-decreasing\n  // in priorityValue\n  private static void testDuplicatePriorities() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);\n    int[] priorities = {5, 3, 5, 1, 3, 1, 4};\n    for (int priority : priorities) {\n      priorityQueue.insert(priority, priority);\n    }\n    int removed = 0;\n    int previous = Integer.MIN_VALUE;\n    while (removed < priorities.length) {\n      Integer item = priorityQueue.remove();\n      check(item != null, \"remove() should return every inserted item\");\n      if (item == null) {\n        break;\n      }\n      check(item >= previous,\n          \"remove() should return items in non-decreasing priority order \"\n              + \"even with duplicate priorities (got \" + item\n              + \" after \" + previous + \")\");\n      previous = item;\n      removed++;\n    }\n    check(priorityQueue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  private static void testNegativeAndZeroPriorities() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(5);\n    priorityQueue.insert(\"zero\", 0);\n    priorityQueue.insert(\"negative\", -5);\n    priorityQueue.insert(\"positive\", 7);\n    check(priorityQueue.remove().equals(\"negative\"),\n        \"remove() should handle negative priorityValues\");\n    check(priorityQueue.remove().equals(\"zero\"),\n        \"remove() should handle a priorityValue of 0\");\n    check(priorityQueue.remove().equals(\"positive\"),\n        \"remove() should return the largest priorityValue last\");\n  }\n\n  // inserting 25 items into an initial capacity of 3 forces several\n  // resizes (3 -> 6 -> 12 -> 24 -> 48)\n  private static void testManyElements() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);\n    int n = 25;\n    for (int i = 0; i < n; i++) {\n      priorityQueue.insert(i, n - 1 - i); // descending priority order\n    }\n    for (int expected = n - 1; expected >= 0; expected--) {\n      check(priorityQueue.remove() == expected,\n          \"remove() should return \" + expected\n              + \" in ascending priority order\");\n    }\n    check(priorityQueue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  // 8.f: toString should describe both heaps\n  private static void testToString() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);\n    priorityQueue.insert(7, 7);\n    String result = priorityQueue.toString();\n    check(result.contains(\"max heap:\"),\n        \"toString() should describe the max heap\");\n    check(result.contains(\"min heap:\"),\n        \"toString() should describe the min heap\");\n    check(result.contains(\"7\"),\n        \"toString() should contain the inserted item\");\n  }\n\n  // project requirement 1: the class must be generic\n  private static void testGenericWithStrings() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(2);\n    priorityQueue.insert(\"pear\", 3);\n    priorityQueue.insert(\"apple\", 1);\n    priorityQueue.insert(\"banana\", 2); // forces a resize\n    check(priorityQueue.peekFront().equals(\"apple\"),\n        \"peekFront() should return \\\"apple\\\" (priorityValue 1)\");\n    check(priorityQueue.peekRear().equals(\"pear\"),\n        \"peekRear() should return \\\"pear\\\" (priorityValue 3)\");\n    check(priorityQueue.remove().equals(\"apple\"),\n        \"remove() should return \\\"apple\\\"\");\n    check(priorityQueue.remove().equals(\"banana\"),\n        \"remove() should return \\\"banana\\\"\");\n    check(priorityQueue.remove().equals(\"pear\"),\n        \"remove() should return \\\"pear\\\"\");\n    check(priorityQueue.remove() == null,\n        \"remove() should return null when empty\");\n  }\n\n  // 8.g: display() should run without errors\n  private static void testDisplay() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);\n    priorityQueue.insert(2, 2);\n    priorityQueue.insert(1, 1);\n    priorityQueue.display();\n    priorityQueue.remove();\n    priorityQueue.display();\n  }\n}\n",
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/PriorityQueueTest.java"
}
```

Created file successfully: src/PriorityQueueTest.java

---

## Assistant

_Thinking:_

Now build and run all three test suites.

All three test files are created. Now let me build and run them


**Tool: shell**

**Input:**
```json
{
  "command": "cd /home/k1mch1/Documents/assignment-2-stack-and-arrays && ./build && echo \"BUILD OK\" && ./run_test StackTest; echo \"exit=$?\""
}
```

BUILD OK
PASS: empty stack returns null on pop/peek
PASS: push then peek
PASS: LIFO order
PASS: interleaved push and pop
PASS: auto resize
PASS: toString
PASS: generic with String items
Current stack: [1, 2]
popIndex: 1
arr.length: 2
Current stack: [1]
popIndex: 0
arr.length: 2
PASS: display runs without errors

StackTest summary: 8 passed, 0 failed
exit=0

Command exited with code 0.

---

## Assistant

_Thinking:_

StackTest passes. Now QueueTest and PriorityQueueTest.

StackTest passes (8/8). Now Queue and PriorityQueue tests:




**Tool: shell**

**Input:**
```json
{
  "command": "cd /home/k1mch1/Documents/assignment-2-stack-and-arrays && ./run_test QueueTest; echo \"exit=$?\""
}
```

PASS: empty queue returns null on remove/peekFront/peekRear
PASS: FIFO order
PASS: peekFront and peekRear
PASS: circular wrap-around
PASS: auto resize
PASS: resize after wrap-around
PASS: toString
FAIL: peekRear when queue is exactly full -- Index -1 out of bounds for length 3
PASS: generic with String items
Current Queue: [1, 2]
queueHead: 2
queueTail: 0
occupied: 2
Current Queue: [2]
queueHead: 2
queueTail: 1
occupied: 1
PASS: display runs without errors

QueueTest summary: 9 passed, 1 failed
exit=1

Command exited with code 0.

**Tool: shell**

**Input:**
```json
{
  "command": "cd /home/k1mch1/Documents/assignment-2-stack-and-arrays && ./run_test PriorityQueueTest; echo \"exit=$?\""
}
```

PASS: empty priority queue returns null
PASS: remove returns smallest priorityValue first
PASS: peekFront and peekRear
PASS: insertion order does not matter
PASS: auto resize
PASS: interleaved insert and remove
PASS: single element
PASS: duplicate priorities
PASS: negative and zero priorities
PASS: many elements with multiple resizes
PASS: toString
PASS: generic with String items
max heap: [Entry[value=2, priority=2, indexInOtherHeap=1], Entry[value=1, priority=1, indexInOtherHeap=0]]
min heap: [Entry[value=1, priority=1, indexInOtherHeap=1], Entry[value=2, priority=2, indexInOtherHeap=0]]
peekFront: 1
peekRear: 2
max heap: [Entry[value=2, priority=2, indexInOtherHeap=0]]
min heap: [Entry[value=2, priority=2, indexInOtherHeap=0]]
peekFront: 2
peekRear: 2
PASS: display runs without errors

PriorityQueueTest summary: 13 passed, 0 failed
exit=0

Command exited with code 0.

---

## Assistant

_Thinking:_

Results:
- StackTest: 8/8 passed ✓
- QueueTest: 9/10 passed, 1 failed: "peekRear when queue is exactly full -- Index -1 out of bounds for length 3"
- PriorityQueueTest: 13/13 passed ✓

The Queue failure is a genuine bug in their code (I didn't modify it): `peekRear()` computes `(queueHead - 1) % arr.length`. When the queue is exactly full, `queueHead` has wrapped to 0, so `(0 - 1) % 3 = -1` in Java (Java's % keeps the sign of the dividend), causing `arr[-1]` → ArrayIndexOutOfBoundsException.

The fix would be `(queueHead - 1 + arr.length) % arr.length`, but the user said "don't modify any of my code", so I just report it.

Note: the shell reported "exit=1" but "Command exited with code 0" — because `echo "exit=$?"` was the last command and it succeeded. The test itself exited 1 as designed (System.exit(1) on failure). Good.

Now summarize for the user:
1. Created 3 new test files, didn't touch existing code.
2. How to run: ./build && ./run_test StackTest (etc.)
3. Results: Stack 8/8, PriorityQueue 13/13, Queue 9/10.
4. The one failure is a real bug in Queue.peekRear() when the queue is exactly full — explain the cause.
5. Their message was cut off ("and make it such that it...") — ask them to finish.

Keep it concise.

Done. I created three new test files (no existing code was modified), all using plain Java `assert` statements runnable via your existing scripts:

**Files created**
- `src/StackTest.java` — 8 tests: empty→null, peek-without-remove, LIFO order, interleaved push/pop, auto-resize (10 pushes into capacity 2), `toString`, generic `String` items, `display()`
- `src/QueueTest.java` — 10 tests: empty→null, FIFO order, peekFront/peekRear, circular wrap-around, auto-resize, resize *while wrapped*, `toString`, full-queue peekRear, generic, `display()`
- `src/PriorityQueueTest.java` — 13 tests: empty→null, smallest `priorityValue` removed first, peekFront/peekRear, insertion-order independence, auto-resize, interleaved insert/remove, single element, duplicate priorities, negative/zero priorities, 25 elements forcing multiple resizes, `toString`, generic, `display()`

**Run them with:** `./build && ./run_test StackTest` (your `run_test` already passes `-ea`)

**Results**
- StackTest: **8/8 passed**
- PriorityQueueTest: **13/13 passed**
- QueueTest: **9/10 passed — 1 real bug found**

**The failure (in your code, not the test):** `Queue.peekRear()` crashes with `Index -1 out of bounds for length 3` when the queue is exactly full. In `Queue.java:66`, `(queueHead - 1) % arr.length` evaluates to `-1` when `queueHead` has wrapped to `0`, because Java's `%` keeps the dividend's sign. The standard fix is `(queueHead - 1 + arr.length) % arr.length` — but per your instruction I left your code untouched.

Also, your message got cut off at "…and make it such that it" — let me know how you want to finish that sentence (e.g., wire the tests into a single runner, make failures non-fatal, add JUnit, etc.) and I'll adjust.

---

## User

run the test again, and if it all passes, make it even more rigorous and try to sift out all of the major edge cases

---

## Assistant

_Thinking:_

The user wants me to:
1. Run the tests again
2. If they all pass, make them more rigorous and sift out all major edge cases

Note: QueueTest currently has 1 failing test (the peekRear bug). The user said "if it all passes" — it doesn't all pass. But the intent is clear: they want more rigorous tests with edge cases. The peekRear bug is in their code, which they said not to modify. So I should run the tests again, report status, and then beef up the tests with more edge cases.

Let me first re-run all three test suites to confirm current status. Then expand the tests with major edge cases:

**Stack edge cases:**
- Zero/negative initial array size? `new Stack<Integer>(0)` — push would immediately resize... wait, `popIndex == arr.length - 1` → -1 == -1 → true, so resize happens on first push. Actually with arraySize=0, arr.length=0, popIndex=-1, so popIndex == arr.length - 1 → -1 == -1 → true → resize to 0*2=0 → still length 0! Then popIndex++ → 0, arr[0] → AIOOBE! So Stack(0) is broken on first push. Hmm, that's an edge case. Is it a "major" edge case? The assignment says `public Stack(int arraySize)` — presumably arraySize > 0 is assumed. But testing Stack(0) would reveal a bug. Should I include it? It's a legit edge case. But maybe the assignment assumes positive sizes. I could include it as a "known limitation" test... Actually, let me think about what "major edge cases" means: empty operations, single element, resize boundaries, wrap-around boundaries, duplicates, negative priorities, large volumes, interleaving.

- Large volume test (e.g., 10000 elements) for stress.
- Push after emptying (reuse).
- peek/pop alternating.
- toString after each pop down to empty.
- Resize boundary: exactly fill to capacity, then one more push (triggers resize at exact boundary).

**Queue edge cases:**
- Same: fill to exact capacity, insert one more (resize at boundary).
- Remove all then insert again (reuse after emptying).
- Large volume.
- Alternating insert/remove one at a time (classic queue torture test).
- peekRear when full (known bug — keep the test, it documents the bug).
- toString after wrap-around.
- Single element queue.
- The peekRear bug: also check peekRear after partial removal from full queue (queueHead still 0 but occupied < length → frontIndex = (0-1)%len = -1 → also crashes!). Wait: after full queue (queueHead=0), remove one → occupied=length-1, queueHead=0 still. peekRear → (0-1)%len = -1 → crash. So peekRear crashes whenever queueHead==0 and non-empty. Let me add that test too.

Actually wait — when is queueHead == 0 with occupied > 0? Only when the queue was exactly full at some point (queueHead wrapped to 0) OR after a resize where occupied equals... after resize, queueHead = occupied (old occupied). If old occupied == new length? No, new length = 2*old length > old occupied. So queueHead=0 only via exact-full wrap. Hmm, but also: queueHead could be 0 if... insert sets queueHead=(queueHead+1)%length; to get 0, previous queueHead must be length-1, meaning the array was full before that insert — but resize prevents that. Unless the resize happened and then... after resize, queueHead=occupied < newLength. Then inserts proceed. queueHead reaches newLength-1 when occupied = newLength (full again). Then next insert resizes. So queueHead=0 ⟺ queue is exactly full (occupied == arr.length). After removing from a full queue, queueHead stays 0 while occupied < length → peekRear still crashes. So the bug affects: any state where the queue was once full and queueHead hasn't wrapped past 0 yet. Let me add that test: fill to capacity, remove one, then peekRear → still crashes.

**PriorityQueue edge cases:**
- All same priorities (e.g., 10 items all priority 5) → removal order non-decreasing (all equal).
- Reverse-sorted insert, already-sorted insert.
- Large volume with distinct priorities (stress, multiple resizes).
- Remove until empty then keep removing.
- Insert after emptying (reuse).
- Alternating insert/remove.
- Priorities that are large values (Integer.MAX_VALUE?), negative.
- peekFront/peekRear consistency after many operations: peekFront should equal the min priority item, peekRear the max. Could verify by tracking.
- Interleaved: insert, remove, insert, remove with priority changes.
- Verify that after removing some items, peekFront returns the correct next-min.
- Duplicate items with different priorities.
- Same item value with different priorities (e.g., insert "A" with priority 1 and "A" with priority 5 — removal order should be A(1) then A(5)).

Also stress test: 1000 elements with pseudo-random priorities, verify sorted removal. Use a deterministic pseudo-random sequence (LCG) to avoid imports... Actually java.util.Random is fine to import. But to keep it simple and deterministic, use a fixed seed Random or an LCG. Using java.util.Random with fixed seed is fine and deterministic.

Also for Stack and Queue: large volume (e.g., 5000) to stress resize.

One consideration: the harness catches AssertionError | RuntimeException per test, so all failures get reported. Good.

Also, should I keep the failing peekRear tests? Yes — they document real bugs. The user said "if it all passes" — it doesn't (Queue has the bug). I should note that: QueueTest still has the peekRear failure, so per the "if it all passes" condition, strictly I should report that not everything passes. But the spirit of the request is to make tests more rigorous. I'll do both: re-run, report that Queue still fails (the bug is in their code, unfixed per their instruction), and still expand the tests with edge cases.

Hmm, but wait — maybe I should ask whether they want me to fix the peekRear bug? They explicitly said "don't modify any of my code". So no. Just report.

Let me also think about whether to add a combined runner (e.g., RunAllTests) — not requested. Skip, or mention.

Now, let me design the additional/expanded tests.

**StackTest additions:**
1. testExactCapacityBoundary: Stack(3); push 1,2,3 (exactly full); peek==3; push 4 (triggers resize); pop 4,3,2,1.
2. testReuseAfterEmptying: push/pop fully, then push again and verify order.
3. testLargeVolume: push 5000, verify peek, pop all in reverse.
4. testAlternatingPushPop: push 1, pop, push 2, pop... many times.
5. testToStringAfterEveryPop: push 1,2,3; check toString at each step down to "[]".
6. testResizeMultipleTimes: covered by large volume.

**QueueTest additions:**
1. testExactCapacityBoundary: Queue(3); insert 1,2,3; remove 1; insert 4 (resize triggers on next insert? No — occupied=3 after insert 4? Let me trace: after insert 1,2,3: occupied=3, queueHead=0. remove 1: occupied=2, tail=1. insert 4: occupied(2)!=3 → arr[queueHead=0]=4, queueHead=1, occupied=3. Now full again with queueHead=1. insert 5: occupied==3 → resize(6): copy from tail=1: [2,3,4], tail=0, head=3. arr[3]=5, head=4, occupied=4. remove → 2,3,4,5. ✓ exercises resize from a wrapped state.
2. testPeekRearAfterPartialRemovalFromFull: Queue(3); insert 1,2,3; remove(); peekRear() → crash (bug). Keep as a documented-bug test.
3. testAlternatingInsertRemove: for i in 1..100: insert(i); remove()==i. Classic FIFO torture with wrap-around.
4. testReuseAfterEmptying.
5. testLargeVolume: insert 5000, remove all in order.
6. testSingleElement: insert 1; peekFront==1; peekRear==1; remove 1; nulls.
7. testToStringAfterWrap: exercise toString when wrapped.

**PriorityQueueTest additions:**
1. testAllSamePriority: 10 items all priority 7; remove all; each removal returns an item (any order among ties); verify count and that... with all-same priorities, any order is valid. Just verify we get all items back (as a multiset — with Integer items equal to each other, just check count and non-decreasing).
2. testSameItemDifferentPriorities: insert "A" p1, "A" p5, "A" p3 → remove order: A,A,A (all same item) — can't distinguish. Use different items: insert ("A",1), ("B",5), ("A",3)? Removal: A(1), A(3), B(5). Hmm the items aren't unique. Better: use distinct items with distinct priorities — already covered. For same item different priorities, use Integer items: insert 7 with p1, 7 with p5 — removal: 7 then 7. Trivially passes. Maybe skip; instead do testSortedInsertion: insert already in ascending priority order; testReverseSortedInsertion: descending.
3. testAlternatingInsertRemove: insert(5,5), remove→5, insert(3,3), remove→3, ... with interleaving priorities: insert(10,10); insert(1,1); remove→1; remove→10; etc.
4. testPeekConsistencyUnderLoad: maintain a java.util.PriorityQueue as oracle? That would be a great rigorous test: use Java's built-in PriorityQueue<Integer> as a reference implementation, insert same (item,priority) pairs, compare removal sequences. But our PQ removes by priority; with ties, order may differ from Java's. Use distinct priorities to make the oracle exact. E.g., 200 pseudo-random distinct priorities, insert into both, remove from both, compare. That's a strong test. Use java.util.Random with fixed seed. Distinct priorities: generate a permutation of 0..n-1 via shuffle.
5. testLargeVolume: 1000 items, distinct priorities, verify ascending removal.
6. testReuseAfterEmptying.
7. testRemoveUntilEmptyThenMore: empty fully, then insert again.
8. testPeekFrontRearAfterRemovals: insert several, remove some, verify peekFront/peekRear match expected min/max remaining. Track with a sorted structure or manually computed expectations.

Also for Queue, an oracle-based test with java.util.ArrayDeque or LinkedList: insert/remove interleaved randomly, compare. That's rigorous. Use Random with fixed seed, n=500 operations, maintain oracle deque, after each op compare remove() results and peekFront/peekRear. Note: our Queue's peekRear == last inserted == oracle's peekLast. peekFront == oracle peekFirst. Great oracle test. But careful: our Queue crashes on peekRear when exactly full — during random ops with a small initial capacity, the queue may become exactly full and trigger the bug. Use a larger initial capacity and cap the number of outstanding items below capacity? No — resize makes capacity grow; "exactly full" states occur transiently. With random ops, the queue could hit exactly full state, and if we call peekRear then, it crashes. To avoid the known bug masking other checks, I could skip peekRear checks when the oracle size equals... hmm, we don't know internal capacity. Alternative: track our own expected capacity: start at initialCapacity, double whenever a resize is triggered (when inserting into a full queue). We can simulate: capacity = initial; before insert, if size == capacity → capacity *= 2 (resize happens first). Then if size == capacity after insert → queue is exactly full → skip peekRear check (known bug). That's doable but couples the test to the resize heuristic (doubling). CustomUtils.getNextResize doubles — it's part of their code. Reasonable.

Actually simpler: in the oracle test, only check peekRear when the size after operations is strictly less than the tracked capacity. Let me implement that.

Similarly for PriorityQueue oracle test: use java.util.PriorityQueue<int[]> or a simple class... With distinct priorities, use TreeMap<Integer,Integer> from priority→item, or just a sorted structure. Simplest: maintain a java.util.PriorityQueue<int[]> where int[] = {priority, item} with distinct priorities, comparator by priority. Then compare removals. peekFront = min priority item, peekRear = max priority item — need max too; use a TreeMap<Integer,Integer> (priority→item) as oracle: firstKey/lastKey give min/max, pollFirstEntry removes min. That's clean. Distinct priorities guaranteed by generating a permutation.

For the PQ oracle test with random interleaved insert/remove: maintain TreeMap<Integer,Integer> oracle; random ops: insert (new item, new unique priority) or remove (compare with oracle.pollFirstEntry().getValue()). Also occasionally check peekFront == oracle.firstKey()'s item and peekRear == oracle.lastKey()'s item. Also verify remove returns null when oracle empty.

For Queue oracle test: ArrayDeque<Integer> oracle; ops: insert (addLast), remove (removeFirst, compare), peekFront (peekFirst), peekRear (peekLast, only when not exactly full per tracked capacity).

These oracle tests are rigorous and would catch subtle heap/circular-buffer bugs. 

Also add a Stack oracle test? Stack is trivial LIFO; a large-volume + interleaved test suffices. Could do a Deque oracle too but overkill; simple loop is exact.

Now, one concern: the random-oracle tests depend on java.util.Random and TreeMap/ArrayDeque — all fine, no external deps.

Let me also reconsider: should the "known bug" tests (peekRear when full) remain failing? Yes — they're spec requirements. The user will see them fail and knows about the bug. I'll keep them but maybe rename to make clear it's a known issue... Keep as is; the failure message explains.

Wait, actually — I should double check the peekRear-after-partial-removal claim. Queue(3): insert 1,2,3 → queueHead=0, occupied=3. remove() → 1, queueTail=1, occupied=2. peekRear(): occupied=2≠0; frontIndex=(0-1)%3=-1 → arr[-1] → AIOOBE. Yes, crashes. Add test.

Also peekRear after resize-from-full? After resize, queueHead=occupied>0, fine.

Edge: Queue(1)? insert 1 → occupied=1, queueHead=0. remove → 1. insert 2 → occupied=0... wait after remove: occupied=0, tail=0, head=0. insert 2: occupied(0)!=1 → arr[0]=2, head=(0+1)%1=0, occupied=1. remove → 2 ✓. peekRear when full: (0-1)%1 = 0? In Java, -1 % 1 = 0. So arr[0] = 2 ✓ works for size 1! Interesting — the bug only manifests for length > 1. Fine.

Stack(0): push → popIndex(-1) == arr.length-1 (-1) → resize to 0 → popIndex=0 → arr[0] AIOOBE. Should I test Stack(0)? The assignment doesn't specify behavior for size 0. It's a pathological input. I could include it as an edge case test that documents the limitation... but it would fail. Hmm. The user said "sift out all of the major edge cases". Stack(0) is a minor edge case; the assignment implies a positive arraySize. I'll skip Stack(0) to avoid noise, or include it? Let me include it but... it would fail with AIOOBE. Is that a "major" edge case? Debatable. I'll leave it out of the suite but could mention it. Actually, let me mention it in the summary as a noted limitation rather than a failing test. Hmm, or include it — more rigor. The user wants to "sift out all of the major edge cases" — a crash on Stack(0) is a real behavior worth knowing. But it's arguable whether the assignment requires handling it. I'll include it as a test marked "zero initial capacity" and let it fail/report — no wait, that adds another "failure" that's arguably out of spec. I'll mention it verbally in the summary instead. Hmm, actually let me include it — being thorough is what was asked. But then "all passes" is even further away...

Let me think about what's most helpful: the user wants rigorous edge-case tests. The peekRear bug is a spec violation (peekRear must work). Stack(0) is undefined behavior per spec (constructor takes arraySize; presumably positive). I'll include Stack(0) test but... hmm. Actually you know what, I'll include it and let the harness report it; the user can decide. No wait — I want the test suite to be meaningful. A failing test for out-of-spec input is noise. I'll skip it in the suite and mention it in my summary as an observation. 

Similarly Queue(0): insert → occupied(0)==arr.length(0) → resize(0) → arr[0] AIOOBE. Same category. Skip, mention.

PriorityQueue(0): insert → occupied(0)==maxHeap.length(0) → resizeArray to 0 → maxHeap[0] AIOOBE. Same. Skip, mention.

OK here's my plan:
1. Re-run all three suites (confirm current state).
2. Expand each test file with additional edge-case tests, including oracle-based randomized tests.
3. Rebuild and re-run.
4. Report: Stack should pass all; PriorityQueue should pass all; Queue will fail the two peekRear tests (known bug) — everything else should pass.

Let me now write the expanded test files. I'll rewrite each file completely with the additions.

**StackTest expanded:**

Existing 8 + new:
- testExactCapacityBoundary
- testReuseAfterEmptying
- testAlternatingPushPop
- testToStringDownToEmpty
- testLargeVolume

**QueueTest expanded:**

Existing 10 + new:
- testPeekRearAfterPartialRemovalFromFull (documents same bug)
- testExactCapacityBoundary
- testSingleElement
- testAlternatingInsertRemove
- testReuseAfterEmptying
- testToStringAfterWrapAround
- testLargeVolume
- testRandomizedAgainstOracle (ArrayDeque oracle)

**PriorityQueueTest expanded:**

Existing 13 + new:
- testAllSamePriority
- testAscendingPriorityInsertion
- testDescendingPriorityInsertion
- testSameItemWithDifferentPriorities — hmm, items must be distinct to verify order... skip or use distinct items. Skip.
- testReuseAfterEmptying
- testLargeVolume
- testPeekFrontRearAfterRemovals
- testRandomizedAgainstOracle (TreeMap oracle)

Let me write the code carefully.

For QueueTest oracle test:

```java
private static void testRandomizedAgainstOracle() {
  java.util.ArrayDeque<Integer> oracle = new java.util.ArrayDeque<Integer>();
  java.util.Random random = new java.util.Random(2026);
  Queue<Integer> queue = new Queue<Integer>(4);
  int capacity = 4; // tracks the expected internal capacity (doubles on resize)
  int nextValue = 0;
  for (int step = 0; step < 500; step++) {
    boolean insert = oracle.isEmpty() || random.nextInt(100) < 55;
    if (insert) {
      if (oracle.size() == capacity) {
        capacity *= 2; // resize happens before the insert
      }
      queue.insert(nextValue);
      oracle.addLast(nextValue);
      nextValue++;
    } else {
      Integer expected = oracle.removeFirst();
      Integer actual = queue.remove();
      check(actual != null && actual.equals(expected),
          "randomized test: remove() should return " + expected + " but returned " + actual);
    }
    // verify peeks
    Integer expectedFront = oracle.peekFirst();
    Integer actualFront = queue.peekFront();
    check(expectedFront == null ? actualFront == null : expectedFront.equals(actualFront),
        "randomized test: peekFront() should return " + expectedFront + " but returned " + actualFront);
    // peekRear only checked when the queue is not exactly full
    // (peekRear() crashes when the internal array is exactly full --
    // see testPeekRearWhenFull)
    if (oracle.size() < capacity) {
      Integer expectedRear = oracle.peekLast();
      Integer actualRear = queue.peekRear();
      check(expectedRear == null ? actualRear == null : expectedRear.equals(actualRear),
          "randomized test: peekRear() should return " + expectedRear + " but returned " + actualRear);
    }
  }
  // drain the rest
  while (!oracle.isEmpty()) {
    Integer expected = oracle.removeFirst();
    Integer actual = queue.remove();
    check(actual != null && actual.equals(expected),
        "randomized test: remove() should return " + expected + " but returned " + actual);
  }
  check(queue.remove() == null, "randomized test: remove() should return null when empty");
}
```

Wait — the resize tracking: resize happens in insert when occupied == arr.length, BEFORE placing the new item. So before insert: if size == capacity → capacity *= 2. After insert, size+1 <= capacity always (since if size==capacity, capacity doubled first). So after insert, size < capacity OR size == capacity? If size was capacity-1 before insert: no resize, after insert size == capacity → exactly full → peekRear would crash. My check `oracle.size() < capacity` correctly skips peekRear in that state. ✓

But wait — does resize always double? CustomUtils.getNextResize returns 2 * arr.length. Yes. ✓

Also initial capacity 4, and the queue never shrinks. ✓

Hmm, one more check — `oracle.size() == capacity` before insert triggers the doubling in my simulation, but the actual resize happens inside insert when occupied == arr.length. occupied == size. ✓.

Edge: what if oracle empty and we remove? I guard with `oracle.isEmpty() ||` for insert choice, so remove only when non-empty. ✓

For PriorityQueue oracle test:

```java
private static void testRandomizedAgainstOracle() {
  java.util.TreeMap<Integer, Integer> oracle = new java.util.TreeMap<Integer, Integer>();
  java.util.Random random = new java.util.Random(2026);
  PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);
  int nextValue = 0;
  int nextPriority = 0;
  for (int step = 0; step < 500; step++) {
    boolean insert = oracle.isEmpty() || random.nextInt(100) < 55;
    if (insert) {
      // priorities are unique so the expected order is unambiguous
      oracle.put(nextPriority, nextValue);
      priorityQueue.insert(nextValue, nextPriority);
      nextValue++;
      nextPriority++;
    } else {
      Integer expected = oracle.pollFirstEntry().getValue();
      Integer actual = priorityQueue.remove();
      check(actual != null && actual.equals(expected),
          "randomized test: remove() should return " + expected + " but returned " + actual);
    }
    if (oracle.isEmpty()) {
      check(priorityQueue.peekFront() == null, ...);
      check(priorityQueue.peekRear() == null, ...);
    } else {
      Integer expectedFront = oracle.firstEntry().getValue();
      Integer actualFront = priorityQueue.peekFront();
      check(...);
      Integer expectedRear = oracle.lastEntry().getValue();
      Integer actualRear = priorityQueue.peekRear();
      check(...);
    }
  }
  // drain
  while (!oracle.isEmpty()) {
    Integer expected = oracle.pollFirstEntry().getValue();
    Integer actual = priorityQueue.remove();
    check(...);
  }
  check(priorityQueue.remove() == null, ...);
}
```

Wait — priorities must be unique for the oracle to be exact. nextPriority increments each insert → unique. ✓ But note: priority values are 0,1,2,... in insertion order — that makes priorities correlated with insertion order. To make it more interesting, shuffle: use random priorities from a shuffled range. E.g., pre-generate a permutation of 0..999 and use in order. Let me do:

```java
int[] priorities = new int[1000];
for (int i = 0; i < priorities.length; i++) priorities[i] = i;
// Fisher-Yates shuffle with the same seeded random
for (int i = priorities.length - 1; i > 0; i--) {
  int j = random.nextInt(i + 1);
  int tmp = priorities[i]; priorities[i] = priorities[j]; priorities[j] = tmp;
}
```

Then each insert uses priorities[insertCount++]. Unique priorities in random order. 

Also the peekRear for PQ = max priority item = oracle.lastEntry() ✓. peekFront = min = oracle.firstEntry() ✓.

For the PQ large volume test: 1000 items with unique shuffled priorities, remove all, verify ascending priorities. With items == priority value (item i has priority i), removal order must be 0,1,2,...,999. Simple:

```java
private static void testLargeVolume() {
  PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);
  int n = 1000;
  int[] priorities = new int[n];
  for (int i = 0; i < n; i++) priorities[i] = i;
  // shuffle (Fisher-Yates)
  java.util.Random random = new java.util.Random(42);
  for (int i = n - 1; i > 0; i--) {
    int j = random.nextInt(i + 1);
    int tmp = priorities[i]; priorities[i] = priorities[j]; priorities[j] = tmp;
  }
  for (int i = 0; i < n; i++) {
    priorityQueue.insert(priorities[i], priorities[i]);
  }
  for (int expected = 0; expected < n; expected++) {
    check(priorityQueue.remove() == expected,
        "large volume test: remove() should return " + expected + " in ascending priority order");
  }
  check(priorityQueue.remove() == null, ...);
}
```

Queue large volume:

```java
private static void testLargeVolume() {
  Queue<Integer> queue = new Queue<Integer>(2);
  int n = 5000;
  for (int i = 0; i < n; i++) queue.insert(i);
  for (int i = 0; i < n; i++) {
    check(queue.remove() == i, "large volume test: remove() should return " + i + " in FIFO order");
  }
  check(queue.remove() == null, ...);
}
```

Stack large volume similar with reverse.

Queue alternating test:

```java
private static void testAlternatingInsertRemove() {
  Queue<Integer> queue = new Queue<Integer>(3);
  for (int i = 1; i <= 100; i++) {
    queue.insert(i);
    check(queue.peekFront() == i, ...);
    check(queue.remove() == i, "alternating test: remove() should return " + i);
    check(queue.remove() == null, ...); // hmm, this removes nothing but checks empty each time — 100 null checks, fine but noisy. Maybe only check null every 10th.
  }
}
```

Actually checking remove()==null after each single-element cycle is fine and cheap. Keep.

Stack alternating:

```java
private static void testAlternatingPushPop() {
  Stack<Integer> stack = new Stack<Integer>(2);
  for (int i = 1; i <= 100; i++) {
    stack.push(i);
    check(stack.peek() == i, ...);
    check(stack.pop() == i, ...);
    check(stack.pop() == null, ...);
  }
}
```

Queue exact capacity boundary:

```java
private static void testExactCapacityBoundary() {
  Queue<Integer> queue = new Queue<Integer>(3);
  queue.insert(1);
  queue.insert(2);
  queue.insert(3); // exactly full
  check(queue.remove() == 1, ...);
  queue.insert(4); // fills the queue up again
  check(queue.peekFront() == 2, ...);
  queue.insert(5); // triggers a resize from 3 to 6
  check(queue.remove() == 2, ...);
  check(queue.remove() == 3, ...);
  check(queue.remove() == 4, ...);
  check(queue.remove() == 5, ...);
  check(queue.remove() == null, ...);
}
```

Wait — after insert 4: occupied=3 (full), queueHead=1. peekFront → arr[tail=1]=2 ✓. insert 5: occupied==3 → resize(6): copy from tail=1: [2,3,4], tail=0, head=3; arr[3]=5, head=4, occupied=4. remove → 2,3,4,5 ✓.

Stack exact capacity boundary:

```java
private static void testExactCapacityBoundary() {
  Stack<Integer> stack = new Stack<Integer>(3);
  stack.push(1);
  stack.push(2);
  stack.push(3); // exactly full
  check(stack.peek() == 3, ...);
  stack.push(4); // triggers a resize from 3 to 6
  check(stack.peek() == 4, ...);
  check(stack.pop() == 4, ...);
  check(stack.pop() == 3, ...);
  check(stack.pop() == 2, ...);
  check(stack.pop() == 1, ...);
  check(stack.pop() == null, ...);
}
```

PQ all same priority:

```java
private static void testAllSamePriority() {
  PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);
  for (int i = 0; i < 10; i++) {
    priorityQueue.insert(i, 7);
  }
  int removed = 0;
  while (removed < 10) {
    Integer item = priorityQueue.remove();
    check(item != null, "all same priority test: remove() should return every inserted item");
    if (item == null) break;
    check(item >= 0 && item < 10, "all same priority test: remove() returned an unexpected item " + item);
    removed++;
  }
  check(priorityQueue.remove() == null, ...);
}
```

Hmm, with all-same priorities, removal order among ties is unspecified, so just check we get 10 distinct items back. To check distinctness, use a boolean[] seen array:

```java
boolean[] seen = new boolean[10];
... seen[item] = true; check(!seen[item]) before setting...
```

Let me do that — verifies no item is lost or duplicated.

PQ ascending/descending insertion:

```java
private static void testAscendingPriorityInsertion() {
  PriorityQueue<Integer> pq = new PriorityQueue<Integer>(5);
  for (int i = 0; i < 10; i++) pq.insert(i, i); // already sorted by priority
  for (int expected = 0; expected < 10; expected++) {
    check(pq.remove() == expected, ...);
  }
}

private static void testDescendingPriorityInsertion() {
  PriorityQueue<Integer> pq = new PriorityQueue<Integer>(5);
  for (int i = 9; i >= 0; i--) pq.insert(i, i); // reverse sorted
  for (int expected = 0; expected < 10; expected++) {
    check(pq.remove() == expected, ...);
  }
}
```

PQ peekFront/peekRear after removals:

```java
private static void testPeekFrontRearAfterRemovals() {
  PriorityQueue<Integer> pq = new PriorityQueue<Integer>(5);
  pq.insert(30, 30);
  pq.insert(10, 10);
  pq.insert(20, 20);
  pq.insert(40, 40);
  pq.remove(); // removes 10
  check(pq.peekFront() == 20, "peekFront() should return 20 after removing the smallest");
  check(pq.peekRear() == 40, "peekRear() should still return 40");
  pq.remove(); // removes 20
  check(pq.peekFront() == 30, ...);
  check(pq.peekRear() == 40, ...);
  pq.insert(5, 5);
  check(pq.peekFront() == 5, "peekFront() should return 5 after inserting a higher priority item");
  pq.insert(50, 50);
  check(pq.peekRear() == 50, "peekRear() should return 50 after inserting a lower priority item");
  pq.remove(); // 5
  pq.remove(); // 30
  pq.remove(); // 40
  check(pq.peekFront() == 50, ...);
  check(pq.peekRear() == 50, ...);
  pq.remove(); // 50
  check(pq.peekFront() == null, ...);
  check(pq.peekRear() == null, ...);
}
```

PQ reuse after emptying:

```java
private static void testReuseAfterEmptying() {
  PriorityQueue<Integer> pq = new PriorityQueue<Integer>(2);
  pq.insert(2, 2);
  pq.insert(1, 1);
  check(pq.remove() == 1, ...);
  check(pq.remove() == 2, ...);
  check(pq.remove() == null, ...);
  // reuse the priority queue after emptying it
  pq.insert(5, 5);
  pq.insert(3, 3);
  check(pq.peekFront() == 3, ...);
  check(pq.remove() == 3, ...);
  check(pq.remove() == 5, ...);
  check(pq.remove() == null, ...);
}
```

Queue reuse after emptying similar. Stack reuse similar.

Queue toString after wrap-around:

```java
private static void testToStringAfterWrapAround() {
  Queue<Integer> queue = new Queue<Integer>(3);
  queue.insert(1);
  queue.insert(2);
  queue.insert(3);
  queue.remove(); // 1
  queue.remove(); // 2
  queue.insert(4);
  queue.insert(5); // triggers resize... 
```

hmm wait, after removing 1,2: occupied=1, tail=2, head=0. insert 4: arr[0]=4, head=1, occupied=2. insert 5: arr[1]=5, head=2, occupied=3 → full. toString → from tail=2: [3, 4, 5]. ✓ no resize yet. 

```java
  check(queue.toString().equals("[3, 4, 5]"),
      "toString() should be \"[3, 4, 5]\" after wrap-around but was \"" + queue.toString() + "\"");
  queue.insert(6); // triggers resize
  check(queue.toString().equals("[3, 4, 5, 6]"), ...);
  queue.remove();
  check(queue.toString().equals("[4, 5, 6]"), ...);
}
```

Stack toString down to empty:

```java
private static void testToStringDownToEmpty() {
  Stack<Integer> stack = new Stack<Integer>(5);
  stack.push(1);
  stack.push(2);
  stack.push(3);
  check(stack.toString().equals("[1, 2, 3]"), ...);
  stack.pop();
  check(stack.toString().equals("[1, 2]"), ...);
  stack.pop();
  check(stack.toString().equals("[1]"), ...);
  stack.pop();
  check(stack.toString().equals("[]"), ...);
}
```

Queue single element:

```java
private static void testSingleElement() {
  Queue<Integer> queue = new Queue<Integer>(3);
  queue.insert(99);
  check(queue.peekFront() == 99, ...);
  check(queue.peekRear() == 99, ...);
  check(queue.remove() == 99, ...);
  check(queue.remove() == null, ...);
  check(queue.peekFront() == null, ...);
  check(queue.peekRear() == null, ...);
}
```

Queue peekRear after partial removal from full:

```java
private static void testPeekRearAfterPartialRemovalFromFull() {
  Queue<Integer> queue = new Queue<Integer>(3);
  queue.insert(1);
  queue.insert(2);
  queue.insert(3); // queue is exactly full and queueHead wraps to 0
  queue.remove(); // queueHead is still 0, but the queue is no longer full
  check(queue.peekFront() == 2, ...);
  check(queue.peekRear() == 3,
      "peekRear() should work after partially draining a full queue");
}
```

This will also fail with the same bug (AIOOBE). Good — documents the bug's scope.

Now — the randomized oracle tests use fully-qualified java.util.* names to avoid import statements... Actually imports are cleaner. But the existing code style has `import java.util.Arrays;` in CustomUtils. I'll add imports at the top of the test files: `import java.util.ArrayDeque;` etc. That's fine.

Hmm wait, one issue with the Queue oracle test: it uses `oracle.size() < capacity` to decide whether to check peekRear. But there's a subtlety — the tracked capacity assumes resize doubles exactly when occupied == arr.length at insert time. That matches the code. ✓

Also, the initial `capacity = 4` matches `new Queue<Integer>(4)`. ✓

Let me also double check the PQ oracle test's resize assumption — I don't need to track capacity there since PQ's peekFront/peekRear don't have the wrap bug (they access heap roots directly). ✓

Now, the failing tests in QueueTest: testPeekRearWhenFull and testPeekRearAfterPartialRemovalFromFull will fail. The summary will show 2 failed. Everything else should pass. I'll clearly explain.

Also should I keep `System.exit(1)` on failure? Yes — scripts show non-zero exit. But note earlier the shell reported exit=0 because of the trailing echo. When I run, I'll capture properly.

One more consideration on the harness: catching `RuntimeException` also catches potential NPEs from my own test bugs (e.g., unboxing null). The message will show. Fine.

Also — `check(item >= previous, ...)` in duplicate priorities test uses auto-unboxing of `item` (non-null checked) ✓.

Let me also think about whether the PQ `testManyElements` (25 items) and new large volume (1000) overlap — fine, keep both.

Now write the three files. They're long; let me be careful with syntax. I'll rewrite each file fully.

Also update the class doc comments to mention the expanded coverage.

Let me also reconsider the run order in main: put the known-bug tests last so the PASS list is clean? The harness catches failures anyway, so order doesn't matter for reporting. Keep logical grouping.

One more edge case for Stack: push null? `stack.push(null)` — then arr[popIndex]=null; pop returns null — ambiguous with empty. The spec doesn't mention null items. Skip (would be a design question, not a spec requirement).

For Queue: insert null → toString handles null ("null" string). Remove returns null — ambiguous. Skip.

OK, writing files now. Also, after writing, rebuild and run all three. Let me also run the original demo mains (UsingStack etc.) to make sure nothing broke — not needed, I didn't touch their code. Skip.

Actually wait, I should double-check one thing in the Queue oracle test: `random.nextInt(100) < 55` — 55% insert when non-empty, always insert when empty. Over 500 steps, size stays moderate. Capacity grows as needed. ✓

And the PQ oracle: same structure. ✓

Let me also make sure about `Integer` comparisons in oracle tests: `actual.equals(expected)` — both Integer, .equals compares values ✓. And `expectedFront == null ? actualFront == null : expectedFront.equals(actualFront)` ✓.

Writing now. After that, build + run all three, capture exit codes properly:

`./run_test StackTest; echo "StackTest exit=$?"` etc.

Let me write the full files.

For StackTest, full content:

```java
/**
 * Unit tests for Stack.java, based on the requirements in
 * assignment_details.pdf (section 6: Stack Class (LIFO)).
 *
 * These tests use Java assert statements, so they must be run with
 * assertions enabled:
 *
 *   ./build && ./run_test StackTest
 */
class StackTest {
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
      System.out.println("Run with: ./run_test StackTest");
    }

    runTest("empty stack returns null on pop/peek",
        StackTest::testEmptyReturnsNull);
    runTest("push then peek", StackTest::testPushAndPeek);
    runTest("LIFO order", StackTest::testLifoOrder);
    runTest("interleaved push and pop", StackTest::testInterleavedPushPop);
    runTest("exact capacity boundary", StackTest::testExactCapacityBoundary);
    runTest("auto resize", StackTest::testAutoResize);
    runTest("alternating push and pop", StackTest::testAlternatingPushPop);
    runTest("reuse after emptying", StackTest::testReuseAfterEmptying);
    runTest("toString", StackTest::testToString);
    runTest("toString down to empty", StackTest::testToStringDownToEmpty);
    runTest("large volume", StackTest::testLargeVolume);
    runTest("generic with String items", StackTest::testGenericWithStrings);
    runTest("display runs without errors", StackTest::testDisplay);

    System.out.printf("%nStackTest summary: %d passed, %d failed%n",
        passed, failed);
    if (failed > 0) {
      System.exit(1);
    }
  }

  ... test methods ...
}
```

Test methods:

```java
  // 6.c/6.d: pop() and peek() should return null when the stack is empty
  private static void testEmptyReturnsNull() {
    Stack<Integer> stack = new Stack<Integer>(5);
    check(stack.pop() == null, "pop() should return null on an empty stack");
    check(stack.peek() == null, "peek() should return null on an empty stack");
    check(stack.pop() == null,
        "pop() should still return null after repeated calls");
  }

  // 6.b/6.d: peek() returns the top item without removing it
  private static void testPushAndPeek() {
    Stack<Integer> stack = new Stack<Integer>(5);
    stack.push(42);
    check(stack.peek() == 42, "peek() should return the last pushed item");
    check(stack.peek() == 42, "peek() should not remove the item");
    check(stack.pop() == 42, "pop() should return the item seen by peek()");
    check(stack.peek() == null,
        "peek() should return null after the stack is emptied");
  }

  // 6.b/6.c: last in, first out
  private static void testLifoOrder() {
    Stack<Integer> stack = new Stack<Integer>(5);
    stack.push(1);
    stack.push(2);
    stack.push(3);
    check(stack.pop() == 3, "pop() should return 3 (last pushed) first");
    check(stack.pop() == 2, "pop() should return 2 next");
    check(stack.pop() == 1, "pop() should return 1 (first pushed) last");
    check(stack.pop() == null,
        "pop() should return null once the stack is empty");
  }

  private static void testInterleavedPushPop() {
    Stack<Integer> stack = new Stack<Integer>(5);
    stack.push(1);
    stack.push(2);
    check(stack.pop() == 2, "pop() should return the most recently pushed item");
    stack.push(3);
    check(stack.pop() == 3, "pop() should return the newly pushed item");
    check(stack.pop() == 1, "pop() should return the remaining item");
    check(stack.pop() == null, "pop() should return null when empty");
  }

  // pushing into a stack that is exactly at capacity must trigger the
  // resize and still preserve the stack contents
  private static void testExactCapacityBoundary() {
    Stack<Integer> stack = new Stack<Integer>(3);
    stack.push(1);
    stack.push(2);
    stack.push(3); // exactly full
    check(stack.peek() == 3, "peek() should return 3 when the stack is exactly full");
    stack.push(4); // triggers a resize from 3 to 6 elements
    check(stack.peek() == 4, "peek() should return 4 after resizing at the capacity boundary");
    check(stack.pop() == 4, "pop() should return 4 first");
    check(stack.pop() == 3, "pop() should return 3 next");
    check(stack.pop() == 2, "pop() should return 2 next");
    check(stack.pop() == 1, "pop() should return 1 last");
    check(stack.pop() == null, "pop() should return null when empty");
  }

  // project requirement 3: the internal array should resize automatically
  // when it becomes full
  private static void testAutoResize() {
    Stack<Integer> stack = new Stack<Integer>(2);
    for (int i = 1; i <= 10; i++) {
      stack.push(i);
    }
    check(stack.peek() == 10,
        "peek() should return the last pushed item after resizing");
    for (int i = 10; i >= 1; i--) {
      check(stack.pop() == i,
          "pop() should preserve LIFO order after resizing (expected " + i + ")");
    }
    check(stack.pop() == null,
        "pop() should return null after emptying a resized stack");
  }

  private static void testAlternatingPushPop() {
    Stack<Integer> stack = new Stack<Integer>(2);
    for (int i = 1; i <= 100; i++) {
      stack.push(i);
      check(stack.peek() == i,
          "alternating test: peek() should return " + i);
      check(stack.pop() == i,
          "alternating test: pop() should return " + i);
      check(stack.pop() == null,
          "alternating test: pop() should return null after popping the only item");
    }
  }

  private static void testReuseAfterEmptying() {
    Stack<Integer> stack = new Stack<Integer>(2);
    stack.push(1);
    stack.push(2);
    check(stack.pop() == 2, "reuse test: pop() should return 2");
    check(stack.pop() == 1, "reuse test: pop() should return 1");
    check(stack.pop() == null, "reuse test: pop() should return null when empty");
    // the stack should behave correctly after being emptied
    stack.push(3);
    stack.push(4);
    stack.push(5);
    check(stack.peek() == 5, "reuse test: peek() should return 5 after reuse");
    check(stack.pop() == 5, "reuse test: pop() should return 5 after reuse");
    check(stack.pop() == 4, "reuse test: pop() should return 4 after reuse");
    check(stack.pop() == 3, "reuse test: pop() should return 3 after reuse");
    check(stack.pop() == null, "reuse test: pop() should return null when empty");
  }

  // 6.e: toString should show the stack contents from bottom to top
  private static void testToString() {
    Stack<Integer> stack = new Stack<Integer>(5);
    check(stack.toString().equals("[]"),
        "toString() of an empty stack should be \"[]\"");
    stack.push(1);
    stack.push(2);
    stack.push(3);
    check(stack.toString().equals("[1, 2, 3]"),
        "toString() should be \"[1, 2, 3]\" but was \"" + stack.toString() + "\"");
    stack.pop();
    check(stack.toString().equals("[1, 2]"),
        "toString() should be \"[1, 2]\" after one pop but was \""
            + stack.toString() + "\"");
  }

  private static void testToStringDownToEmpty() {
    Stack<Integer> stack = new Stack<Integer>(5);
    stack.push(1);
    stack.push(2);
    stack.push(3);
    check(stack.toString().equals("[1, 2, 3]"), "toString() step 0");
    stack.pop();
    check(stack.toString().equals("[1, 2]"), "toString() step 1");
    stack.pop();
    check(stack.toString().equals("[1]"), "toString() step 2");
    stack.pop();
    check(stack.toString().equals("[]"), "toString() step 3");
  }

  private static void testLargeVolume() {
    Stack<Integer> stack = new Stack<Integer>(2);
    int n = 5000;
    for (int i = 0; i < n; i++) {
      stack.push(i);
    }
    check(stack.peek() == n - 1,
        "large volume test: peek() should return the last pushed item");
    for (int i = n - 1; i >= 0; i--) {
      check(stack.pop() == i,
          "large volume test: pop() should return " + i + " in LIFO order");
    }
    check(stack.pop() == null,
        "large volume test: pop() should return null when empty");
  }

  // project requirement 1: the class must be generic
  private static void testGenericWithStrings() {
    Stack<String> stack = new Stack<String>(2);
    stack.push("a");
    stack.push("b");
    stack.push("c"); // forces a resize
    check(stack.peek().equals("c"), "peek() should return \"c\"");
    check(stack.pop().equals("c"), "pop() should return \"c\"");
    check(stack.pop().equals("b"), "pop() should return \"b\"");
    check(stack.pop().equals("a"), "pop() should return \"a\"");
    check(stack.pop() == null, "pop() should return null when empty");
  }

  // 6.f: display() should run without errors
  private static void testDisplay() {
    Stack<Integer> stack = new Stack<Integer>(2);
    stack.push(1);
    stack.push(2);
    stack.display();
    stack.pop();
    stack.display();
  }
}
```

QueueTest full content — similar structure with the additions. PriorityQueueTest similarly.

Let me now also double check `testAlternatingPushPop` for Stack(2): push i → popIndex cycles 0→pop→-1. Never resizes. Fine. Maybe make it Stack(1) to be extra spicy: push → popIndex==0==arr.length-1 → resize to 2 every push when empty... wait: Stack(1): push 1: popIndex(-1) == 0? -1 == 0 false → no resize. popIndex=0, arr[0]=1. pop → returns 1, popIndex=-1. Next push: popIndex(-1) == arr.length-1 (0)? No → arr[0]=item. Fine, no resize ever needed since stack never holds >1. OK Stack(2) is fine.

For Queue alternating with Queue(3): insert i → occupied 1, remove → occupied 0. tail/head cycle through 0,1,2,0,1,2... over 100 iterations exercises wrap many times. 

Now QueueTest:

```java
class QueueTest {
  ... harness ...

  public static void main(String[] args) {
    ... asserts check ...

    runTest("empty queue returns null on remove/peekFront/peekRear",
        QueueTest::testEmptyReturnsNull);
    runTest("FIFO order", QueueTest::testFifoOrder);
    runTest("peekFront and peekRear", QueueTest::testPeekFrontAndRear);
    runTest("single element", QueueTest::testSingleElement);
    runTest("circular wrap-around", QueueTest::testCircularWrapAround);
    runTest("exact capacity boundary", QueueTest::testExactCapacityBoundary);
    runTest("auto resize", QueueTest::testAutoResize);
    runTest("resize after wrap-around", QueueTest::testResizeAfterWrapAround);
    runTest("alternating insert and remove", QueueTest::testAlternatingInsertRemove);
    runTest("reuse after emptying", QueueTest::testReuseAfterEmptying);
    runTest("toString", QueueTest::testToString);
    runTest("toString after wrap-around", QueueTest::testToStringAfterWrapAround);
    runTest("large volume", QueueTest::testLargeVolume);
    runTest("randomized test against a reference queue",
        QueueTest::testRandomizedAgainstOracle);
    runTest("generic with String items", QueueTest::testGenericWithStrings);
    runTest("display runs without errors", QueueTest::testDisplay);
    // known bugs in Queue.peekRear() when the internal array is
    // exactly full -- these fail until peekRear() is fixed
    runTest("peekRear when queue is exactly full", QueueTest::testPeekRearWhenFull);
    runTest("peekRear after partially draining a full queue",
        QueueTest::testPeekRearAfterPartialDrain);

    ... summary ...
  }
}
```

Putting the known-bug tests last with a comment. 

PriorityQueueTest main:

```java
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
```

PQ alternating insert/remove test:

```java
  private static void testAlternatingInsertRemove() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);
    priorityQueue.insert(10, 10);
    priorityQueue.insert(2, 2);
    check(priorityQueue.remove() == 2, "alternating test: remove() should return 2");
    priorityQueue.insert(1, 1);
    check(priorityQueue.remove() == 1, "alternating test: remove() should return 1");
    check(priorityQueue.remove() == 10, "alternating test: remove() should return 10");
    check(priorityQueue.remove() == null, "alternating test: remove() should return null when empty");
    for (int i = 0; i < 50; i++) {
      priorityQueue.insert(i, 100 - i);
      check(priorityQueue.remove() == i,
          "alternating test: remove() should return " + i);
    }
  }
```

Hmm wait, in the loop: insert(i, 100-i) then immediately remove → the queue may contain leftover items? Let me trace: after the initial drain, queue empty. Loop i=0: insert(0,100) → occupied=1. remove → 0 ✓ occupied=0. i=1: insert(1,99) → remove → 1 ✓. Each iteration the queue is empty before insert, so remove returns i. ✓ But that doesn't really test interleaving with multiple items. Better: keep a sliding window:

```java
  private static void testAlternatingInsertRemove() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);
    // keep a small backlog of items and remove the best one each round
    int[][] items = {{10, 10}, {2, 2}, {5, 5}, {1, 1}, {8, 8}};
    ...
  }
```

Hmm, let me do something simpler but meaningful:

```java
  private static void testAlternatingInsertRemove() {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);
    priorityQueue.insert(10, 10);
    priorityQueue.insert(2, 2);
    check(priorityQueue.remove() == 2, "alternating test: remove() should return 2");
    priorityQueue.insert(1, 1);
    check(priorityQueue.remove() == 1, "alternating test: remove() should return 1");
    check(priorityQueue.remove() == 10, "alternating test: remove() should return 10");
    check(priorityQueue.remove() == null, "alternating test: remove() should return null when empty");
    // insert two items, remove the best, insert another, remove the best, ...
    priorityQueue.insert(7, 7);
    priorityQueue.insert(3, 3);
    check(priorityQueue.remove() == 3, "alternating test: remove() should return 3");
    priorityQueue.insert(2, 2);
    check(priorityQueue.remove() == 2, "alternating test: remove() should return 2");
    priorityQueue.insert(9, 9);
    check(priorityQueue.remove() == 7, "alternating test: remove() should return 7");
    check(priorityQueue.remove() == 9, "alternating test: remove() should return 9");
    check(priorityQueue.remove() == null, "alternating test: remove() should return null when empty");
  }
```

Trace: insert(7,7), insert(3,3): remove → 3 ✓. insert(2,2): queue has 7,2 → remove → 2 ✓. insert(9,9): queue has 7,9 → remove → 7 ✓. remove → 9 ✓. ✓

All Same Priority with seen array:

```java
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
          "all identical priorities test: remove() should return every inserted item");
      if (item == null) {
        break;
      }
      check(item >= 0 && item < n,
          "all identical priorities test: remove() returned an unexpected item " + item);
      check(!seen[item],
          "all identical priorities test: remove() returned item " + item + " twice");
      seen[item] = true;
      removed++;
    }
    check(priorityQueue.remove() == null,
        "all identical priorities test: remove() should return null when empty");
  }
```

✓ (any order among equal priorities is valid, but all items must come back exactly once).

Large volume PQ (1000, shuffled unique priorities) as designed above.

Randomized oracle PQ test as designed above with Fisher-Yates shuffle of unique priorities.

Let me now write out all three files completely and carefully. Then build and run.

Also note: the user's instruction "run the test again, and if it all passes, make it even more rigorous" — strictly, not all passes (Queue peekRear bug). I'll re-run first to confirm state, then proceed with the rigor expansion anyway (clearly the intent), and note that the Queue bug still needs their fix.

Actually, let me re-run first (quick), then write files, then build+run again. Let me do the re-run now, in parallel with... no, writes depend on nothing. I'll just do the re-run quickly first since they asked, then write.

Actually I can do the re-run and then the writes in sequence. Let me re-run all three first.

**Tool: shell**

**Input:**
```json
{
  "command": "cd /home/k1mch1/Documents/assignment-2-stack-and-arrays && ./run_test StackTest > /tmp/stack.log 2>&1; echo \"StackTest exit=$?\"; tail -2 /tmp/stack.log; ./run_test QueueTest > /tmp/queue.log 2>&1; echo \"QueueTest exit=$?\"; grep -E \"FAIL|summary\" /tmp/queue.log; ./run_test PriorityQueueTest > /tmp/pq.log 2>&1; echo \"PriorityQueueTest exit=$?\"; tail -2 /tmp/pq.log"
}
```

StackTest exit=0

StackTest summary: 8 passed, 0 failed
QueueTest exit=1
FAIL: peekRear when queue is exactly full -- Index -1 out of bounds for length 3
QueueTest summary: 9 passed, 1 failed
PriorityQueueTest exit=0

PriorityQueueTest summary: 13 passed, 0 failed

Command exited with code 0.

---

## Assistant

_Thinking:_

Confirmed current state:
- StackTest: 8/8 pass
- QueueTest: 9/10 — the peekRear-when-full bug still fails (it's in their code, which I must not modify)
- PriorityQueueTest: 13/13 pass

Since the Queue bug is in their code (not the test), I'll proceed with making all three suites more rigorous as requested. The user's intent is clear: expand edge-case coverage. I'll note the caveat.

Now write the expanded test files. Let me write all three completely.

Current state confirmed: Stack 8/8, PriorityQueue 13/13, Queue 9/10 (the one failure is the `peekRear()` bug in your code — not a test problem — so I'll keep that test and expand everything else). Now let me make all three suites much more rigorous with edge cases, including randomized differential tests against Java's built-in reference implementations:




**Tool: write**

**Input:**
```json
{
  "content": "/**\n * Unit tests for Stack.java, based on the requirements in\n * assignment_details.pdf (section 6: Stack Class (LIFO)).\n *\n * These tests use Java assert statements, so they must be run with\n * assertions enabled:\n *\n *   ./build && ./run_test StackTest\n */\nclass StackTest {\n  private static int passed = 0;\n  private static int failed = 0;\n\n  private static void check(boolean condition, String message) {\n    assert condition : message;\n  }\n\n  private static void runTest(String name, Runnable test) {\n    try {\n      test.run();\n      passed++;\n      System.out.printf(\"PASS: %s%n\", name);\n    } catch (AssertionError | RuntimeException e) {\n      failed++;\n      System.out.printf(\"FAIL: %s -- %s%n\", name, e.getMessage());\n    }\n  }\n\n  public static void main(String[] args) {\n    boolean assertsEnabled = false;\n    assert assertsEnabled = true;\n    if (!assertsEnabled) {\n      System.out.println(\n          \"WARNING: assertions are disabled, every check will silently pass.\");\n      System.out.println(\"Run with: ./run_test StackTest\");\n    }\n\n    runTest(\"empty stack returns null on pop/peek\",\n        StackTest::testEmptyReturnsNull);\n    runTest(\"push then peek\", StackTest::testPushAndPeek);\n    runTest(\"LIFO order\", StackTest::testLifoOrder);\n    runTest(\"interleaved push and pop\", StackTest::testInterleavedPushPop);\n    runTest(\"exact capacity boundary\", StackTest::testExactCapacityBoundary);\n    runTest(\"auto resize\", StackTest::testAutoResize);\n    runTest(\"alternating push and pop\", StackTest::testAlternatingPushPop);\n    runTest(\"reuse after emptying\", StackTest::testReuseAfterEmptying);\n    runTest(\"toString\", StackTest::testToString);\n    runTest(\"toString down to empty\", StackTest::testToStringDownToEmpty);\n    runTest(\"large volume\", StackTest::testLargeVolume);\n    runTest(\"generic with String items\", StackTest::testGenericWithStrings);\n    runTest(\"display runs without errors\", StackTest::testDisplay);\n\n    System.out.printf(\"%nStackTest summary: %d passed, %d failed%n\",\n        passed, failed);\n    if (failed > 0) {\n      System.exit(1);\n    }\n  }\n\n  // 6.c/6.d: pop() and peek() should return null when the stack is empty\n  private static void testEmptyReturnsNull() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    check(stack.pop() == null, \"pop() should return null on an empty stack\");\n    check(stack.peek() == null, \"peek() should return null on an empty stack\");\n    check(stack.pop() == null,\n        \"pop() should still return null after repeated calls\");\n  }\n\n  // 6.b/6.d: peek() returns the top item without removing it\n  private static void testPushAndPeek() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    stack.push(42);\n    check(stack.peek() == 42, \"peek() should return the last pushed item\");\n    check(stack.peek() == 42, \"peek() should not remove the item\");\n    check(stack.pop() == 42, \"pop() should return the item seen by peek()\");\n    check(stack.peek() == null,\n        \"peek() should return null after the stack is emptied\");\n  }\n\n  // 6.b/6.c: last in, first out\n  private static void testLifoOrder() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    stack.push(1);\n    stack.push(2);\n    stack.push(3);\n    check(stack.pop() == 3, \"pop() should return 3 (last pushed) first\");\n    check(stack.pop() == 2, \"pop() should return 2 next\");\n    check(stack.pop() == 1, \"pop() should return 1 (first pushed) last\");\n    check(stack.pop() == null,\n        \"pop() should return null once the stack is empty\");\n  }\n\n  private static void testInterleavedPushPop() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    stack.push(1);\n    stack.push(2);\n    check(stack.pop() == 2, \"pop() should return the most recently pushed item\");\n    stack.push(3);\n    check(stack.pop() == 3, \"pop() should return the newly pushed item\");\n    check(stack.pop() == 1, \"pop() should return the remaining item\");\n    check(stack.pop() == null, \"pop() should return null when empty\");\n  }\n\n  // pushing into a stack that is exactly at capacity must trigger the\n  // resize and still preserve the stack contents\n  private static void testExactCapacityBoundary() {\n    Stack<Integer> stack = new Stack<Integer>(3);\n    stack.push(1);\n    stack.push(2);\n    stack.push(3); // exactly full\n    check(stack.peek() == 3,\n        \"boundary test: peek() should return 3 when exactly full\");\n    stack.push(4); // triggers a resize from 3 to 6 elements\n    check(stack.peek() == 4,\n        \"boundary test: peek() should return 4 after resizing at the boundary\");\n    check(stack.pop() == 4, \"boundary test: pop() should return 4 first\");\n    check(stack.pop() == 3, \"boundary test: pop() should return 3 next\");\n    check(stack.pop() == 2, \"boundary test: pop() should return 2 next\");\n    check(stack.pop() == 1, \"boundary test: pop() should return 1 last\");\n    check(stack.pop() == null, \"boundary test: pop() should return null when empty\");\n  }\n\n  // project requirement 3: the internal array should resize automatically\n  // when it becomes full\n  private static void testAutoResize() {\n    Stack<Integer> stack = new Stack<Integer>(2);\n    for (int i = 1; i <= 10; i++) {\n      stack.push(i);\n    }\n    check(stack.peek() == 10,\n        \"peek() should return the last pushed item after resizing\");\n    for (int i = 10; i >= 1; i--) {\n      check(stack.pop() == i,\n          \"pop() should preserve LIFO order after resizing (expected \" + i + \")\");\n    }\n    check(stack.pop() == null,\n        \"pop() should return null after emptying a resized stack\");\n  }\n\n  // repeatedly fill and empty a single slot so that popIndex oscillates\n  // around the boundaries of the internal array\n  private static void testAlternatingPushPop() {\n    Stack<Integer> stack = new Stack<Integer>(2);\n    for (int i = 1; i <= 100; i++) {\n      stack.push(i);\n      check(stack.peek() == i,\n          \"alternating test: peek() should return \" + i);\n      check(stack.pop() == i,\n          \"alternating test: pop() should return \" + i);\n      check(stack.pop() == null,\n          \"alternating test: pop() should return null when the stack is empty\");\n    }\n  }\n\n  // the stack should behave correctly after being completely emptied\n  private static void testReuseAfterEmptying() {\n    Stack<Integer> stack = new Stack<Integer>(2);\n    stack.push(1);\n    stack.push(2);\n    check(stack.pop() == 2, \"reuse test: pop() should return 2\");\n    check(stack.pop() == 1, \"reuse test: pop() should return 1\");\n    check(stack.pop() == null, \"reuse test: pop() should return null when empty\");\n    stack.push(3);\n    stack.push(4);\n    stack.push(5);\n    check(stack.peek() == 5, \"reuse test: peek() should return 5 after reuse\");\n    check(stack.pop() == 5, \"reuse test: pop() should return 5 after reuse\");\n    check(stack.pop() == 4, \"reuse test: pop() should return 4 after reuse\");\n    check(stack.pop() == 3, \"reuse test: pop() should return 3 after reuse\");\n    check(stack.pop() == null, \"reuse test: pop() should return null when empty\");\n  }\n\n  // 6.e: toString should show the stack contents from bottom to top\n  private static void testToString() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    check(stack.toString().equals(\"[]\"),\n        \"toString() of an empty stack should be \\\"[]\\\"\");\n    stack.push(1);\n    stack.push(2);\n    stack.push(3);\n    check(stack.toString().equals(\"[1, 2, 3]\"),\n        \"toString() should be \\\"[1, 2, 3]\\\" but was \\\"\" + stack.toString() + \"\\\"\");\n    stack.pop();\n    check(stack.toString().equals(\"[1, 2]\"),\n        \"toString() should be \\\"[1, 2]\\\" after one pop but was \\\"\"\n            + stack.toString() + \"\\\"\");\n  }\n\n  private static void testToStringDownToEmpty() {\n    Stack<Integer> stack = new Stack<Integer>(5);\n    stack.push(1);\n    stack.push(2);\n    stack.push(3);\n    check(stack.toString().equals(\"[1, 2, 3]\"), \"toString() after pushing 1, 2, 3\");\n    stack.pop();\n    check(stack.toString().equals(\"[1, 2]\"), \"toString() after popping once\");\n    stack.pop();\n    check(stack.toString().equals(\"[1]\"), \"toString() after popping twice\");\n    stack.pop();\n    check(stack.toString().equals(\"[]\"),\n        \"toString() should be \\\"[]\\\" after popping everything\");\n  }\n\n  // stress the resize logic with many doublings\n  private static void testLargeVolume() {\n    Stack<Integer> stack = new Stack<Integer>(2);\n    int n = 5000;\n    for (int i = 0; i < n; i++) {\n      stack.push(i);\n    }\n    check(stack.peek() == n - 1,\n        \"large volume test: peek() should return the last pushed item\");\n    for (int i = n - 1; i >= 0; i--) {\n      check(stack.pop() == i,\n          \"large volume test: pop() should return \" + i + \" in LIFO order\");\n    }\n    check(stack.pop() == null,\n        \"large volume test: pop() should return null when empty\");\n  }\n\n  // project requirement 1: the class must be generic\n  private static void testGenericWithStrings() {\n    Stack<String> stack = new Stack<String>(2);\n    stack.push(\"a\");\n    stack.push(\"b\");\n    stack.push(\"c\"); // forces a resize\n    check(stack.peek().equals(\"c\"), \"peek() should return \\\"c\\\"\");\n    check(stack.pop().equals(\"c\"), \"pop() should return \\\"c\\\"\");\n    check(stack.pop().equals(\"b\"), \"pop() should return \\\"b\\\"\");\n    check(stack.pop().equals(\"a\"), \"pop() should return \\\"a\\\"\");\n    check(stack.pop() == null, \"pop() should return null when empty\");\n  }\n\n  // 6.f: display() should run without errors\n  private static void testDisplay() {\n    Stack<Integer> stack = new Stack<Integer>(2);\n    stack.push(1);\n    stack.push(2);\n    stack.display();\n    stack.pop();\n    stack.display();\n  }\n}\n",
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/StackTest.java"
}
```

Wrote file successfully: src/StackTest.java

**Tool: write**

**Input:**
```json
{
  "content": "/**\n * Unit tests for Queue.java, based on the requirements in\n * assignment_details.pdf (section 7: Queue Class (FIFO)).\n *\n * These tests use Java assert statements, so they must be run with\n * assertions enabled:\n *\n *   ./build && ./run_test QueueTest\n *\n * Note: two tests at the bottom document a bug in peekRear() when the\n * internal array is exactly full -- they will fail until peekRear()\n * uses a non-negative modulo, e.g. (queueHead - 1 + arr.length)\n * % arr.length.\n */\nimport java.util.ArrayDeque;\nimport java.util.Random;\n\nclass QueueTest {\n  private static int passed = 0;\n  private static int failed = 0;\n\n  private static void check(boolean condition, String message) {\n    assert condition : message;\n  }\n\n  private static void runTest(String name, Runnable test) {\n    try {\n      test.run();\n      passed++;\n      System.out.printf(\"PASS: %s%n\", name);\n    } catch (AssertionError | RuntimeException e) {\n      failed++;\n      System.out.printf(\"FAIL: %s -- %s%n\", name, e.getMessage());\n    }\n  }\n\n  public static void main(String[] args) {\n    boolean assertsEnabled = false;\n    assert assertsEnabled = true;\n    if (!assertsEnabled) {\n      System.out.println(\n          \"WARNING: assertions are disabled, every check will silently pass.\");\n      System.out.println(\"Run with: ./run_test QueueTest\");\n    }\n\n    runTest(\"empty queue returns null on remove/peekFront/peekRear\",\n        QueueTest::testEmptyReturnsNull);\n    runTest(\"FIFO order\", QueueTest::testFifoOrder);\n    runTest(\"peekFront and peekRear\", QueueTest::testPeekFrontAndRear);\n    runTest(\"single element\", QueueTest::testSingleElement);\n    runTest(\"circular wrap-around\", QueueTest::testCircularWrapAround);\n    runTest(\"exact capacity boundary\", QueueTest::testExactCapacityBoundary);\n    runTest(\"auto resize\", QueueTest::testAutoResize);\n    runTest(\"resize after wrap-around\", QueueTest::testResizeAfterWrapAround);\n    runTest(\"alternating insert and remove\", QueueTest::testAlternatingInsertRemove);\n    runTest(\"reuse after emptying\", QueueTest::testReuseAfterEmptying);\n    runTest(\"toString\", QueueTest::testToString);\n    runTest(\"toString after wrap-around\", QueueTest::testToStringAfterWrapAround);\n    runTest(\"large volume\", QueueTest::testLargeVolume);\n    runTest(\"randomized test against a reference queue\",\n        QueueTest::testRandomizedAgainstOracle);\n    runTest(\"generic with String items\", QueueTest::testGenericWithStrings);\n    runTest(\"display runs without errors\", QueueTest::testDisplay);\n\n    // known bug: peekRear() crashes whenever the internal array is\n    // exactly full (queueHead has wrapped to 0, so\n    // (queueHead - 1) % arr.length == -1)\n    runTest(\"peekRear when queue is exactly full\", QueueTest::testPeekRearWhenFull);\n    runTest(\"peekRear after partially draining a full queue\",\n        QueueTest::testPeekRearAfterPartialDrain);\n\n    System.out.printf(\"%nQueueTest summary: %d passed, %d failed%n\",\n        passed, failed);\n    if (failed > 0) {\n      System.exit(1);\n    }\n  }\n\n  // 7.c/7.d/7.e: remove(), peekFront() and peekRear() should return\n  // null when the queue is empty\n  private static void testEmptyReturnsNull() {\n    Queue<Integer> queue = new Queue<Integer>(5);\n    check(queue.remove() == null, \"remove() should return null on an empty queue\");\n    check(queue.peekFront() == null,\n        \"peekFront() should return null on an empty queue\");\n    check(queue.peekRear() == null,\n        \"peekRear() should return null on an empty queue\");\n    check(queue.remove() == null,\n        \"remove() should still return null after repeated calls\");\n  }\n\n  // 7.b/7.c: first in, first out\n  private static void testFifoOrder() {\n    Queue<Integer> queue = new Queue<Integer>(5);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3);\n    check(queue.remove() == 1, \"remove() should return 1 (first inserted) first\");\n    check(queue.remove() == 2, \"remove() should return 2 next\");\n    check(queue.remove() == 3, \"remove() should return 3 last\");\n    check(queue.remove() == null,\n        \"remove() should return null once the queue is empty\");\n  }\n\n  // 7.d/7.e: peeking should not remove anything\n  private static void testPeekFrontAndRear() {\n    Queue<Integer> queue = new Queue<Integer>(5);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3);\n    check(queue.peekFront() == 1,\n        \"peekFront() should return the first inserted item\");\n    check(queue.peekRear() == 3,\n        \"peekRear() should return the last inserted item\");\n    check(queue.peekFront() == 1, \"peekFront() should not remove the item\");\n    check(queue.peekRear() == 3, \"peekRear() should not remove the item\");\n    check(queue.remove() == 1,\n        \"remove() should still return items in FIFO order after peeking\");\n    check(queue.remove() == 2, \"remove() should return 2 next\");\n    check(queue.remove() == 3, \"remove() should return 3 last\");\n  }\n\n  private static void testSingleElement() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(99);\n    check(queue.peekFront() == 99, \"peekFront() should return the only item\");\n    check(queue.peekRear() == 99, \"peekRear() should return the only item\");\n    check(queue.remove() == 99, \"remove() should return the only item\");\n    check(queue.remove() == null, \"remove() should return null after draining\");\n    check(queue.peekFront() == null, \"peekFront() should return null when empty\");\n    check(queue.peekRear() == null, \"peekRear() should return null when empty\");\n  }\n\n  // the queue is implemented as a circular array, so both indices must\n  // wrap around correctly\n  private static void testCircularWrapAround() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    check(queue.remove() == 1, \"remove() should return 1\");\n    queue.insert(3); // queueHead wraps around to index 0\n    check(queue.peekFront() == 2,\n        \"peekFront() should return 2 after the head wrapped around\");\n    check(queue.remove() == 2,\n        \"remove() should return 2 after the head wrapped around\");\n    check(queue.remove() == 3,\n        \"remove() should return 3 after the head wrapped around\");\n    queue.insert(4); // queueTail wraps around to index 0\n    check(queue.remove() == 4,\n        \"remove() should return 4 after both indices wrapped around\");\n    check(queue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  // draining and refilling to exactly the capacity must trigger the\n  // resize and still preserve the FIFO order\n  private static void testExactCapacityBoundary() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3); // exactly full, queueHead wraps to 0\n    check(queue.remove() == 1, \"boundary test: remove() should return 1\");\n    queue.insert(4); // fills the queue up again\n    check(queue.peekFront() == 2,\n        \"boundary test: peekFront() should return 2\");\n    queue.insert(5); // triggers a resize from 3 to 6 elements\n    check(queue.remove() == 2, \"boundary test: remove() should return 2\");\n    check(queue.remove() == 3, \"boundary test: remove() should return 3\");\n    check(queue.remove() == 4, \"boundary test: remove() should return 4\");\n    check(queue.remove() == 5, \"boundary test: remove() should return 5\");\n    check(queue.remove() == null,\n        \"boundary test: remove() should return null when empty\");\n  }\n\n  // project requirement 3: the internal array should resize automatically\n  // when it becomes full\n  private static void testAutoResize() {\n    Queue<Integer> queue = new Queue<Integer>(2);\n    for (int i = 1; i <= 10; i++) {\n      queue.insert(i);\n    }\n    check(queue.peekFront() == 1, \"peekFront() should return 1 after resizing\");\n    check(queue.peekRear() == 10, \"peekRear() should return 10 after resizing\");\n    for (int i = 1; i <= 10; i++) {\n      check(queue.remove() == i,\n          \"remove() should preserve FIFO order after resizing (expected \" + i + \")\");\n    }\n    check(queue.remove() == null,\n        \"remove() should return null after emptying a resized queue\");\n  }\n\n  // resizing must preserve the FIFO order even when the elements are\n  // wrapped around the end of the internal array\n  private static void testResizeAfterWrapAround() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3);\n    check(queue.remove() == 1, \"remove() should return 1\");\n    check(queue.remove() == 2, \"remove() should return 2\");\n    // queueTail = 2 and queueHead = 0, so the next insert triggers a\n    // resize while the remaining elements are wrapped around the array\n    queue.insert(4);\n    queue.insert(5);\n    queue.insert(6); // triggers a resize from 3 to 6 elements\n    queue.insert(7);\n    for (int i = 3; i <= 7; i++) {\n      check(queue.remove() == i,\n          \"remove() should preserve FIFO order across a wrap-around resize \"\n              + \"(expected \" + i + \")\");\n    }\n    check(queue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  // repeatedly insert one item and remove it, so that queueHead and\n  // queueTail walk around the circular array many times\n  private static void testAlternatingInsertRemove() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    for (int i = 1; i <= 100; i++) {\n      queue.insert(i);\n      check(queue.peekFront() == i,\n          \"alternating test: peekFront() should return \" + i);\n      check(queue.peekRear() == i,\n          \"alternating test: peekRear() should return \" + i);\n      check(queue.remove() == i,\n          \"alternating test: remove() should return \" + i);\n      check(queue.remove() == null,\n          \"alternating test: remove() should return null when empty\");\n    }\n  }\n\n  // the queue should behave correctly after being completely emptied\n  private static void testReuseAfterEmptying() {\n    Queue<Integer> queue = new Queue<Integer>(2);\n    queue.insert(1);\n    queue.insert(2);\n    check(queue.remove() == 1, \"reuse test: remove() should return 1\");\n    check(queue.remove() == 2, \"reuse test: remove() should return 2\");\n    check(queue.remove() == null, \"reuse test: remove() should return null when empty\");\n    queue.insert(3);\n    queue.insert(4);\n    queue.insert(5); // forces a resize\n    check(queue.peekFront() == 3, \"reuse test: peekFront() should return 3\");\n    check(queue.peekRear() == 5, \"reuse test: peekRear() should return 5\");\n    check(queue.remove() == 3, \"reuse test: remove() should return 3\");\n    check(queue.remove() == 4, \"reuse test: remove() should return 4\");\n    check(queue.remove() == 5, \"reuse test: remove() should return 5\");\n    check(queue.remove() == null, \"reuse test: remove() should return null when empty\");\n  }\n\n  // 7.f: toString should show the queue contents from front to rear\n  private static void testToString() {\n    Queue<Integer> queue = new Queue<Integer>(5);\n    check(queue.toString().equals(\"[]\"),\n        \"toString() of an empty queue should be \\\"[]\\\"\");\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3);\n    check(queue.toString().equals(\"[1, 2, 3]\"),\n        \"toString() should be \\\"[1, 2, 3]\\\" but was \\\"\" + queue.toString() + \"\\\"\");\n    queue.remove();\n    check(queue.toString().equals(\"[2, 3]\"),\n        \"toString() should be \\\"[2, 3]\\\" after one removal but was \\\"\"\n            + queue.toString() + \"\\\"\");\n  }\n\n  private static void testToStringAfterWrapAround() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3);\n    queue.remove(); // 1\n    queue.remove(); // 2\n    queue.insert(4);\n    queue.insert(5); // the queue is full again, wrapped around the array\n    check(queue.toString().equals(\"[3, 4, 5]\"),\n        \"toString() should be \\\"[3, 4, 5]\\\" after wrapping around but was \\\"\"\n            + queue.toString() + \"\\\"\");\n    queue.insert(6); // triggers a resize\n    check(queue.toString().equals(\"[3, 4, 5, 6]\"),\n        \"toString() should be \\\"[3, 4, 5, 6]\\\" after resizing but was \\\"\"\n            + queue.toString() + \"\\\"\");\n    queue.remove();\n    check(queue.toString().equals(\"[4, 5, 6]\"),\n        \"toString() should be \\\"[4, 5, 6]\\\" after one removal but was \\\"\"\n            + queue.toString() + \"\\\"\");\n  }\n\n  // stress the resize logic with many doublings\n  private static void testLargeVolume() {\n    Queue<Integer> queue = new Queue<Integer>(2);\n    int n = 5000;\n    for (int i = 0; i < n; i++) {\n      queue.insert(i);\n    }\n    check(queue.peekFront() == 0,\n        \"large volume test: peekFront() should return the first inserted item\");\n    check(queue.peekRear() == n - 1,\n        \"large volume test: peekRear() should return the last inserted item\");\n    for (int i = 0; i < n; i++) {\n      check(queue.remove() == i,\n          \"large volume test: remove() should return \" + i + \" in FIFO order\");\n    }\n    check(queue.remove() == null,\n        \"large volume test: remove() should return null when empty\");\n  }\n\n  // differential test: perform random insert/remove operations and\n  // compare the results against java.util.ArrayDeque after every step\n  private static void testRandomizedAgainstOracle() {\n    ArrayDeque<Integer> oracle = new ArrayDeque<Integer>();\n    Random random = new Random(2026);\n    Queue<Integer> queue = new Queue<Integer>(4);\n    // tracks the expected internal capacity, which doubles every time\n    // a resize is triggered (see CustomUtils.getNextResize)\n    int capacity = 4;\n    int nextValue = 0;\n    for (int step = 0; step < 500; step++) {\n      boolean insert = oracle.isEmpty() || random.nextInt(100) < 55;\n      if (insert) {\n        if (oracle.size() == capacity) {\n          capacity *= 2; // the resize happens before the insert\n        }\n        queue.insert(nextValue);\n        oracle.addLast(nextValue);\n        nextValue++;\n      } else {\n        Integer expected = oracle.removeFirst();\n        Integer actual = queue.remove();\n        check(actual != null && actual.equals(expected),\n            \"randomized test: remove() should return \" + expected\n                + \" but returned \" + actual);\n      }\n      Integer expectedFront = oracle.peekFirst();\n      Integer actualFront = queue.peekFront();\n      check(expectedFront == null ? actualFront == null\n          : expectedFront.equals(actualFront),\n          \"randomized test: peekFront() should return \" + expectedFront\n              + \" but returned \" + actualFront);\n      // peekRear() is only checked while the internal array is not\n      // exactly full, because peekRear() currently crashes in that\n      // state (see the tests at the bottom of this file)\n      if (oracle.size() < capacity) {\n        Integer expectedRear = oracle.peekLast();\n        Integer actualRear = queue.peekRear();\n        check(expectedRear == null ? actualRear == null\n            : expectedRear.equals(actualRear),\n            \"randomized test: peekRear() should return \" + expectedRear\n                + \" but returned \" + actualRear);\n      }\n    }\n    while (!oracle.isEmpty()) {\n      Integer expected = oracle.removeFirst();\n      Integer actual = queue.remove();\n      check(actual != null && actual.equals(expected),\n          \"randomized test: remove() should return \" + expected\n              + \" but returned \" + actual);\n    }\n    check(queue.remove() == null,\n        \"randomized test: remove() should return null when empty\");\n  }\n\n  // project requirement 1: the class must be generic\n  private static void testGenericWithStrings() {\n    Queue<String> queue = new Queue<String>(2);\n    queue.insert(\"a\");\n    queue.insert(\"b\");\n    queue.insert(\"c\"); // forces a resize\n    check(queue.peekFront().equals(\"a\"), \"peekFront() should return \\\"a\\\"\");\n    check(queue.peekRear().equals(\"c\"), \"peekRear() should return \\\"c\\\"\");\n    check(queue.remove().equals(\"a\"), \"remove() should return \\\"a\\\"\");\n    check(queue.remove().equals(\"b\"), \"remove() should return \\\"b\\\"\");\n    check(queue.remove().equals(\"c\"), \"remove() should return \\\"c\\\"\");\n    check(queue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  // 7.g: display() should run without errors\n  private static void testDisplay() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    queue.display();\n    queue.remove();\n    queue.display();\n  }\n\n  // peekRear() must work even when the queue is exactly at capacity\n  private static void testPeekRearWhenFull() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3); // queue is now exactly full and queueHead wraps to 0\n    check(queue.peekFront() == 1, \"peekFront() should return 1 on a full queue\");\n    check(queue.peekRear() == 3,\n        \"peekRear() should return the last inserted item when the queue is \"\n            + \"exactly full\");\n  }\n\n  // the same bug affects any state where queueHead has wrapped to 0,\n  // even after the queue is no longer full\n  private static void testPeekRearAfterPartialDrain() {\n    Queue<Integer> queue = new Queue<Integer>(3);\n    queue.insert(1);\n    queue.insert(2);\n    queue.insert(3); // queue is exactly full and queueHead wraps to 0\n    queue.remove(); // queueHead is still 0, but the queue is no longer full\n    check(queue.peekFront() == 2,\n        \"peekFront() should return 2 after partially draining a full queue\");\n    check(queue.peekRear() == 3,\n        \"peekRear() should work after partially draining a full queue\");\n  }\n}\n",
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/QueueTest.java"
}
```

Wrote file successfully: src/QueueTest.java

**Tool: write**

**Input:**
```json
{
  "content": "/**\n * Unit tests for PriorityQueue.java, based on the requirements in\n * assignment_details.pdf (section 8: PriorityQueue Class).\n *\n * Reminder from the assignment: a smaller priorityValue means higher\n * priority, and items must be removed based on priority.\n *\n * These tests use Java assert statements, so they must be run with\n * assertions enabled:\n *\n *   ./build && ./run_test PriorityQueueTest\n */\nimport java.util.Random;\nimport java.util.TreeMap;\n\nclass PriorityQueueTest {\n  private static int passed = 0;\n  private static int failed = 0;\n\n  private static void check(boolean condition, String message) {\n    assert condition : message;\n  }\n\n  private static void runTest(String name, Runnable test) {\n    try {\n      test.run();\n      passed++;\n      System.out.printf(\"PASS: %s%n\", name);\n    } catch (AssertionError | RuntimeException e) {\n      failed++;\n      System.out.printf(\"FAIL: %s -- %s%n\", name, e.getMessage());\n    }\n  }\n\n  public static void main(String[] args) {\n    boolean assertsEnabled = false;\n    assert assertsEnabled = true;\n    if (!assertsEnabled) {\n      System.out.println(\n          \"WARNING: assertions are disabled, every check will silently pass.\");\n      System.out.println(\"Run with: ./run_test PriorityQueueTest\");\n    }\n\n    runTest(\"empty priority queue returns null\",\n        PriorityQueueTest::testEmptyReturnsNull);\n    runTest(\"remove returns smallest priorityValue first\",\n        PriorityQueueTest::testRemoveSmallestPriorityFirst);\n    runTest(\"peekFront and peekRear\",\n        PriorityQueueTest::testPeekFrontAndRear);\n    runTest(\"peekFront and peekRear after removals\",\n        PriorityQueueTest::testPeekFrontRearAfterRemovals);\n    runTest(\"insertion order does not matter\",\n        PriorityQueueTest::testInsertionOrderDoesNotMatter);\n    runTest(\"ascending priority insertion\",\n        PriorityQueueTest::testAscendingPriorityInsertion);\n    runTest(\"descending priority insertion\",\n        PriorityQueueTest::testDescendingPriorityInsertion);\n    runTest(\"auto resize\", PriorityQueueTest::testAutoResize);\n    runTest(\"interleaved insert and remove\",\n        PriorityQueueTest::testInterleavedInsertRemove);\n    runTest(\"alternating insert and remove\",\n        PriorityQueueTest::testAlternatingInsertRemove);\n    runTest(\"single element\", PriorityQueueTest::testSingleElement);\n    runTest(\"duplicate priorities\", PriorityQueueTest::testDuplicatePriorities);\n    runTest(\"all identical priorities\", PriorityQueueTest::testAllSamePriority);\n    runTest(\"negative and zero priorities\",\n        PriorityQueueTest::testNegativeAndZeroPriorities);\n    runTest(\"many elements with multiple resizes\",\n        PriorityQueueTest::testManyElements);\n    runTest(\"large volume\", PriorityQueueTest::testLargeVolume);\n    runTest(\"reuse after emptying\", PriorityQueueTest::testReuseAfterEmptying);\n    runTest(\"toString\", PriorityQueueTest::testToString);\n    runTest(\"randomized test against a reference priority queue\",\n        PriorityQueueTest::testRandomizedAgainstOracle);\n    runTest(\"generic with String items\",\n        PriorityQueueTest::testGenericWithStrings);\n    runTest(\"display runs without errors\", PriorityQueueTest::testDisplay);\n\n    System.out.printf(\"%nPriorityQueueTest summary: %d passed, %d failed%n\",\n        passed, failed);\n    if (failed > 0) {\n      System.exit(1);\n    }\n  }\n\n  // 8.c/8.d/8.e: remove(), peekFront() and peekRear() should return\n  // null when the priority queue is empty\n  private static void testEmptyReturnsNull() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(5);\n    check(priorityQueue.remove() == null,\n        \"remove() should return null on an empty priority queue\");\n    check(priorityQueue.peekFront() == null,\n        \"peekFront() should return null on an empty priority queue\");\n    check(priorityQueue.peekRear() == null,\n        \"peekRear() should return null on an empty priority queue\");\n    check(priorityQueue.remove() == null,\n        \"remove() should still return null after repeated calls\");\n  }\n\n  // section 8: a smaller priorityValue means higher priority, so\n  // remove() must return the smallest priorityValue first\n  private static void testRemoveSmallestPriorityFirst() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(5);\n    priorityQueue.insert(\"A\", 5);\n    priorityQueue.insert(\"B\", 1);\n    priorityQueue.insert(\"C\", 3);\n    check(priorityQueue.remove().equals(\"B\"),\n        \"remove() should return the item with priorityValue 1 first\");\n    check(priorityQueue.remove().equals(\"C\"),\n        \"remove() should return the item with priorityValue 3 next\");\n    check(priorityQueue.remove().equals(\"A\"),\n        \"remove() should return the item with priorityValue 5 last\");\n    check(priorityQueue.remove() == null,\n        \"remove() should return null once the priority queue is empty\");\n  }\n\n  // 8.d/8.e: peekFront() is the next item to be removed (smallest\n  // priorityValue) and peekRear() is the last (largest priorityValue);\n  // peeking must not remove anything\n  private static void testPeekFrontAndRear() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(5);\n    priorityQueue.insert(\"low\", 10);\n    priorityQueue.insert(\"high\", 1);\n    priorityQueue.insert(\"mid\", 5);\n    check(priorityQueue.peekFront().equals(\"high\"),\n        \"peekFront() should return the smallest priorityValue item\");\n    check(priorityQueue.peekRear().equals(\"low\"),\n        \"peekRear() should return the largest priorityValue item\");\n    check(priorityQueue.peekFront().equals(\"high\"),\n        \"peekFront() should not remove the item\");\n    check(priorityQueue.peekRear().equals(\"low\"),\n        \"peekRear() should not remove the item\");\n    check(priorityQueue.remove().equals(\"high\"),\n        \"remove() should still return the highest priority item after peeking\");\n    check(priorityQueue.remove().equals(\"mid\"),\n        \"remove() should return the middle priority item next\");\n    check(priorityQueue.remove().equals(\"low\"),\n        \"remove() should return the lowest priority item last\");\n  }\n\n  // peekFront() and peekRear() must stay correct as items are removed\n  // and new ones are inserted\n  private static void testPeekFrontRearAfterRemovals() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(5);\n    priorityQueue.insert(30, 30);\n    priorityQueue.insert(10, 10);\n    priorityQueue.insert(20, 20);\n    priorityQueue.insert(40, 40);\n    priorityQueue.remove(); // removes 10\n    check(priorityQueue.peekFront() == 20,\n        \"peekFront() should return 20 after removing the smallest item\");\n    check(priorityQueue.peekRear() == 40,\n        \"peekRear() should still return 40\");\n    priorityQueue.remove(); // removes 20\n    check(priorityQueue.peekFront() == 30,\n        \"peekFront() should return 30 after removing 20\");\n    check(priorityQueue.peekRear() == 40,\n        \"peekRear() should still return 40\");\n    priorityQueue.insert(5, 5);\n    check(priorityQueue.peekFront() == 5,\n        \"peekFront() should return 5 after inserting a higher priority item\");\n    priorityQueue.insert(50, 50);\n    check(priorityQueue.peekRear() == 50,\n        \"peekRear() should return 50 after inserting a lower priority item\");\n    priorityQueue.remove(); // removes 5\n    priorityQueue.remove(); // removes 30\n    priorityQueue.remove(); // removes 40\n    check(priorityQueue.peekFront() == 50,\n        \"peekFront() should return 50 when only one item remains\");\n    check(priorityQueue.peekRear() == 50,\n        \"peekRear() should return 50 when only one item remains\");\n    priorityQueue.remove(); // removes 50\n    check(priorityQueue.peekFront() == null,\n        \"peekFront() should return null when empty\");\n    check(priorityQueue.peekRear() == null,\n        \"peekRear() should return null when empty\");\n  }\n\n  // removal order must depend on priority, not insertion order\n  private static void testInsertionOrderDoesNotMatter() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(10);\n    priorityQueue.insert(50, 5);\n    priorityQueue.insert(10, 1);\n    priorityQueue.insert(40, 4);\n    priorityQueue.insert(20, 2);\n    priorityQueue.insert(30, 3);\n    for (int expected = 10; expected <= 50; expected += 10) {\n      check(priorityQueue.remove() == expected,\n          \"remove() should return \" + expected\n              + \" based on priority, not insertion order\");\n    }\n  }\n\n  private static void testAscendingPriorityInsertion() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(5);\n    for (int i = 0; i < 10; i++) {\n      priorityQueue.insert(i, i); // already sorted by priority\n    }\n    for (int expected = 0; expected < 10; expected++) {\n      check(priorityQueue.remove() == expected,\n          \"ascending insertion test: remove() should return \" + expected);\n    }\n    check(priorityQueue.remove() == null,\n        \"ascending insertion test: remove() should return null when empty\");\n  }\n\n  private static void testDescendingPriorityInsertion() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(5);\n    for (int i = 9; i >= 0; i--) {\n      priorityQueue.insert(i, i); // inserted in reverse priority order\n    }\n    for (int expected = 0; expected < 10; expected++) {\n      check(priorityQueue.remove() == expected,\n          \"descending insertion test: remove() should return \" + expected);\n    }\n    check(priorityQueue.remove() == null,\n        \"descending insertion test: remove() should return null when empty\");\n  }\n\n  // project requirement 3: the internal arrays should resize\n  // automatically when they become full\n  private static void testAutoResize() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(2);\n    // item i gets priority 9 - i, so item 9 is the highest priority\n    for (int i = 0; i < 10; i++) {\n      priorityQueue.insert(i, 9 - i);\n    }\n    check(priorityQueue.peekFront() == 9,\n        \"peekFront() should return the smallest priorityValue item \"\n            + \"after resizing\");\n    check(priorityQueue.peekRear() == 0,\n        \"peekRear() should return the largest priorityValue item \"\n            + \"after resizing\");\n    for (int expected = 9; expected >= 0; expected--) {\n      check(priorityQueue.remove() == expected,\n          \"remove() should return items in ascending priority order after \"\n              + \"resizing (expected \" + expected + \")\");\n    }\n    check(priorityQueue.remove() == null,\n        \"remove() should return null after emptying a resized priority queue\");\n  }\n\n  private static void testInterleavedInsertRemove() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);\n    priorityQueue.insert(5, 5);\n    priorityQueue.insert(1, 1);\n    check(priorityQueue.remove() == 1,\n        \"remove() should return the item with priorityValue 1\");\n    priorityQueue.insert(0, 0);\n    priorityQueue.insert(9, 9);\n    check(priorityQueue.remove() == 0,\n        \"remove() should return the item with priorityValue 0\");\n    check(priorityQueue.remove() == 5,\n        \"remove() should return the item with priorityValue 5\");\n    check(priorityQueue.remove() == 9,\n        \"remove() should return the item with priorityValue 9\");\n    check(priorityQueue.remove() == null,\n        \"remove() should return null when empty\");\n  }\n\n  private static void testAlternatingInsertRemove() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);\n    priorityQueue.insert(10, 10);\n    priorityQueue.insert(2, 2);\n    check(priorityQueue.remove() == 2,\n        \"alternating test: remove() should return 2\");\n    priorityQueue.insert(1, 1);\n    check(priorityQueue.remove() == 1,\n        \"alternating test: remove() should return 1\");\n    check(priorityQueue.remove() == 10,\n        \"alternating test: remove() should return 10\");\n    check(priorityQueue.remove() == null,\n        \"alternating test: remove() should return null when empty\");\n    priorityQueue.insert(7, 7);\n    priorityQueue.insert(3, 3);\n    check(priorityQueue.remove() == 3,\n        \"alternating test: remove() should return 3\");\n    priorityQueue.insert(2, 2);\n    check(priorityQueue.remove() == 2,\n        \"alternating test: remove() should return 2\");\n    priorityQueue.insert(9, 9);\n    check(priorityQueue.remove() == 7,\n        \"alternating test: remove() should return 7\");\n    check(priorityQueue.remove() == 9,\n        \"alternating test: remove() should return 9\");\n    check(priorityQueue.remove() == null,\n        \"alternating test: remove() should return null when empty\");\n  }\n\n  private static void testSingleElement() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(2);\n    priorityQueue.insert(\"only\", 42);\n    check(priorityQueue.peekFront().equals(\"only\"),\n        \"peekFront() should return the only item\");\n    check(priorityQueue.peekRear().equals(\"only\"),\n        \"peekRear() should return the only item\");\n    check(priorityQueue.remove().equals(\"only\"),\n        \"remove() should return the only item\");\n    check(priorityQueue.remove() == null,\n        \"remove() should return null after removing the only item\");\n    check(priorityQueue.peekFront() == null,\n        \"peekFront() should return null when empty\");\n    check(priorityQueue.peekRear() == null,\n        \"peekRear() should return null when empty\");\n  }\n\n  // with duplicate priorityValues, any order among the duplicates is\n  // acceptable, but the overall removal order must be non-decreasing\n  // in priorityValue\n  private static void testDuplicatePriorities() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);\n    int[] priorities = {5, 3, 5, 1, 3, 1, 4};\n    for (int priority : priorities) {\n      priorityQueue.insert(priority, priority);\n    }\n    int removed = 0;\n    int previous = Integer.MIN_VALUE;\n    while (removed < priorities.length) {\n      Integer item = priorityQueue.remove();\n      check(item != null, \"remove() should return every inserted item\");\n      if (item == null) {\n        break;\n      }\n      check(item >= previous,\n          \"remove() should return items in non-decreasing priority order \"\n              + \"even with duplicate priorities (got \" + item\n              + \" after \" + previous + \")\");\n      previous = item;\n      removed++;\n    }\n    check(priorityQueue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  // when every item has the same priority, every item must still be\n  // returned exactly once, in any order\n  private static void testAllSamePriority() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);\n    int n = 10;\n    for (int i = 0; i < n; i++) {\n      priorityQueue.insert(i, 7);\n    }\n    boolean[] seen = new boolean[n];\n    int removed = 0;\n    while (removed < n) {\n      Integer item = priorityQueue.remove();\n      check(item != null,\n          \"all identical priorities test: remove() should return every \"\n              + \"inserted item\");\n      if (item == null) {\n        break;\n      }\n      check(item >= 0 && item < n,\n          \"all identical priorities test: remove() returned an unexpected \"\n              + \"item \" + item);\n      check(!seen[item],\n          \"all identical priorities test: remove() returned item \" + item\n              + \" more than once\");\n      seen[item] = true;\n      removed++;\n    }\n    check(priorityQueue.remove() == null,\n        \"all identical priorities test: remove() should return null when empty\");\n  }\n\n  private static void testNegativeAndZeroPriorities() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(5);\n    priorityQueue.insert(\"zero\", 0);\n    priorityQueue.insert(\"negative\", -5);\n    priorityQueue.insert(\"positive\", 7);\n    check(priorityQueue.remove().equals(\"negative\"),\n        \"remove() should handle negative priorityValues\");\n    check(priorityQueue.remove().equals(\"zero\"),\n        \"remove() should handle a priorityValue of 0\");\n    check(priorityQueue.remove().equals(\"positive\"),\n        \"remove() should return the largest priorityValue last\");\n  }\n\n  // inserting 25 items into an initial capacity of 3 forces several\n  // resizes (3 -> 6 -> 12 -> 24 -> 48)\n  private static void testManyElements() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);\n    int n = 25;\n    for (int i = 0; i < n; i++) {\n      priorityQueue.insert(i, n - 1 - i); // descending priority order\n    }\n    for (int expected = n - 1; expected >= 0; expected--) {\n      check(priorityQueue.remove() == expected,\n          \"remove() should return \" + expected + \" in ascending priority order\");\n    }\n    check(priorityQueue.remove() == null, \"remove() should return null when empty\");\n  }\n\n  // stress the resize logic and the heap invariant with many items and\n  // many resizes (3 -> 6 -> ... -> 1536)\n  private static void testLargeVolume() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(3);\n    int n = 1000;\n    int[] priorities = new int[n];\n    for (int i = 0; i < n; i++) {\n      priorities[i] = i;\n    }\n    // shuffle so that the insertion order is unrelated to the priority\n    Random random = new Random(42);\n    for (int i = n - 1; i > 0; i--) {\n      int j = random.nextInt(i + 1);\n      int temp = priorities[i];\n      priorities[i] = priorities[j];\n      priorities[j] = temp;\n    }\n    for (int i = 0; i < n; i++) {\n      priorityQueue.insert(priorities[i], priorities[i]);\n    }\n    for (int expected = 0; expected < n; expected++) {\n      check(priorityQueue.remove() == expected,\n          \"large volume test: remove() should return \" + expected\n              + \" in ascending priority order\");\n    }\n    check(priorityQueue.remove() == null,\n        \"large volume test: remove() should return null when empty\");\n  }\n\n  // the priority queue should behave correctly after being completely\n  // emptied\n  private static void testReuseAfterEmptying() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(2);\n    priorityQueue.insert(2, 2);\n    priorityQueue.insert(1, 1);\n    check(priorityQueue.remove() == 1, \"reuse test: remove() should return 1\");\n    check(priorityQueue.remove() == 2, \"reuse test: remove() should return 2\");\n    check(priorityQueue.remove() == null,\n        \"reuse test: remove() should return null when empty\");\n    priorityQueue.insert(5, 5);\n    priorityQueue.insert(3, 3);\n    check(priorityQueue.peekFront() == 3,\n        \"reuse test: peekFront() should return 3 after reuse\");\n    check(priorityQueue.remove() == 3, \"reuse test: remove() should return 3\");\n    check(priorityQueue.remove() == 5, \"reuse test: remove() should return 5\");\n    check(priorityQueue.remove() == null,\n        \"reuse test: remove() should return null when empty\");\n  }\n\n  // 8.f: toString should describe both heaps\n  private static void testToString() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);\n    priorityQueue.insert(7, 7);\n    String result = priorityQueue.toString();\n    check(result.contains(\"max heap:\"),\n        \"toString() should describe the max heap\");\n    check(result.contains(\"min heap:\"),\n        \"toString() should describe the min heap\");\n    check(result.contains(\"7\"),\n        \"toString() should contain the inserted item\");\n  }\n\n  // differential test: perform random insert/remove operations and\n  // compare the results against a TreeMap (which keeps the items\n  // sorted by priority) after every step. Priorities are unique, so\n  // the expected order is unambiguous.\n  private static void testRandomizedAgainstOracle() {\n    TreeMap<Integer, Integer> oracle = new TreeMap<Integer, Integer>();\n    Random random = new Random(2026);\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);\n    // a shuffled range of unique priorities\n    int[] priorities = new int[1000];\n    for (int i = 0; i < priorities.length; i++) {\n      priorities[i] = i;\n    }\n    for (int i = priorities.length - 1; i > 0; i--) {\n      int j = random.nextInt(i + 1);\n      int temp = priorities[i];\n      priorities[i] = priorities[j];\n      priorities[j] = temp;\n    }\n    int insertCount = 0;\n    int nextValue = 0;\n    for (int step = 0; step < 500; step++) {\n      boolean insert = oracle.isEmpty() || random.nextInt(100) < 55;\n      if (insert) {\n        int priority = priorities[insertCount];\n        insertCount++;\n        oracle.put(priority, nextValue);\n        priorityQueue.insert(nextValue, priority);\n        nextValue++;\n      } else {\n        Integer expected = oracle.pollFirstEntry().getValue();\n        Integer actual = priorityQueue.remove();\n        check(actual != null && actual.equals(expected),\n            \"randomized test: remove() should return \" + expected\n                + \" but returned \" + actual);\n      }\n      if (oracle.isEmpty()) {\n        check(priorityQueue.peekFront() == null,\n            \"randomized test: peekFront() should return null when empty\");\n        check(priorityQueue.peekRear() == null,\n            \"randomized test: peekRear() should return null when empty\");\n      } else {\n        Integer expectedFront = oracle.firstEntry().getValue();\n        Integer actualFront = priorityQueue.peekFront();\n        check(actualFront != null && actualFront.equals(expectedFront),\n            \"randomized test: peekFront() should return \" + expectedFront\n                + \" but returned \" + actualFront);\n        Integer expectedRear = oracle.lastEntry().getValue();\n        Integer actualRear = priorityQueue.peekRear();\n        check(actualRear != null && actualRear.equals(expectedRear),\n            \"randomized test: peekRear() should return \" + expectedRear\n                + \" but returned \" + actualRear);\n      }\n    }\n    while (!oracle.isEmpty()) {\n      Integer expected = oracle.pollFirstEntry().getValue();\n      Integer actual = priorityQueue.remove();\n      check(actual != null && actual.equals(expected),\n          \"randomized test: remove() should return \" + expected\n              + \" but returned \" + actual);\n    }\n    check(priorityQueue.remove() == null,\n        \"randomized test: remove() should return null when empty\");\n  }\n\n  // project requirement 1: the class must be generic\n  private static void testGenericWithStrings() {\n    PriorityQueue<String> priorityQueue = new PriorityQueue<String>(2);\n    priorityQueue.insert(\"pear\", 3);\n    priorityQueue.insert(\"apple\", 1);\n    priorityQueue.insert(\"banana\", 2); // forces a resize\n    check(priorityQueue.peekFront().equals(\"apple\"),\n        \"peekFront() should return \\\"apple\\\" (priorityValue 1)\");\n    check(priorityQueue.peekRear().equals(\"pear\"),\n        \"peekRear() should return \\\"pear\\\" (priorityValue 3)\");\n    check(priorityQueue.remove().equals(\"apple\"),\n        \"remove() should return \\\"apple\\\"\");\n    check(priorityQueue.remove().equals(\"banana\"),\n        \"remove() should return \\\"banana\\\"\");\n    check(priorityQueue.remove().equals(\"pear\"),\n        \"remove() should return \\\"pear\\\"\");\n    check(priorityQueue.remove() == null,\n        \"remove() should return null when empty\");\n  }\n\n  // 8.g: display() should run without errors\n  private static void testDisplay() {\n    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(4);\n    priorityQueue.insert(2, 2);\n    priorityQueue.insert(1, 1);\n    priorityQueue.display();\n    priorityQueue.remove();\n    priorityQueue.display();\n  }\n}\n",
  "path": "/home/k1mch1/Documents/assignment-2-stack-and-arrays/src/PriorityQueueTest.java"
}
```

Wrote file successfully: src/PriorityQueueTest.java

---

## Assistant

_Thinking:_

Now rebuild and run all three expanded suites.

Now let me rebuild and run the expanded suites:


**Tool: shell**

**Input:**
```json
{
  "command": "cd /home/k1mch1/Documents/assignment-2-stack-and-arrays && ./build && echo \"BUILD OK\" && ./run_test StackTest; echo \"StackTest exit=$?\""
}
```

BUILD OK
PASS: empty stack returns null on pop/peek
PASS: push then peek
PASS: LIFO order
PASS: interleaved push and pop
PASS: exact capacity boundary
PASS: auto resize
PASS: alternating push and pop
PASS: reuse after emptying
PASS: toString
PASS: toString down to empty
PASS: large volume
PASS: generic with String items
Current stack: [1, 2]
popIndex: 1
arr.length: 2
Current stack: [1]
popIndex: 0
arr.length: 2
PASS: display runs without errors

StackTest summary: 13 passed, 0 failed
StackTest exit=0

Command exited with code 0.

---

## Assistant

_Thinking:_

StackTest passes 13/13. Now Queue and PriorityQueue.

Stack passes 13/13. Now the other two:





**Tool: shell**

**Input:**
```json
{
  "command": "cd /home/k1mch1/Documents/assignment-2-stack-and-arrays && ./run_test QueueTest; echo \"QueueTest exit=$?\""
}
```

PASS: empty queue returns null on remove/peekFront/peekRear
PASS: FIFO order
PASS: peekFront and peekRear
PASS: single element
PASS: circular wrap-around
PASS: exact capacity boundary
PASS: auto resize
PASS: resize after wrap-around
PASS: alternating insert and remove
PASS: reuse after emptying
PASS: toString
PASS: toString after wrap-around
PASS: large volume
PASS: randomized test against a reference queue
PASS: generic with String items
Current Queue: [1, 2]
queueHead: 2
queueTail: 0
occupied: 2
Current Queue: [2]
queueHead: 2
queueTail: 1
occupied: 1
PASS: display runs without errors
PASS: peekRear when queue is exactly full
PASS: peekRear after partially draining a full queue

QueueTest summary: 18 passed, 0 failed
QueueTest exit=0

Command exited with code 0.

**Tool: shell**

**Input:**
```json
{
  "command": "cd /home/k1mch1/Documents/assignment-2-stack-and-arrays && ./run_test PriorityQueueTest; echo \"PriorityQueueTest exit=$?\""
}
```

PASS: empty priority queue returns null
PASS: remove returns smallest priorityValue first
PASS: peekFront and peekRear
PASS: peekFront and peekRear after removals
PASS: insertion order does not matter
PASS: ascending priority insertion
PASS: descending priority insertion
PASS: auto resize
PASS: interleaved insert and remove
PASS: alternating insert and remove
PASS: single element
PASS: duplicate priorities
PASS: all identical priorities
PASS: negative and zero priorities
PASS: many elements with multiple resizes
PASS: large volume
PASS: reuse after emptying
PASS: toString
PASS: randomized test against a reference priority queue
PASS: generic with String items
max heap: [Entry[value=2, priority=2, indexInOtherHeap=1], Entry[value=1, priority=1, indexInOtherHeap=0]]
min heap: [Entry[value=1, priority=1, indexInOtherHeap=1], Entry[value=2, priority=2, indexInOtherHeap=0]]
peekFront: 1
peekRear: 2
max heap: [Entry[value=2, priority=2, indexInOtherHeap=0]]
min heap: [Entry[value=2, priority=2, indexInOtherHeap=0]]
peekFront: 2
peekRear: 2
PASS: display runs without errors

PriorityQueueTest summary: 21 passed, 0 failed
PriorityQueueTest exit=0

Command exited with code 0.

---

## Assistant

_Thinking:_

Interesting! QueueTest now passes 18/18 — including the two peekRear tests that previously failed! Wait, what? Previously "peekRear when queue is exactly full" failed with "Index -1 out of bounds for length 3". Now it PASSES?

Hmm, that's suspicious. Did the code change? The user said don't modify their code, and I didn't. Let me check if Queue.java changed... Wait — maybe the user fixed the bug themselves between my runs? Let me check git status and the current content of Queue.java.

Actually, let me verify: git status / git diff to see if Queue.java was modified.

Let me check.

Interesting — QueueTest now passes 18/18, including the two `peekRear` tests that were failing before. Let me verify whether `Queue.java` changed since my last run:


**Tool: shell**

**Input:**
```json
{
  "command": "cd /home/k1mch1/Documents/assignment-2-stack-and-arrays && git status --short && git diff --stat && grep -n \"frontIndex\" src/Queue.java && git log --oneline -3"
}
```

 M out/DelimiterChecker.class
 M out/Queue.class
 M src/Queue.java
?? out/PriorityQueueTest.class
?? out/QueueTest.class
?? out/StackTest.class
?? src/PriorityQueueTest.java
?? src/QueueTest.java
?? src/StackTest.java
 out/DelimiterChecker.class | Bin 882 -> 882 bytes
 out/Queue.class            | Bin 2195 -> 2225 bytes
 src/Queue.java             |  12 +++++++-----
 3 files changed, 7 insertions(+), 5 deletions(-)
68:    int frontIndex = (queueHead - 1 + arr.length) % arr.length;
69:    return arr[frontIndex];
4285ec6 added time complexity information for delimiter checking
7376bd2 tested finished delimiter checker
ac0e566 finished string reverser

Command exited with code 0.

---

## Assistant

_Thinking:_

The user fixed the bug themselves between my runs — `src/Queue.java` line 68 now reads:

```java
int frontIndex = (queueHead - 1 + arr.length) % arr.length;
```

That's exactly the fix I suggested. So the peekRear bug is fixed in their code (they modified it, not me). All tests now pass:
- StackTest: 13/13
- QueueTest: 18/18
- PriorityQueueTest: 21/21

Total: 52 tests, 0 failures.

Let me give a final summary. I should mention:
1. All suites now pass — they fixed the peekRear bug themselves (the `(queueHead - 1 + arr.length) % arr.length` fix at Queue.java:68).
2. The expanded coverage: 52 test groups total, including randomized differential tests against ArrayDeque/TreeMap oracles, boundary tests, wrap-around tests, large-volume stress tests, etc.
3. How to run.
4. One observation: zero/negative initial capacity still crashes on first insert (Stack(0), Queue(0), PriorityQueue(0)) — out of spec but worth noting.

Let me double check that zero-capacity claim quickly before mentioning it... I reasoned earlier: Stack(0): push → popIndex(-1) == arr.length-1 (-1) → resize to 0 → popIndex=0 → arr[0] AIOOBE. Let me verify quickly with a tiny test rather than assert. Actually, I can just reason it's true, but let me verify empirically to be safe — quick shell with jshell or a temp test. jshell might not be available. I could write a quick temp file in /tmp and compile against out/. Let me do it quickly.

Actually, is it worth it? It's a minor observation. But I claimed nothing yet — I'd rather verify before claiming. Let me verify with a quick command.

**Tool: shell**

**Input:**
```json
{
  "command": "cd /home/k1mch1/Documents/assignment-2-stack-and-arrays && cat > /tmp/ZeroCapacityProbe.java <<'EOF'\nclass ZeroCapacityProbe {\n  public static void main(String[] args) {\n    try {\n      Stack<Integer> s = new Stack<Integer>(0);\n      s.push(1);\n      System.out.println(\"Stack(0): push OK\");\n    } catch (RuntimeException e) {\n      System.out.println(\"Stack(0): \" + e);\n    }\n    try {\n      Queue<Integer> q = new Queue<Integer>(0);\n      q.insert(1);\n      System.out.println(\"Queue(0): insert OK\");\n    } catch (RuntimeException e) {\n      System.out.println(\"Queue(0): \" + e);\n    }\n    try {\n      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(0);\n      pq.insert(1, 1);\n      System.out.println(\"PriorityQueue(0): insert OK\");\n    } catch (RuntimeException e) {\n      System.out.println(\"PriorityQueue(0): \" + e);\n    }\n  }\n}\nEOF\njavac -cp out -d /tmp /tmp/ZeroCapacityProbe.java && java -ea -cp \"out:/tmp\" ZeroCapacityProbe"
}
```

Stack(0): java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
Queue(0): java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
PriorityQueue(0): java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0

Command exited with code 0.

---

## Assistant

_Thinking:_

Confirmed: all three classes crash with ArrayIndexOutOfBoundsException when constructed with capacity 0 and then used. This is out of the assignment's spec (the assignment doesn't define behavior for arraySize <= 0), so I didn't add it as a failing test — just an observation.

Final summary:
- All 52 test groups pass.
- They fixed the peekRear bug themselves (Queue.java:68 now uses `(queueHead - 1 + arr.length) % arr.length`).
- Expanded coverage details.
- How to run.
- Observation about zero capacity.

All green. Final state:

**Results (52 test groups, 0 failures)**
- `StackTest`: **13/13 passed**
- `QueueTest`: **18/18 passed**
- `PriorityQueueTest`: **21/21 passed**

**What changed since the last run:** you fixed the `peekRear()` bug yourself — `src/Queue.java:68` now reads `(queueHead - 1 + arr.length) % arr.length`, so the two previously failing tests now pass. I didn't touch any of your code.

**Expanded edge-case coverage**
- **Boundaries:** exact-capacity insert/push (resize trigger at the precise moment the array fills), single-element structures, empty-after-drain
- **Circular behavior:** wrap-around of `queueHead`/`queueTail`, resize *while* wrapped, `toString` in wrapped states
- **Stress:** 5,000-item Stack/Queue, 1,000-item PriorityQueue (forcing resizes up to 1,536 capacity)
- **Priority semantics:** ascending/descending insertion, duplicate priorities (non-decreasing order check), all-identical priorities (every item returned exactly once), negative/zero priorities, insertion-order independence
- **Reuse:** refill after completely emptying
- **Randomized differential tests:** 500 random insert/remove operations per suite, checked step-by-step against `java.util.ArrayDeque` (Queue) and `java.util.TreeMap` (PriorityQueue) as reference implementations, with fixed seeds so failures are reproducible
- **Peek non-destructiveness** verified after every peek

**Run with:** `./build && ./run_test StackTest` (or `QueueTest` / `PriorityQueueTest`)

**One observation (out of spec, not tested):** `new Stack<>(0)`, `new Queue<>(0)`, and `new PriorityQueue<>(0)` all throw `ArrayIndexOutOfBoundsException` on the first insert — the resize heuristic (`2 * 0 = 0`) never grows. The assignment doesn't specify behavior for non-positive sizes, so I left it alone, but a guard in the constructor would fix it if you want it.

Your earlier sentence was still cut off ("…and make it such that it") — let me know what you wanted there and I'll adjust.
