import java.util.Arrays;

public final class CustomUtils<T extends Comparable<T>> {
  public static <T> int getNextResize(T[] arr) {
    // specify the heuristics, which in our case, will just be doubling
    return 2 * arr.length;
  }

  public static <T> T[] resizeArray(T[] arr) {
    /*
     * if java was able to allow creation of arrays of generic types, then we could
     * do something like:
     *
     * T[] newArr = new T[getNextResize(arr)];
     *
     * for (int i = 0; i < arr.length; i++){
     * newArr[i] = arr[i];
     * }
     *
     * return newArr;
     *
     * The alternative to making something like this work is way too complicated and
     * hacky, so i'm just going to use Arrays.copyOf since it's more elegant. Note
     * that this will probably take O(n) time
     */
    return Arrays.copyOf(arr, getNextResize(arr));
  }
}
