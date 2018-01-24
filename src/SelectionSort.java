import java.util.ArrayList;
import java.util.List;

public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

  @Override
  public List<T> sort(List<T> array) {
    array = new ArrayList<T>(array);

    for (int i = 0; i < array.size(); i++) {
      int minIndex = i;
      for (int j = i; j < array.size(); j++) {
        if (array.get(j).compareTo(array.get(minIndex)) < 0) {
          minIndex = j;
        }
      }
      swap(array, i, minIndex);
    }

    return array;
  }

}
