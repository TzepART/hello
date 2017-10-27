package task.task3;

class SecondThread extends Thread{

    private IncrementClass incrementObj;
    static Integer count = 5;

    public SecondThread(String name, IncrementClass incrementObj) {
        super(name);//set Name to thread
        this.incrementObj = incrementObj;
    }

    @Override
    public void run(){
        for (int i = 1; i <= count; i++) {
            System.out.print("Поток - "+this.getName()+"; ");
            incrementObj.inc100(i);
        }
    }
}
