import java.util.*;

public class AccountIDLookup {

    // ---------------- Linear Search ----------------
    public static int linearFirstOccurrence(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear First Occurrence: index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear First Occurrence: not found (" + comparisons + " comparisons)");
        return -1;
    }

    public static int linearLastOccurrence(String[] logs, String target) {
        int comparisons = 0;
        int lastIndex = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) lastIndex = i;
        }
        if (lastIndex != -1)
            System.out.println("Linear Last Occurrence: index " + lastIndex + " (" + comparisons + " comparisons)");
        else
            System.out.println("Linear Last Occurrence: not found (" + comparisons + " comparisons)");
        return lastIndex;
    }

    // ---------------- Binary Search ----------------
    public static void binarySearchCount(String[] sortedLogs, String target) {
        int low = 0, high = sortedLogs.length - 1;
        int comparisons = 0;
        int first = -1, last = -1;

        // Find first occurrence
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (sortedLogs[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else if (sortedLogs[mid].compareTo(target) > 0) {
                high = mid - 1;
            } else {
                first = mid;
                high = mid - 1; // continue search left
            }
        }

        // Find last occurrence
        low = 0; high = sortedLogs.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (sortedLogs[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else if (sortedLogs[mid].compareTo(target) > 0) {
                high = mid - 1;
            } else {
                last = mid;
                low = mid + 1; // continue search right
            }
        }

        if (first != -1 && last != -1) {
            System.out.println("Binary Search: first index=" + first + ", last index=" + last +
                    ", count=" + (last - first + 1) + ", comparisons=" + comparisons);
        } else {
            System.out.println("Binary Search: target not found (" + comparisons + " comparisons)");
        }
    }

    // ---------------- Main Method ----------------
    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};

        // Linear search
        linearFirstOccurrence(logs, "accB");
        linearLastOccurrence(logs, "accB");

        // Binary search requires sorted input
        String[] sortedLogs = Arrays.copyOf(logs, logs.length);
        Arrays.sort(sortedLogs); // ["accA", "accB", "accB", "accC"]
        System.out.println("Sorted logs: " + Arrays.toString(sortedLogs));

        binarySearchCount(sortedLogs, "accB");
    }
}