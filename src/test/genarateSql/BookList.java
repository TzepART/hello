package test.genarateSql;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;


class BookList extends BaseList{

    private ArrayList<Book> books = new ArrayList<Book>();

    BookList(String path) {
        super(path);
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
        Map<String, String> values = this.getValuesByQueryRow(row);

        for (Map.Entry<String, String> entry : values.entrySet())
        {
            switch (entry.getKey()) {
                case "type_id":
                    book.setType_id(Integer.parseInt(entry.getValue()));
                    break;
                case "name":
                    book.setName(entry.getValue());
                    break;
                case "cnt":
                    book.setCnt(Integer.parseInt(entry.getValue()));
                    break;
            }
        }

        this.books.add(book);
    }
}
