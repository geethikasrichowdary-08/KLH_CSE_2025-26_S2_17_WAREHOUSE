import java.util.ArrayList;
import java.util.List;

class PatternMatcher {

    private final KMP kmp;

    public PatternMatcher() {
        kmp = new KMP();
    }

    public List<Integer> findAll(String text, String pattern) {

        List<Integer> positions = new ArrayList<>();

        if (text == null || pattern == null || pattern.isEmpty()) {
            return positions;
        }

        int start = 0;

        while (start <= text.length() - pattern.length()) {

            int position = kmp.findPosition(
                    text.substring(start),
                    pattern
            );

            if (position == -1) {
                break;
            }

            int actualPosition = start + position;

            positions.add(actualPosition);

            start = actualPosition + pattern.length();
        }

        return positions;
    }

    public void displayMatches(String text, String pattern) {

        List<Integer> positions = findAll(text, pattern);

        System.out.println("Pattern: " + pattern);
        System.out.println("Number of matches: " + positions.size());

        if (positions.isEmpty()) {
            System.out.println("No matches found.");
        } else {

            System.out.print("Match positions: ");

            for (int position : positions) {
                System.out.print(position + " ");
            }

            System.out.println();
        }
    }
}