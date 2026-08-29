import java.util.ArrayList;
import java.util.List;

public class Corpus {

    private final List<String> documents;

    public Corpus() {
        documents = new ArrayList<>();
    }

    public void addDocument(String document) {
        if (document != null && !document.trim().isEmpty()) {
            documents.add(document);
        }
    }

    public List<String> getDocuments() {
        return documents;
    }

    public int size() {
        return documents.size();
    }

    public List<String> search(String keyword) {
        List<String> results = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }

        String searchText = keyword.toLowerCase();

        for (String document : documents) {
            if (document.toLowerCase().contains(searchText)) {
                results.add(document);
            }
        }

        return results;
    }

    public void displayCorpus() {
        if (documents.isEmpty()) {
            System.out.println("Corpus is empty.");
            return;
        }

        System.out.println("===== TEXT CORPUS =====");

        for (int i = 0; i < documents.size(); i++) {
            System.out.println("Document " + (i + 1) + ":");
            System.out.println(documents.get(i));
            System.out.println();
        }
    }
}