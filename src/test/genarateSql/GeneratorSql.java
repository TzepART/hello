package test.genarateSql;

public class GeneratorSql {

    public static void main(String args[]){

        BookList bookList = new BookList();
        ClientList clientList = new ClientList();
        Journal journal = new Journal(bookList,clientList);

        System.out.println(journal.getSqlQuery());
    }
}
