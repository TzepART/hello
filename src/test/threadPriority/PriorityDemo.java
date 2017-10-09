package test.threadPriority;

public class PriorityDemo {

    public static void main(String args[]){
        Priority mt1 = new Priority("High Priority");
        Priority mt2 = new Priority("Low Priority");

        //set Priority
        mt1.thread.setPriority(Thread.NORM_PRIORITY+2);
        mt2.thread.setPriority(Thread.NORM_PRIORITY-2);

        //running threads
        mt1.thread.start();
        mt2.thread.start();

        try{
            mt1.thread.join();
            mt2.thread.join();
        }catch (InterruptedException exc){
            System.out.println("Interrupted main thread");
        }

        System.out.println("Counter thread High Priority "+mt1.count);
        System.out.println("Counter thread Low Priority "+mt2.count);

    }

}
