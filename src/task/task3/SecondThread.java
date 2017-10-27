package task.task3;

class SecondThread extends Thread{

    private IncrementClass incrementObj;
    static Integer count = 10;
    private Integer step = 10;

    SecondThread(String name, IncrementClass incrementObj) {
        super(name);//set Name to thread
        this.incrementObj = incrementObj;
    }

    @Override
    public void run(){
        for (int i = 1; i <= count; i++) {
            incrementObj.increment(i,step,this.getName());
        }
    }
}
