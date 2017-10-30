package test.genarateSql;

import test.genarateSql.List.BookList;
import test.genarateSql.List.ClientList;

public class GeneratorSql {

    public static void main(String args[]){

        BookList bookList = new BookList("src/test/genarateSql/sql/book.sql");
        ClientList clientList = new ClientList("src/test/genarateSql/sql/client.sql");
        Journal journal = new Journal(bookList,clientList);

        System.out.println(journal.getSqlQuery());
    }

}
