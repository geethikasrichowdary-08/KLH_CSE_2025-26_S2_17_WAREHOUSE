import java.util.ArrayList;
import java.util.List;

public class TextAnalyzer {

    private final Corpus corpus;
    private final KMP kmp;

    public TextAnalyzer(Corpus corpus) {
        this.corpus = corpus;
        this.kmp = new KMP();
    }

    // Search using KMP algorithm
    public List<String> search(String keyword) {

        List<String> results = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }

        for (String document : corpus.getDocuments()) {

            if (kmp.search(document, keyword)) {
                results.add(document);
            }
        }

        return results;
    }

    // Count occurrences of a pattern in one document
    public int countOccurrences(String text, String pattern) {

        if (text == null || pattern == null || pattern.isEmpty()) {
            return 0;
        }

        int count = 0;
        int position = 0;

        while (position <= text.length() - pattern.length()) {

            int found = kmp.findPosition(
                    text.substring(position),
                    pattern
            );

            if (found == -1) {
                break;
            }

            count++;

            position += found + pattern.length();
        }

        return count;
    }

    // Display search results
    public void displayResults(String keyword) {

        List<String> results = search(keyword);

        System.out.println("===== SEARCH RESULTS =====");
        System.out.println("Keyword: " + keyword);
        System.out.println("Matches found: " + results.size());

        for (int i = 0; i < results.size(); i++) {
            System.out.println();
            System.out.println("Result " + (i + 1) + ":");
            System.out.println(results.get(i));
        }
    }
}