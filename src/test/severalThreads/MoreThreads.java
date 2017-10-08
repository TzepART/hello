package test.severalThreads;

public class MoreThreads {
    public static void main(String args[]){
        System.out.println("On main thread");

        MyThread mt1 = new MyThread("Child #1");
        MyThread mt2 = new MyThread("Child #2");
        MyThread mt3 = new MyThread("Child #3");

        do{
            for (int i=0; i<50; i++){
                System.out.print(".");
                try{
                    Thread.sleep(100);
                }catch (InterruptedException exc){
                    System.out.println("Crash main thread");
                }
            }
        }while(
                mt1.thread.isAlive() ||
                mt2.thread.isAlive() ||
                mt3.thread.isAlive()
        );

        System.out.println("Off main thread");
    }
}
