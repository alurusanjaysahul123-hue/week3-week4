import java.util.*;

public class RiskThresholdLookup {

    // ---------------- Linear Search ----------------
    public static void linearSearch(int[] risks, int target) {
        int comparisons = 0;
        boolean found = false;
        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                System.out.println("Linear Search: threshold=" + target + " found at index " + i +
                        " (" + comparisons + " comparisons)");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Linear Search: threshold=" + target + " not found (" + comparisons + " comparisons)");
        }
    }

    // ---------------- Binary Search for Floor and Ceiling ----------------
    public static void binaryFloorCeiling(int[] sortedRisks, int target) {
        int low = 0, high = sortedRisks.length - 1;
        int comparisons = 0;
        int floor = Integer.MIN_VALUE;
        int ceiling = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (sortedRisks[mid] == target) {
                floor = ceiling = sortedRisks[mid];
                break;
            } else if (sortedRisks[mid] < target) {
                floor = sortedRisks[mid];
                low = mid + 1;
            } else {
                ceiling = sortedRisks[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary Search: floor=" + floor + ", ceiling=" + ceiling +
                " (" + comparisons + " comparisons)");
    }

    // ---------------- Main Method ----------------
    public static void main(String[] args) {
        int[] riskBands = {10, 25, 50, 100};
        int targetThreshold = 30;

        System.out.println("Sorted risk bands: " + Arrays.toString(riskBands));

        // Linear search
        linearSearch(riskBands, targetThreshold);

        // Binary search for floor/ceiling
        binaryFloorCeiling(riskBands, targetThreshold);
    }
}