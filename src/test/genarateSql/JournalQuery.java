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

    static final Integer MAX_DAYS_AGO = 80;
    private BookList bookList;
    private ClientList clientList;
    private Random randomGenerator;
    private DateFormat dateFormat;
    private Integer countType1 = 40;
    private Integer countType2 = 30;
    private Integer countType3 = 20;
    private Integer countType4 = 20;


    public JournalQuery(BookList bookList, ClientList clientList) {
        this.bookList = bookList;
        this.clientList = clientList;
        this.randomGenerator = new Random();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

        Journal[] JournalRows = this.getJournalRowsByType("type1");

        String query = "Hello! I have: "+bookList.getBooksList().size()+" books and "+clientList.getClientsList().size()+" clients";

        return query;
    }

    private Journal[] getJournalRowsByType(String type){
        Journal[] JournalRows = new Journal[this.countType1];

        for (int i=0; i<this.countType1; i++){
            Journal journal = new Journal();

            //get random book
            int indexBook = this.randomGenerator.nextInt(this.bookList.getBooksList().size());
            Book book = this.bookList.getBooksList().get(indexBook);

            //get random client
            int indexClient = this.randomGenerator.nextInt(this.clientList.getClientsList().size());
            Client client = this.clientList.getClientsList().get(indexClient);

            this.setDatesByType1(book,journal);

            JournalRows[i] = journal;
        }

        return JournalRows;
    }

    private void setDatesByType1(Book book, Journal journal){

        Date date = new Date();

        //Шаг 1 через сколько дней принес
        Integer usesDays = this.randomGenerator.nextInt(this.getLimitDaysByCategoryId(book.getType_id()));

        //Шаг 2 когда взял
        Integer countDaysAgoTake = this.randomGenerator.nextInt((MAX_DAYS_AGO - (MAX_DAYS_AGO - usesDays)) + 1) + (MAX_DAYS_AGO - usesDays);
        journal.setDdate(this.getPastDateByValue(date,countDaysAgoTake));

        //Шаг 3 когда отдал
        Integer countDaysAgoReturn = countDaysAgoTake - usesDays;
        journal.setDate_return_real(this.getPastDateByValue(date,countDaysAgoReturn));

        //Шаг 4 когда должен был отдать
        Integer countDaysAgoMustReturn = countDaysAgoTake - this.getLimitDaysByCategoryId(book.getType_id());

        if(countDaysAgoMustReturn < 0){
            journal.setDate_return(this.getPastDateByValue(date,countDaysAgoMustReturn));
        }else{
            journal.setDate_return(this.getFutureDateByValue(date,countDaysAgoMustReturn));
        }
    }

    private String getPastDateByValue(Date date, int countDays){
        Date newDate = new Date(date.getTime()-countDays*24*60*60*1000);
        return dateFormat.format(newDate);
    }

    private String getFutureDateByValue(Date date, int countDays){
        Date newDate = new Date(date.getTime()+countDays*24*60*60*1000);
        return dateFormat.format(newDate);
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
