package test.threadExtend;

public class UseThreads {

    public static void main(String args[]){
        System.out.println("On main thread");


        //create thread by this object
        MyThread myThread = new MyThread("Child #1");

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
