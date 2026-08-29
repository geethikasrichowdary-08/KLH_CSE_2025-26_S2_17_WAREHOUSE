public class TextQuery {

    private final String query;
    private final String type;

    public TextQuery(String query, String type) {
        this.query = query;
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public String getType() {
        return type;
    }

    public void displayQuery() {
        System.out.println("Query Type : " + type);
        System.out.println("Query      : " + query);
    }
}