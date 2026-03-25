import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeVolumeAnalysis {

    // ---------------- Merge Sort: Ascending ----------------
    public static Trade[] mergeSort(Trade[] trades) {
        if (trades.length <= 1) return trades;

        int mid = trades.length / 2;
        Trade[] left = mergeSort(Arrays.copyOfRange(trades, 0, mid));
        Trade[] right = mergeSort(Arrays.copyOfRange(trades, mid, trades.length));

        return merge(left, right);
    }

    private static Trade[] merge(Trade[] left, Trade[] right) {
        Trade[] merged = new Trade[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].volume <= right[j].volume) { // Stable merge
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }
        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];

        return merged;
    }

    // ---------------- Quick Sort: Descending ----------------
    public static void quickSortDesc(Trade[] trades, int low, int high) {
        if (low < high) {
            int pivotIndex = lomutoPartition(trades, low, high);
            quickSortDesc(trades, low, pivotIndex - 1);
            quickSortDesc(trades, pivotIndex + 1, high);
        }
    }

    private static int lomutoPartition(Trade[] trades, int low, int high) {
        Trade pivot = trades[high]; // last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (trades[j].volume >= pivot.volume) { // descending
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    // ---------------- Merge Two Sorted Trade Arrays (ascending) ----------------
    public static Trade[] mergeTwoSorted(Trade[] arr1, Trade[] arr2) {
        int n = arr1.length, m = arr2.length;
        Trade[] merged = new Trade[n + m];
        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (arr1[i].volume <= arr2[j].volume) merged[k++] = arr1[i++];
            else merged[k++] = arr2[j++];
        }
        while (i < n) merged[k++] = arr1[i++];
        while (j < m) merged[k++] = arr2[j++];
        return merged;
    }

    // ---------------- Compute total volume ----------------
    public static int totalVolume(Trade[] trades) {
        int total = 0;
        for (Trade t : trades) total += t.volume;
        return total;
    }

    // ---------------- Main Method ----------------
    public static void main(String[] args) {
        Trade[] morningTrades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Merge Sort Ascending
        Trade[] mergeSorted = mergeSort(morningTrades);
        System.out.println("MergeSort (asc): " + Arrays.toString(mergeSorted));

        // Quick Sort Descending
        Trade[] quickSorted = Arrays.copyOf(morningTrades, morningTrades.length);
        quickSortDesc(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickSorted));

        // Simulate afternoon trades and merge
        Trade[] afternoonTrades = {
                new Trade("trade4", 200),
                new Trade("trade5", 400)
        };
        Trade[] mergedTrades = mergeTwoSorted(mergeSorted, mergeSort(afternoonTrades));
        System.out.println("Merged morning+afternoon trades: " + Arrays.toString(mergedTrades));
        System.out.println("Total volume post-merge: " + totalVolume(mergedTrades));
    }
}