/*
  The two algorithms used were sourced from Wikipedia:
  https://en.wikipedia.org/wiki/Maximum_subarray_problem
*/
public class MaximumSubArray {
  
  public static int KadanesAlgorithm(int[] array) {
    int maxSoFar = array[0];
    
    // loop for each element in the array
    // consider currentBestSubArray + array[i] or array[i] by itself
    // update currentBestSubArray with max
    // update maxSoFar if it's better
    int currentMax = array[0];
    for (int i = 1; i < array.length; i++) {
      if (currentMax + array[i] > array[i]) {
        currentMax += array[i];
      } else {
        currentMax = array[i];
      }

      if (currentMax > maxSoFar) {
        maxSoFar = currentMax;
      }
    }

    return maxSoFar;
  }

  public static int DivideAndConquer(int[] array) {
    // calculate cumulative sum array so that cumulative sum from [i, j], i < j = cumSum[j + 1] - cumSum[i]
    // the array will also begin with a zero, so that i = cumSum[i + 1] - cumSum[i]
    // (note: yes, it is an unfortunate name, I know...)
    int[] cumSum = cumSumArrayPrecedeZero(array);

    return DivideAndConquerLoop(0, array.length - 1, array, cumSum).sum;
  }

  public static SubArray DivideAndConquerLoop(int left, int right, int[] array, int[] cumSum) {
    if (left == right) {
      return new SubArray(array[left], cumSum[left], cumSum[left + 1]);
    } else {
      SubArray leftResult = DivideAndConquerLoop(left, (left + right) / 2, array, cumSum);
      SubArray rightResult = DivideAndConquerLoop((left + right) / 2 + 1, right, array, cumSum);

      int minPrefixSum = Math.min(leftResult.leftPrefixSum, rightResult.leftPrefixSum);
      int maxPrefixSum = Math.max(leftResult.rightPrefixSum, rightResult.rightPrefixSum);

      int midResult = rightResult.rightPrefixSum - leftResult.leftPrefixSum;
      int result = Math.max(Math.max(leftResult.sum, midResult), rightResult.sum);

      return new SubArray(result, minPrefixSum, maxPrefixSum);
    }
  }

  static class SubArray {

    public int sum;
    public int leftPrefixSum;
    public int rightPrefixSum;

    public SubArray(int a, int b, int c) {
      sum = a;
      leftPrefixSum = b;
      rightPrefixSum = c;
    }
  }

  public static int[] cumSumArrayPrecedeZero(int[] array) {
    int cumSum[] = new int[array.length + 1];
    cumSum[0] = 0;

    for (int i = 0; i < array.length; i++) {
      cumSum[i + 1] = cumSum[i] + array[i];
    }

    return cumSum;
  }

  public static void main(String[] args) {
    int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println("Expected: 6, Got: " + KadanesAlgorithm(test1));
    System.out.println("Expected: 6, Got: " + DivideAndConquer(test1));
    int[] test2 = {1, 2, 3, 4, 5};
    System.out.println("Expected: 15, Got: " + KadanesAlgorithm(test2));
    System.out.println("Expected: 15, Got: " + DivideAndConquer(test2));
    int[] test3 = {-1, -2, -3, -4, -5};
    System.out.println("Expected: -1, Got: " + KadanesAlgorithm(test3));
    System.out.println("Expected: -1, Got: " + DivideAndConquer(test3));
  }
}