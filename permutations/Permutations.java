

public class Permutations {
  public static void main(String[] args) {
    printPermutations(args);
  }

  public static void printPermutations(String[] array) {
    printPermutationsRecur(array, new String[array.length], 0);
  }

  public static void printPermutationsRecur(String[] array, String[] current, int n) {
    // edge case, finished now so print
    if (n == current.length) {
      for (int i = 0; i < current.length; i++) {
        if (i != 0) {
          System.out.print(" ");
        }
        System.out.print(current[i]);
      }
      System.out.println();
    }

    for (int i = 0; i < array.length; i++) {
      // determine if array[i] has been used in the current array yet
      boolean used = false;
      for (int j = 0; j < n; j++) {
        if (current[j] == array[i]) {
          used = true;
          break;
        }
      }

      if (used) {
        continue;
      } else {
        current[n] = array[i];
        printPermutationsRecur(array, current, n + 1);
      }
    }
  }
}