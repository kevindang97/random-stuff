import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class SortTest {

  @Test
  void sortTest() {
    List<Sort<Integer>> allSorts = new ArrayList<Sort<Integer>>();
    allSorts.add(new SelectionSort<Integer>());
    allSorts.add(new BubbleSort<Integer>());
    allSorts.add(new InsertionSort<Integer>());
    allSorts.add(new QuickSort<Integer>());

    List<List<Integer>> allLists = Arrays.asList(Arrays.asList(4, 3, 5, 1, 2),
        Arrays.asList(5, -1, 0, 3, 2), Arrays.asList(0, 0, 0, 0, 1));

    for (Sort<Integer> s : allSorts) {
      for (List<Integer> l : allLists) {
        assertTrue(isSorted(s.sort(l)),
            "Failed at sort #" + allSorts.indexOf(s) + " with list #" + allLists.indexOf(l));
      }
    }
  }

  @Test
  void isSortedTest() {
    assertTrue(isSorted(Arrays.asList(1, 2, 3, 4, 5)));
    assertTrue(isSorted(Arrays.asList(-2, -1, 0, 1, 2)));
    assertTrue(isSorted(Arrays.asList(0, 0, 0, 0, 0)));
    assertFalse(isSorted(Arrays.asList(1, 2, 3, 5, 4)));
  }

  <T extends Comparable<T>> boolean isSorted(List<T> array) {
    for (int i = 1; i < array.size(); i++) {
      if (array.get(i - 1).compareTo(array.get(i)) > 0) {
        return false;
      }
    }
    return true;
  }

}
