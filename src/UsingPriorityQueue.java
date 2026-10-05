class UsingPriorityQueue {
  public static void main(String[] args) {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(10);
    priorityQueue.insert(2, 2);
    priorityQueue.display();
    priorityQueue.insert(3, 3);
    priorityQueue.insert(10, 10);
    priorityQueue.insert(1, 1);
    priorityQueue.insert(4, 4);
    priorityQueue.insert(8, 8);
    priorityQueue.insert(1, 1);
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    priorityQueue.display();
    priorityQueue.insert(100, 100);
    priorityQueue.display();
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    priorityQueue.insert(100, 100);
    priorityQueue.insert(50, 50);
    priorityQueue.insert(25, 25);
    priorityQueue.insert(75, 75);
    priorityQueue.display();
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    priorityQueue.display();
  }
}
