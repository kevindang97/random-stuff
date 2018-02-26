import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {

  /**
   * Mergesort from memory, probably very inefficient, but oh well
   */
  @Override
  public List<T> sort(List<T> array) {
    array = new ArrayList<T>(array);

    // partition the array into a bunch of arrays of size 1
    List<List<T>> listArrays = new ArrayList<List<T>>();
    for (T element : array) {
      List<T> temp = new ArrayList<T>();
      temp.add(element);
      listArrays.add(temp);
    }

    return mergesort(listArrays);
  }

  private List<T> mergesort(List<List<T>> listArrays) {
    if (listArrays.size() == 1) {
      return listArrays.get(0);
    }

    List<List<T>> newList = new ArrayList<List<T>>();
    for (int i = 0; i < listArrays.size() - 1; i += 2) {
      // combine adjacent sorted arrays
      List<T> array1 = listArrays.get(i);
      List<T> array2 = listArrays.get(i + 1);
      List<T> combined = new ArrayList<T>();
      int index1 = 0;
      int index2 = 0;
      while (index1 < array1.size() && index2 < array2.size()) {
        if (array1.get(index1).compareTo(array2.get(index2)) < 0) {
          combined.add(array1.get(index1));
          index1++;
        } else {
          combined.add(array2.get(index2));
          index2++;
        }
      }
      if (index1 < array1.size()) {
        combined.addAll(array1.subList(index1, array1.size()));
      }
      if (index2 < array2.size()) {
        combined.addAll(array2.subList(index2, array2.size()));
      }
      newList.add(combined);
    }
    return mergesort(newList);
  }

}
