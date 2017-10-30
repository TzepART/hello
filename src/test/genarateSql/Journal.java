package test.genarateSql;

import test.genarateSql.List.BookList;
import test.genarateSql.List.ClientList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Journal {

    private BookList bookList;
    private ClientList clientList;

    public Journal(BookList bookList, ClientList clientList) {
        this.bookList = bookList;
        this.clientList = clientList;
    }

    /**
     * Должна быть реализована следующая логика
     * Tt - время когда взял
     * Trr - время когда вернул
     * Tmr - когда должен был вернуть
     * Trt - время - сейчас
     * Взял и вернул вовремя Tt < Trr < Tmr
     * Взял и вернул просроченно Tt < Tmr < Trr
     * Взял и ещё не вернул, но успевает Tt < Trt < Tmr
     * Взял и ещё не вернул и не успевает Tt  < Tmr < Trr
    * */
    public String getSqlQuery(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43;

        String query = "Hello! I have: "+bookList.getBooksList().size()+" books and "+clientList.getClientsList().size()+" clients";

        return query;
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
