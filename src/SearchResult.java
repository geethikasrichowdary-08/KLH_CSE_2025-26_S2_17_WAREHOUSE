public class SearchResult {

    private final String document;
    private final String keyword;
    private final int position;

    public SearchResult(String document, String keyword, int position) {
        this.document = document;
        this.keyword = keyword;
        this.position = position;
    }

    public String getDocument() {
        return document;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getPosition() {
        return position;
    }

    public void display() {

        System.out.println("Keyword  : " + keyword);
        System.out.println("Position : " + position);
        System.out.println("Document : " + document);
    }
}