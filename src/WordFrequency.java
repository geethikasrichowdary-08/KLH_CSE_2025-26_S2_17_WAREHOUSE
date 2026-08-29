import java.util.HashMap;
import java.util.Map;

public class WordFrequency {

    public Map<String, Integer> countWords(String text) {

        Map<String, Integer> frequency = new HashMap<>();

        if (text == null || text.trim().isEmpty()) {
            return frequency;
        }

        String cleanedText = text.toLowerCase()
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

        return frequency;
    }

    public void displayFrequency(String text) {

        Map<String, Integer> frequency = countWords(text);

        System.out.println("===== WORD FREQUENCY =====");

        if (frequency.isEmpty()) {
            System.out.println("No words found.");
            return;
        }

        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {

            System.out.println(
                    entry.getKey() + " : " + entry.getValue()
            );
        }
    }
}