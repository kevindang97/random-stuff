import java.util.*;
import java.lang.*;

public class TowerHanoi {

  // essentially a stack with a constraint:
  // won't let ints > peek() to be pushed
  // this stack pushes and pops ints
  class Tower {
    
    class Node {
      public int value;
      public Node next;

      public Node(int i) {
        value = i;
        next = null;
      }
    }

    Node head;

    public Tower() {
      head = null;
    }

    public boolean push(int i) {
      if (head == null) {
        head = new Node(i);
        return true;
      }
      if (i > peek()) {
        return false;
      }
      Node n = new Node(i);
      n.next = head;
      head = n;
      return true;
    }

    public int pop() {
      if (head == null) {
        return -1;
      } else {
        Node n = head;
        head = head.next;
        return n.value;
      }
    }

    public int peek() {
      if (head == null) {
        return -1;
      } else {
        return head.value;
      }
    }

    // this is only used for printing out the towers to console
    // I wasn't sure how else to do it
    public List<Integer> getListView() {
      List<Integer> list = new ArrayList<Integer>();

      Node n = head;

      while(n != null) {
        list.add(0, n.value);
        n = n.next;
      }

      return list;
    }
  }

  private Tower[] towers;
  private int size;

  public TowerHanoi(int paramSize) {
    towers = new Tower[3];
    towers[0] = new Tower();
    towers[1] = new Tower();
    towers[2] = new Tower();
    size = paramSize;

    for (int i = size; i > 0; i--) {
      towers[0].push(i);
    }

    // this gives the console adequate space for displaying the tower
    // makes sure the ANSI clear codes don't delete the previous console lines
    for (int i = 0; i < size + 2; i++) {
      System.out.println();
    }
  }

  public void move(int numberToMove, int from, int to) {
    // base case, moving a single disc
    if (numberToMove == 1) {
      int n = towers[from].pop();
      towers[to].push(n);
      try {
        System.in.read();
      } catch (Exception e) {
        e.printStackTrace();
      }
      print();
      return;
    }

    int otherTower = 3 - from - to;
    move(numberToMove - 1, from, otherTower);
    move(1, from, to);
    move(numberToMove - 1, otherTower, to);
  }

  public void print() {
    List<Integer> listOne = towers[0].getListView();
    List<Integer> listTwo = towers[1].getListView();
    List<Integer> listThree = towers[2].getListView();

    // use ANSI clear codes to clear the lines used to render the tower
    // this way the tower is rerendered in place rather than rendering 50 different times
    System.out.print("\033[" + (size  + 2) + "F\033[J");
    for (int i = size - 1; i >= 0; i--) {
      System.out.println("  " + (i >= listOne.size() ? " " : listOne.get(i))
        + "  " + (i >= listTwo.size() ? " " : listTwo.get(i))
        + "  " + (i >= listThree.size() ? " " : listThree.get(i)));
    }
    System.out.println("-----------");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("What size would you like your tower? ");
    int towerSize = scanner.nextInt();
    TowerHanoi tower = new TowerHanoi(towerSize);
    tower.print();
    tower.move(towerSize, 0, 2);
  }
}