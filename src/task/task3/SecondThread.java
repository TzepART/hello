package task.task3;

class SecondThread extends Thread{

    private IncrementClass incrementObj;
    static Integer count = 10;

    public SecondThread(String name, IncrementClass incrementObj) {
        this.incrementObj = incrementObj;
    }

    @Override
    public void run(){
        for (int i = 1; i <= count; i++) {
            incrementObj.inc100(i);
        }
    }
}
