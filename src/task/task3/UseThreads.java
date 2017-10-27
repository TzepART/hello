package task.task3;


public class UseThreads {

    public static void main(String args[]){

        IncrementClass incrementObj = new IncrementClass();
        FirstTread mt1 = new FirstTread("FirstTread", incrementObj, 12);
        SecondThread mt2 = new SecondThread("SecondThread", incrementObj, 10);

        mt2.start();
        mt1.thread.start();
    }
}
