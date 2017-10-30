package test.genarateSql;

import test.genarateSql.List.BookList;
import test.genarateSql.List.ClientList;
import test.genarateSql.entity.Book;
import test.genarateSql.entity.Client;
import test.genarateSql.entity.Journal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JournalQuery {

    private BookList bookList;
    private ClientList clientList;
    private Random randomGenerator;
    private Integer countType1 = 40;
    private Integer countType2 = 30;
    private Integer countType3 = 20;
    private Integer countType4 = 20;


    public JournalQuery(BookList bookList, ClientList clientList) {
        this.bookList = bookList;
        this.clientList = clientList;
        randomGenerator = new Random();
    }

    /**
     * Должна быть реализована следующая логика
     * Tt - время когда взял
     * Trr - время когда вернул
     * Tmr - когда должен был вернуть
     * Trt - время - сейчас
     * Type1 - Взял и вернул вовремя Tt < Trr < Tmr
     * Type2 - Взял и вернул просроченно Tt < Tmr < Trr
     * Type3 - Взял и ещё не вернул, но успевает Tt < Trt < Tmr
     * Type4 - Взял и ещё не вернул и не успевает Tt  < Tmr < Trr
    * */
    public String getSqlQuery(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43;

        String query = "Hello! I have: "+bookList.getBooksList().size()+" books and "+clientList.getClientsList().size()+" clients";

        return query;
    }

    private Journal[] getJournalRowsByType(String type){
        Journal[] JournalRows = new Journal[this.countType1];

        for (int i=0; i<this.countType1; i++){
            Journal journal = new Journal();

            //get random book
            int indexBook = randomGenerator.nextInt(this.bookList.getBooksList().size());
            Book book = this.bookList.getBooksList().get(indexBook);

            //get random client
            int indexClient = randomGenerator.nextInt(this.clientList.getClientsList().size());
            Client client = this.clientList.getClientsList().get(indexClient);



            JournalRows[i] = journal;
        }

        return JournalRows;
    }

    private Date getPastDateByValue(long value, int countDays){
        return new Date(value-countDays*24*60*60*1000);
    }

    private Date getFutureDateByValue(long value, int countDays){
        return new Date(value+countDays*24*60*60*1000);
    }

    private Integer getLimitDaysByCategoryId(Integer id){
        Integer days = 0;
        switch (id) {
            case 1:
                days = 60;
                break;
            case 2:
                days = 21;
                break;
            case 3:
                days = 7;
                break;
        }
        return days;
    }

}
