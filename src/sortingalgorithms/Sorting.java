package sortingalgorithms;
import java.util.ArrayList;
import java.util.List;

public class Sorting {
    //no data fields

    //this class cannot be instantiated
    private Sorting() { }

    public static<T extends Comparable<T>> void bubbleSort(ArrayList<T> list) {
        boolean swapped = false;
            for (int i = 0; i < list.size() - 1; i++) {
                swapped = false;
                for (int j = 0; j < list.size() - 1; j++) {
                    if (list.get(j).compareTo(list.get(j + 1)) == 1) {
                        T temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                        swapped = true;
                    }
                }
                if (!(swapped)) {
                    break;
                }
            }
    }

    public static<T extends Comparable<T>> void selectionSort(ArrayList<T> list) {
        int min = 0;
        for (int i = 0; i < list.size(); i++) {
            min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(min)) == -1) {
                    min = j;
                }
            }
            T temp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, temp);
        }
    }

    public static<T extends Comparable<T>> void insertionSort(ArrayList<T> list) {
        int j = 0;
        for (int i = 1; i < list.size(); i++) {
            T temp = list.get(i);
            j = i;
            while (j > 0 && temp.compareTo(list.get(j - 1)) == - 1) {
                list.set(j, list.get(j - 1));
                j--;
            }
            list.set(j, temp);
        }
    }

    public static<T extends Comparable<T>> void mergeSort(ArrayList<T> list) {
        int mid = 0;
            if (list.size() > 1) {
                mid = (list.size() - 1) / 2;
                ArrayList<T> left = new ArrayList<>();
                for (int i = 0; i <= mid; i++) {
                    left.add(i, list.get(i));
                }
                mergeSort(left);

                ArrayList<T> right = new ArrayList<>();
                int i = 0;
                for (int j = mid + 1; j < list.size(); j++) {
                    right.add(i, list.get(j));
                    ++i;
                }
                mergeSort(right);

                merge(left, right, list);
            }
    }

    private static<T extends Comparable<T>> void merge(ArrayList<T> left, ArrayList<T> right, ArrayList<T> result) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) == -1) {
                result.set(k, left.get(i));
                i++;
            }
            else {
                result.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while(i < left.size()) {
            result.set(k, left.get(i));
            i++;
            k++;
        }

        while(j < right.size()) {
            result.set(k, right.get(j));
            j++;
            k++;
        }
    }

    public static<T extends Comparable<T>> void quickSort(ArrayList<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    public static<T extends Comparable<T>> void quickSort(ArrayList<T> list, int low, int high) {
            if (low < high) {
                int p = partition(list, low, high);
                quickSort(list, low, p - 1);
                quickSort(list, p + 1, high);
            }
    }

    private static<T extends Comparable<T>> int partition(ArrayList<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (list.get(j).compareTo(pivot) == - 1 || list.get(j).compareTo(pivot) == 0) {
                i = i + 1;
                T temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        T temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    public static void countingSort(ArrayList<Integer> list, int k) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> counts = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            result.add(i);
        }

        for (int i = 0; i < k; i++) {
            counts.add(0);
        }

        for (int i = 0; i < list.size(); i++) {
            counts.set(list.get(i), counts.get(list.get(i)) + 1);
        }


        for (int i = 1; i < k - 1; i++) {
            counts.set(i, counts.get(i) + counts.get(i - 1));
        }

        for (int i = list.size() - 1; i >= 0; i--) {
           result.set(counts.get(list.get(i)) - 1, list.get(i));
           counts.set(list.get(i), counts.get(list.get(i)) - 1);
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, result.get(i));
        }
    }




    public static void radixSort(ArrayList<Integer> list) {
        final int BASE = 10;
        int max = 0;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>(BASE);
        for (int i = 0; i < BASE; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        //get maximum value int the list
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }

        for (int i = 1; max / i != 0; i *= BASE) {
            for (int j = 0; j < list.size(); j++) {
                buckets.get(list.get(j) / i % BASE).add(list.get(j));
            }


            int index = 0;

            for (int k = 0; k < buckets.size(); k++) {
                for (int j = 0; j < buckets.get(k).size(); j++) {
                    list.set(index, buckets.get(k).get(j));
                    index++;
                }
            }

            for (int a = 0; a < buckets.size(); a++) {
                buckets.get(a).clear();
            }
        }
    }
}
