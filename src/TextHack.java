import java.util.Scanner;

public class TextHack {

    public static void main(String[] args) {

        Corpus corpus = new Corpus();

    corpus.addDocument(
    "Text analytics is the process of extracting useful information from large collections of text. It helps systems identify patterns, keywords, and important information from documents."
);

corpus.addDocument(
    "Pattern searching is an important operation in text processing. Algorithms such as KMP can efficiently find a pattern inside a large text document."
);

corpus.addDocument(
    "A text search engine accepts a user query and searches the corpus for matching words or patterns. Efficient search algorithms improve the speed of retrieving relevant documents."
);

corpus.addDocument(
    "The KMP algorithm is a pattern matching algorithm that uses the Longest Prefix Suffix array to avoid unnecessary comparisons while searching for a pattern in text."
);
        TextAnalyzer analyzer = new TextAnalyzer(corpus);

        try (Scanner scanner = new Scanner(System.in)) {

        System.out.println("================================");
        System.out.println("        TEXT HACK SYSTEM         ");

        System.out.println("================================");

        System.out.println();
        System.out.println("Corpus documents: " + corpus.size());

        while (true) {

            System.out.println();
            System.out.println("1. Display Corpus");
            System.out.println("2. Search Text");
            System.out.println("3. Count Pattern");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" ->
                    corpus.displayCorpus();

                case "2" -> {
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine();

                    analyzer.displayResults(keyword);
                }

                case "3" -> {
                    System.out.print("Enter document text: ");
                    String text = scanner.nextLine();

                    System.out.print("Enter pattern: ");
                    String pattern = scanner.nextLine();

                    int count = analyzer.countOccurrences(text, pattern);

                    System.out.println(
                            "Pattern occurrences: " + count
                    );
                }

                case "4" -> {
                    System.out.println("Exiting TextHack...");
                    return;
                }

                default ->
                    System.out.println("Invalid choice.");
            }
        }

        }
    }
}