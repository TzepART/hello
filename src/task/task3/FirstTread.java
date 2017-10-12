package task.task3;

public class FirstTread implements Runnable {

    Thread thread;
    IncrementClass ttOb;

    public FirstTread(String name, IncrementClass tt) {
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