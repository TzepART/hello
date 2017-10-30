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

        String query = "";


        return query;
    }
}
