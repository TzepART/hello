package test.genarateSql;

public class GeneratorSql {

    public static void main(String args[]){

        Book book = new Book();
        Client client = new Client();
        Journal journal = new Journal(book,client);

        System.out.println(journal.getSqlQuery());
    }
}
