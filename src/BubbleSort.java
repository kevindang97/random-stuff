import java.util.ArrayList;
import java.util.List;

public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

  @Override
  public List<T> sort(List<T> array) {
    array = new ArrayList<T>(array);

    boolean didASwap = false;
    do {
      didASwap = false;
      for (int i = 1; i < array.size(); i++) {
        if (array.get(i - 1).compareTo(array.get(i)) > 0) {
          swap(array, i - 1, i);
          didASwap = true;
        }
      }
    } while (didASwap);

    return array;
  }

}
