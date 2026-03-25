import java.util.*;

class Asset {
    String ticker;
    double returnRate;   // in percentage, e.g., 12.0
    double volatility;   // e.g., 0.2 for 20%

    public Asset(String ticker, double returnRate, double volatility) {
        this.ticker = ticker;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return ticker + ":" + returnRate + "%";
    }
}

public class PortfolioReturnSorter {

    // ---------------- Merge Sort: Ascending by returnRate ----------------
    public static Asset[] mergeSortReturn(Asset[] assets) {
        if (assets.length <= 1) return assets;

        int mid = assets.length / 2;
        Asset[] left = mergeSortReturn(Arrays.copyOfRange(assets, 0, mid));
        Asset[] right = mergeSortReturn(Arrays.copyOfRange(assets, mid, assets.length));

        return merge(left, right);
    }

    private static Asset[] merge(Asset[] left, Asset[] right) {
        Asset[] merged = new Asset[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].returnRate <= right[j].returnRate) { // stable merge
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }
        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];
        return merged;
    }

    // ---------------- Quick Sort: Descending returnRate + Ascending volatility ----------------
    public static void quickSortReturn(Asset[] assets, int low, int high) {
        if (low < high) {
            int pivotIndex = lomutoPartition(assets, low, high);
            quickSortReturn(assets, low, pivotIndex - 1);
            quickSortReturn(assets, pivotIndex + 1, high);
        }
    }

    private static int lomutoPartition(Asset[] assets, int low, int high) {
        Asset pivot = assets[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (assets[j].returnRate > pivot.returnRate ||
                    (assets[j].returnRate == pivot.returnRate && assets[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = assets[i];
                assets[i] = assets[j];
                assets[j] = temp;
            }
        }
        Asset temp = assets[i + 1];
        assets[i + 1] = assets[high];
        assets[high] = temp;
        return i + 1;
    }

    // ---------------- Main Method for Demo ----------------
    public static void main(String[] args) {
        Asset[] portfolio = {
                new Asset("AAPL", 12.0, 0.18),
                new Asset("TSLA", 8.0, 0.25),
                new Asset("GOOG", 15.0, 0.15)
        };

        // Merge Sort Ascending by returnRate
        Asset[] mergeSorted = mergeSortReturn(portfolio);
        System.out.println("MergeSort (asc returnRate): " + Arrays.toString(mergeSorted));

        // Quick Sort Descending by returnRate + volatility ASC
        Asset[] quickSorted = Arrays.copyOf(portfolio, portfolio.length);
        quickSortReturn(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort (desc returnRate + asc volatility): " + Arrays.toString(quickSorted));
    }
}