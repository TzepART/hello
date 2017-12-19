package task.task5.task3;


public class task3 {

    public static void main(String args[]){
        Integer countFirst = Integer.parseInt(args[0]);
        Integer countSecond = Integer.parseInt(args[1]);
        IncrementClass incrementObj = new IncrementClass();
        FirstTread mt1 = new FirstTread("FirstTread", incrementObj, countFirst);
        SecondThread mt2 = new SecondThread("SecondThread", incrementObj, countSecond);

        mt2.start();
        mt1.thread.start();
    }
}
