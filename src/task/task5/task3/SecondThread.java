package task.task5.task3;

class SecondThread extends Thread{

    private IncrementClass incrementObj;
    private Integer count;
    private Integer step = 10;

    SecondThread(String name, IncrementClass incrementObj, Integer cicles) {
        super(name);//set Name to thread
        this.count = cicles;
        this.incrementObj = incrementObj;
    }

    @Override
    public void run(){
        for (int i = 1; i <= count; i++) {
            if(i == count){
                incrementObj.setWait(false);
            }
            incrementObj.increment(step,this.getName());
        }
    }
}
