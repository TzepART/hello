package test.genarateSql;

import test.genarateSql.List.BookList;
import test.genarateSql.List.ClientList;
import test.genarateSql.entity.Book;
import test.genarateSql.entity.Client;
import test.genarateSql.entity.Journal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class JournalQuery {

    static final Integer MAX_DAYS_AGO = 80;

    private BookList bookList;
    private ClientList clientList;
    private ArrayList<Journal> journalRows = new ArrayList<Journal>();


    private Random randomGenerator;
    private DateFormat dateFormat;
    private Integer countRowsType = 70;


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
    String getSqlQuery(){

        this.setJournalRowsByType("type1");
        this.setJournalRowsByType("type2");
        this.setJournalRowsByType("type3");
        this.setJournalRowsByType("type4");

        String query = "";

        for (Journal journal: this.journalRows) {
            query += journal.getQuery()+"\n";
        }

        return query;
    }

    private void setJournalRowsByType(String type){

        for (int i=0; i<this.countRowsType; i++){
            Journal journal = new Journal();

            //get random book
            int indexBook = this.randomGenerator.nextInt(this.bookList.getBooksList().size());
            Book book = this.bookList.getBooksList().get(indexBook);

            //get random client
            int indexClient = this.randomGenerator.nextInt(this.clientList.getClientsList().size());
            Client client = this.clientList.getClientsList().get(indexClient);

            switch (type){
                case "type1":
                    this.setDatesByType1(book,journal);
                    break;
                case "type2":
                    this.setDatesByType2(book,journal);
                    break;
                case "type3":
                    this.setDatesByType3(book,journal);
                    break;
                case "type4":
                    this.setDatesByType4(book,journal);
                    break;
            }

            journal.setBook_id(book.getId());
            journal.setClient_id(client.getId());

            this.journalRows.add(journal);
        }
    }

    private void setDatesByType1(Book book, Journal journal){

        Date date = new Date();
        Integer limitDays = this.getLimitDaysByCategoryId(book.getType_id());

        //Шаг 1 через сколько дней принес
        Integer usesDays = this.getRandom(0,limitDays);

        //Шаг 2 когда взял
        Integer countDaysAgoTake = this.getRandom(MAX_DAYS_AGO - usesDays, MAX_DAYS_AGO);
        journal.setDdate(this.getPastDateByValue(date,countDaysAgoTake));

        //Шаг 3 когда отдал
        Integer countDaysAgoReturn = countDaysAgoTake - usesDays;
        journal.setDate_return_real(this.getPastDateByValue(date,countDaysAgoReturn));

        //Шаг 4 когда должен был отдать
        Integer countDaysAgoMustReturn = countDaysAgoTake - limitDays;

        if(countDaysAgoMustReturn > 0){
            journal.setDate_return(this.getPastDateByValue(date,countDaysAgoMustReturn));
        }else{
            journal.setDate_return(this.getFutureDateByValue(date,countDaysAgoMustReturn));
        }
    }

    private void setDatesByType2(Book book, Journal journal){

        Date date = new Date();
        Integer limitDays = this.getLimitDaysByCategoryId(book.getType_id());

        //Шаг 1 через сколько дней принес
        Integer usesDays = this.getRandom(limitDays,MAX_DAYS_AGO);

        //Шаг 2 когда взял
        Integer countDaysAgoTake = this.getRandom(usesDays,MAX_DAYS_AGO);
        journal.setDdate(this.getPastDateByValue(date,countDaysAgoTake));

        //Шаг 3 когда отдал
        Integer countDaysAgoReturn = countDaysAgoTake - usesDays;
        journal.setDate_return_real(this.getPastDateByValue(date,countDaysAgoReturn));

        //Шаг 4 когда должен был отдать
        Integer countDaysAgoMustReturn = countDaysAgoTake - limitDays;

        if(countDaysAgoMustReturn > 0){
            journal.setDate_return(this.getPastDateByValue(date,countDaysAgoMustReturn));
        }else{
            journal.setDate_return(this.getFutureDateByValue(date,countDaysAgoMustReturn));
        }
    }

    private void setDatesByType3(Book book, Journal journal){

        Date date = new Date();
        Integer limitDays = this.getLimitDaysByCategoryId(book.getType_id());

        //Шаг 2 когда взял
        Integer countDaysAgoTake = this.getRandom(0, limitDays);
        journal.setDdate(this.getPastDateByValue(date,countDaysAgoTake));

        //Шаг 3 когда отдал
        journal.setDate_return_real("");

        //Шаг 4 когда должен отдать
        Integer countDaysAgoMustReturn = countDaysAgoTake - limitDays;

        if(countDaysAgoMustReturn < 0){
            journal.setDate_return(this.getPastDateByValue(date,countDaysAgoMustReturn));
        }else{
            journal.setDate_return(this.getFutureDateByValue(date,countDaysAgoMustReturn));
        }
    }

    private void setDatesByType4(Book book, Journal journal){

        Date date = new Date();
        Integer limitDays = this.getLimitDaysByCategoryId(book.getType_id());

        //Шаг 2 когда взял
        Integer countDaysAgoTake = this.getRandom(limitDays, MAX_DAYS_AGO);

        journal.setDdate(this.getPastDateByValue(date,countDaysAgoTake));

        //Шаг 3 когда отдал
        journal.setDate_return_real("");

        //Шаг 4 когда должен отдать
        Integer countDaysAgoMustReturn = countDaysAgoTake - limitDays;

        if(countDaysAgoMustReturn > 0){
            journal.setDate_return(this.getPastDateByValue(date,countDaysAgoMustReturn));
        }else{
            journal.setDate_return(this.getFutureDateByValue(date,countDaysAgoMustReturn));
        }
    }

    private Integer getRandom(Integer min, Integer max){
        return this.randomGenerator.nextInt((max - min) + 1) + min;
    }

    private String getPastDateByValue(Date date, int countDays){
        long hours = (long) countDays*24;
        long minutes = hours*60;
        long seconds = minutes*60;
        long miliseconds = seconds*1000;
        Date newDate = new Date(date.getTime() - miliseconds);
        return dateFormat.format(newDate);
    }

    private String getFutureDateByValue(Date date, int countDays){
        long hours = (long) countDays*24;
        long minutes = hours*60;
        long seconds = minutes*60;
        long miliseconds = seconds*1000;
        Date newDate = new Date(date.getTime() + miliseconds);
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
