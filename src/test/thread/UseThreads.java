package test.thread;

public class UseThreads {

    public static void main(String args[]){
        System.out.println("On main thread");

        //create object with type MyThread
        MyThread mt = new MyThread("Child #1");

        //create thread by this object
        Thread newThread = new Thread(mt);

        //begin 2nd thread
        newThread.start();

        for (int i=0; i<50; i++){
            System.out.print(".");
            try{
                Thread.sleep(100);
            }catch (InterruptedException exc){
                System.out.println("Crash main thread");
            }
        }

        System.out.println("Off main thread");
    }
}
