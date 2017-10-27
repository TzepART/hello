package task.task3;

public class FirstTread implements Runnable {

    Thread thread;
    private IncrementClass incrementObj;
    private Integer count;
    private Integer step = 1000;


    FirstTread(String name, IncrementClass incrementObj, Integer cicles) {
        this.thread = new Thread(this, name);
        this.count = cicles;
        this.incrementObj = incrementObj;
    }

    @Override
    public void run(){
        for (int i = 1; i <= count; i++) {
            if(i == count){
                incrementObj.setWait(false);
            }
            incrementObj.increment(step,this.thread.getName());
        }
    }
}