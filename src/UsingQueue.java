class UsingQueue {
  public static void main(String[] args) {
    // I'm just going to create a normal queue
    Queue<Integer> queue = new Queue<Integer>(5);
    queue.insert(1);
    queue.insert(2);
    queue.insert(3);
    queue.display();
    System.out.println(queue.remove());
    System.out.println(queue.remove());
    // System.out.println(queue.remove());
    // System.out.println(queue.remove());
    queue.insert(4);
    queue.insert(5);
    queue.insert(6);
    queue.insert(7);
    queue.display();
    System.out.println(queue.remove());
    queue.display();
  }
}
