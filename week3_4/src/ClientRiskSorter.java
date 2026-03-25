import java.util.*;

class Client {
    String id;
    int riskScore;
    double accountBalance;

    public Client(String id, int riskScore, double accountBalance) {
        this.id = id;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return id + "(" + riskScore + ")";
    }
}

public class ClientRiskSorter {

    // ---------------- Bubble Sort: Ascending riskScore ----------------
    public static void bubbleSortRiskAsc(Client[] clients) {
        int n = clients.length;
        int totalSwaps = 0;
        System.out.println("\nBubble Sort (Ascending Risk):");

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    // Swap
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swapped = true;
                    totalSwaps++;
                    System.out.println("Swap: " + clients[j] + " <-> " + clients[j + 1]);
                }
            }
            if (!swapped) break; // early termination
        }
        System.out.println("Sorted (asc): " + Arrays.toString(clients) + " // Total swaps: " + totalSwaps);
    }

    // ---------------- Insertion Sort: Descending riskScore + accountBalance ----------------
    public static void insertionSortRiskDesc(Client[] clients) {
        int n = clients.length;

        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;

            while (j >= 0 && (clients[j].riskScore < key.riskScore ||
                    (clients[j].riskScore == key.riskScore && clients[j].accountBalance < key.accountBalance))) {
                clients[j + 1] = clients[j]; // shift right
                j--;
            }
            clients[j + 1] = key; // insert key
        }
        System.out.println("\nInsertion Sort (Desc Risk + Account Balance): " + Arrays.toString(clients));
    }

    // ---------------- Identify Top N Highest-Risk Clients ----------------
    public static void topRisks(Client[] clients, int topN) {
        System.out.print("\nTop " + topN + " Highest Risk Clients: ");
        for (int i = 0; i < Math.min(topN, clients.length); i++) {
            System.out.print(clients[i] + (i < topN - 1 ? ", " : ""));
        }
        System.out.println();
    }

    // ---------------- Main Method for Demo ----------------
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000.0),
                new Client("clientA", 20, 10000.0),
                new Client("clientB", 50, 7000.0)
        };

        // Bubble Sort ascending by riskScore
        Client[] bubbleClients = Arrays.copyOf(clients, clients.length);
        bubbleSortRiskAsc(bubbleClients);

        // Insertion Sort descending by riskScore + accountBalance
        Client[] insertionClients = Arrays.copyOf(clients, clients.length);
        insertionSortRiskDesc(insertionClients);

        // Top 10 highest-risk clients (here only 3 for demo)
        topRisks(insertionClients, 3);
    }
}