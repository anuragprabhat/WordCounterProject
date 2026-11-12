import java.util.Scanner;

/**
 * ============================================================
 *  Main.java : this is the file you RUN.
 *  It shows the menu in the terminal and uses the WordCounter object
 *  (WordCounter.java must be in the same folder).
 * ============================================================
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // creating an object of the class
        WordCounter counter = new WordCounter("");
        boolean textEntered = false;
        int choice;

        System.out.println("=====================================");
        System.out.println("   WORD COUNTER  +  SMART INSIGHTS   ");
        System.out.println("=====================================");

        do {
            System.out.println("\n1. Enter / change text");
            System.out.println("2. Show basic report");
            System.out.println("3. Search a word");
            System.out.println("4. Smart Insights   <<< INNOVATION (extra feature)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();
            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                choice = 0; // invalid choice
            }

            switch (choice) {
                case 1:
                    System.out.println("Type your text. When finished, type END on a new line:");
                    String allText = "";
                    String line = sc.nextLine();
                    while (!line.equals("END")) {
                        allText = allText + line + "\n";
                        line = sc.nextLine();
                    }
                    counter.setText(allText);
                    textEntered = true;
                    System.out.println("Text saved!");
                    break;

                case 2:
                    if (textEntered) {
                        counter.displayBasicReport();
                    } else {
                        System.out.println("Please enter some text first (option 1).");
                    }
                    break;

                case 3:
                    if (textEntered) {
                        System.out.print("Enter the word to search: ");
                        String word = sc.nextLine().trim();
                        int times = counter.countOccurrences(word);
                        System.out.println("The word \"" + word + "\" appears " + times + " time(s).");
                    } else {
                        System.out.println("Please enter some text first (option 1).");
                    }
                    break;

                case 4:
                    if (textEntered) {
                        counter.showSmartInsights(); // INNOVATION
                    } else {
                        System.out.println("Please enter some text first (option 1).");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using Word Counter. Bye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter 1-5.");
            }

        } while (choice != 5);

        sc.close();
    }
}