package test.genarateSql;

public class Journal {

    private Book book;
    private Client client;
    private int count = 30;

    public Journal(Book book, Client client) {
        this.book = book;
        this.client = client;
    }

    public String getSqlQuery(){

        String query = "Hello! I have: "+book.getBookIdsArray(count).size()+" books and "+client.getClientIdsArray(count).size()+" clients";

        return query;
    }
}
