class UsingQueue {
  public static void main(String[] args) {
    // I'm just going to create a normal queue
    Queue<Integer> queue = new Queue<Integer>(5);
    queue.insert(1);
    queue.insert(2);
    queue.insert(3);
    queue.display();
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    queue.insert(4);
    queue.insert(5);
    queue.insert(6);
    queue.insert(7);
    queue.display();
    System.out.printf("removing: %s\r\n", queue.remove());
    queue.display();
    queue.insert(8);
    queue.insert(9);
    queue.insert(10);
    queue.insert(11);
    queue.insert(12);
    queue.insert(13);
    queue.display();
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    System.out.printf("removing: %s\r\n", queue.remove());
    queue.display();
    queue.insert(12);
    queue.display();
  }
}
