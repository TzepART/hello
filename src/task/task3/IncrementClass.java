package task.task3;

public class IncrementClass {

    private int value=0;

    synchronized public void inc10(Integer currentCount) {
        value = value+10;
        System.out.println("Значение переменной: " + value);
        notify();

        try {
            if(currentCount < SecondThread.count){
                wait();
            }
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted main thread");
        }
    }

    synchronized public void inc100(Integer currentCount) {

        value = value+100;
        System.out.println("Значение переменной: " + value);
        notify();
        try {
            if(currentCount < FirstTread.count){
                wait();
            }
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted main thread");
        }
    }

}
