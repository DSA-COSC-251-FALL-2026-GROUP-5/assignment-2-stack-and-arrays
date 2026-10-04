class UsingPriorityQueue {
  public static void main(String[] args) {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(10);
    priorityQueue.insert(1, 2);
    priorityQueue.display();
    priorityQueue.insert(1, 3);
    priorityQueue.insert(1, 10);
    priorityQueue.insert(1, 1);
    priorityQueue.insert(1, 4);
    priorityQueue.insert(1, 8);
    priorityQueue.insert(2, 1);
    priorityQueue.display();
    System.out.println(priorityQueue.remove());
    System.out.println(priorityQueue.remove());
    priorityQueue.display();
  }
}
