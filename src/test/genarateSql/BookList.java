package test.genarateSql;

import java.io.*;
import java.util.ArrayList;


class BookList {

    private Integer count;
    private ArrayList<Book> books = new ArrayList<Book>();
    private String fileName = "src/test/genarateSql/book.sql";

    BookList() {

    }

    ArrayList<Book> getBooksList(){

        //read file into stream, try-with-resources
        try {
            File file = new File(this.fileName);
            BufferedReader b = new BufferedReader(new FileReader(file));

            String readLine = "";

            while ((readLine = b.readLine()) != null) {
               this.addBook(readLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.books;
    }

    private void addBook(String row){
        Book book = new Book();
        book.setName(row);
        this.books.add(book);
    }
}
