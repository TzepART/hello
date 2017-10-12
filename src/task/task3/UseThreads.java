package task.task3;



public class UseThreads {

    public static void main(String args[]){

        System.out.println("On main thread");
        IncrementClass tickTock = new IncrementClass();
        FirstTread mt1 = new FirstTread("Tick", tickTock);
        SecondThread mt2 = new SecondThread("Tock", tickTock);

        try {
            mt1.thread.join();
            mt2.join();
        }catch (InterruptedException exc){
            System.out.print("Interrupted thread");
        }
    }
}
