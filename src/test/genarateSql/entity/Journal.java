package test.genarateSql.entity;

public class Journal {

    private Integer id;

    private Integer book_id;

    private Integer client_id;

    private String ddate;

    private String date_return;

    private String date_return_real;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    public String getDate_return_real() {
        return date_return_real;
    }

    public void setDate_return_real(String date_return_real) {
        this.date_return_real = date_return_real;
    }

    public String getQuery(){
        return "INSERT INTO `library`.`journal` (`ddate`, `book_id`, `client_id`, `date_return`, `date_return_real`) VALUES ('"+this.getDdate()+"', '"+this.getBook_id()+"', '"+this.getClient_id()+"', '"+this.getDate_return()+"', '"+this.getDate_return_real()+"');";
    }
}
