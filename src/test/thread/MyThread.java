package test.thread;

class MyThread implements Runnable{

    private String threadName;

    MyThread(String threadName){
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(this.threadName + " - run thread");
        try{
            for(int count=0; count < 10; count++){
                Thread.sleep(400);
                System.out.println("In "+this.threadName + ", counter: "+count);
            }
        }catch (InterruptedException exc){
            System.out.println(this.threadName + " - crash thread");
        }
        System.out.println(this.threadName + " - end thread");
    }
}
