import java.util.ArrayList;
import java.util.List;

public class InsertionSort<T extends Comparable<T>> extends Sort<T> {

  @Override
  public List<T> sort(List<T> array) {
    array = new ArrayList<T>(array);

    for (int i = 0; i < array.size(); i++) {
      for (int j = i; j > 0; j--) {
        if (array.get(j - 1).compareTo(array.get(j)) > 0) {
          swap(array, j - 1, j);
        }
      }
    }

    return array;
  }

}
