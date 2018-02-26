import java.util.ArrayList;
import java.util.List;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

  /**
   * Quicksort using Lomuto partition scheme sourced from:
   * https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme
   */
  @Override
  public List<T> sort(List<T> array) {
    array = new ArrayList<T>(array);

    quicksort(array, 0, array.size() - 1);

    return array;
  }

  private void quicksort(List<T> array, int low, int high) {
    if (low < high) {
      int pivot = partition(array, low, high);
      quicksort(array, low, pivot - 1);
      quicksort(array, pivot + 1, high);
    }
  }

  private int partition(List<T> array, int low, int high) {
    T pivot = array.get(high);
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (array.get(j).compareTo(pivot) < 0) {
        i++;
        swap(array, i, j);
      }
    }
    if (array.get(high).compareTo(array.get(i + 1)) < 0) {
      swap(array, i + 1, high);
    }
    return i + 1;
  }

}
