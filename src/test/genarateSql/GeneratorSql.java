package test.genarateSql;

import test.genarateSql.List.BookList;
import test.genarateSql.List.ClientList;

import java.io.IOException;
import java.io.PrintWriter;

public class GeneratorSql {

    public static void main(String args[]){

        BookList bookList = new BookList("src/test/genarateSql/sql/book.sql");
        ClientList clientList = new ClientList("src/test/genarateSql/sql/client.sql");
        JournalQuery journal = new JournalQuery(bookList,clientList);

        try(PrintWriter out = new PrintWriter( "src/test/genarateSql/sql/journal.sql" )  ){
            out.println(journal.getSqlQuery());
            System.out.println("Sql script successful generate");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
