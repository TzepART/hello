package test.threadWait;

public class MyThread implements Runnable {

    Thread thread;
    TickTock ttOb;

    public MyThread(String name, TickTock tt) {
        this.thread = new Thread(this, name);
        this.ttOb = tt;
        this.thread.start();
    }

    @Override
    public void run() {
        if (this.thread.getName().compareTo("Tick") == 0){
            for(int i=0; i<5; i++){
                ttOb.tick(true);
            }
            ttOb.tick(false);
        }else {
            for(int i=0; i<5; i++){
                ttOb.tock(true);
            }
            ttOb.tock(false);
        }
    }
}
