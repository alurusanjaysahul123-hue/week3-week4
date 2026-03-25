import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // format "HH:mm"

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionFeeSorter {

    // ---------------- Bubble Sort for small batches (≤100) ----------------
    public static void bubbleSortFees(List<Transaction> list) {
        int n = list.size();
        int totalPasses = 0;
        int totalSwaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // Swap adjacent elements
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                    totalSwaps++;
                }
            }
            totalPasses++;
            if (!swapped) break; // early termination if no swaps
        }

        System.out.println("BubbleSort (fees): " + list + " // " + totalPasses + " passes, " + totalSwaps + " swaps");
    }

    // ---------------- Insertion Sort for medium batches (100–1000) ----------------
    public static void insertionSortFeeTimestamp(List<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Compare fee first; if equal, compare timestamp
            while (j >= 0 && (list.get(j).fee > key.fee ||
                    (list.get(j).fee == key.fee && list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j)); // shift right
                j--;
            }
            list.set(j + 1, key); // insert key
        }

        System.out.println("InsertionSort (fee+ts): " + list);
    }

    // ---------------- Flag high-fee outliers (> $50) ----------------
    public static void flagHighFeeOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50.0) {
                outliers.add(t);
            }
        }
        System.out.println("High-fee outliers: " + (outliers.isEmpty() ? "none" : outliers));
    }

    // ---------------- Main Method for Demo ----------------
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Bubble Sort example (fees only)
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        bubbleSortFees(bubbleList);

        // Insertion Sort example (fee + timestamp)
        List<Transaction> insertionList = new ArrayList<>(transactions);
        insertionSortFeeTimestamp(insertionList);

        // Flag high-fee outliers
        flagHighFeeOutliers(transactions);
    }
}