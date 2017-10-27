package task.task3;

public class FirstTread implements Runnable {

    Thread thread;
    private IncrementClass incrementObj;
    static Integer count = 15;

    public FirstTread(String name, IncrementClass incrementObj) {
        this.thread = new Thread(this, name);
        this.incrementObj = incrementObj;
    }

    @Override
    public void run(){
        for (int i = 1; i <= count; i++) {
            System.out.print("Поток - "+this.thread.getName()+"; ");
            incrementObj.inc10(i);
        }
    }
}