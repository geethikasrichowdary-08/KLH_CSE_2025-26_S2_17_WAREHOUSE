import java.util.HashMap;
import java.util.Map;

public class CorpusStatistics {

    private final Corpus corpus;

    public CorpusStatistics(Corpus corpus) {
        this.corpus = corpus;
    }

    public int totalDocuments() {
        return corpus.size();
    }

    public int totalWords() {

        int total = 0;

        for (String document : corpus.getDocuments()) {

            if (document != null && !document.trim().isEmpty()) {

                String cleaned = document.trim();

                String[] words = cleaned.split("\\s+");

                total += words.length;
            }
        }

        return total;
    }

    public Map<String, Integer> getCorpusWordFrequency() {

        Map<String, Integer> frequency = new HashMap<>();

        for (String document : corpus.getDocuments()) {

            if (document == null) {
                continue;
            }

            String cleanedText = document.toLowerCase()
                    .replaceAll("[^a-z0-9 ]", " ");

            String[] words = cleanedText.split("\\s+");

            for (String word : words) {

                if (!word.isEmpty()) {

                    frequency.put(
                            word,
                            frequency.getOrDefault(word, 0) + 1
                    );
                }
            }
        }

        return frequency;
    }

    public void displayStatistics() {

        System.out.println("===== CORPUS STATISTICS =====");

        System.out.println(
                "Total documents : " + totalDocuments()
        );

        System.out.println(
                "Total words     : " + totalWords()
        );

        System.out.println("Unique words    : "
                + getCorpusWordFrequency().size());
    }
}