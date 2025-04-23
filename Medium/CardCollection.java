import java.util.*;

public class CardCollection {
    private static HashMap<String, List<String>> cardMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Card Collection System ===");

        // Sample cards
        addCard("Hearts", "Ace");
        addCard("Hearts", "2");
        addCard("Hearts", "Queen");
        addCard("Spades", "King");
        addCard("Diamonds", "10");
        addCard("Clubs", "Jack");
        addCard("Clubs", "3");

        int choice;
        do {
            System.out.println("\n1. Add a Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1: addCardInteractive(); break;
                case 2: searchBySymbol(); break;
                case 3: displayAllCards(); break;
                case 4: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static void addCard(String symbol, String value) {
        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(value);
    }

    private static void addCardInteractive() {
        System.out.print("Enter card symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter card value (e.g., Ace, 2, 3...): ");
        String value = scanner.nextLine();

        addCard(symbol, value);
        System.out.println("Card added successfully.");
    }

    private static void searchBySymbol() {
        System.out.print("Enter the symbol to search for: ");
        String symbol = scanner.nextLine();

        List<String> cards = cardMap.get(symbol);
        if (cards != null && !cards.isEmpty()) {
            System.out.println("Cards of " + symbol + ": " + cards);
        } else {
            System.out.println("No cards found for symbol: " + symbol);
        }
    }

    private static void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards in the system.");
            return;
        }

        System.out.println("\nAll cards in the collection:");
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
