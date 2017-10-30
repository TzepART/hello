package test.genarateSql;

import test.genarateSql.List.BookList;
import test.genarateSql.List.ClientList;

public class Journal {

    private BookList bookList;
    private ClientList clientList;

    public Journal(BookList bookList, ClientList clientList) {
        this.bookList = bookList;
        this.clientList = clientList;
    }

    public String getSqlQuery(){

        String query = "Hello! I have: "+bookList.getBooksList().size()+" books and "+clientList.getClientsList().size()+" clients";

        return query;
    }
}
