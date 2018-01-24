import java.util.List;

public abstract class Sort<T extends Comparable<T>> {

  abstract List<T> sort(List<T> array);

  void swap(List<T> array, int i, int j) {
    T temp = array.get(i);
    array.set(i, array.get(j));
    array.set(j, temp);
  }

}
