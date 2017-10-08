package test.severalThreads;

class MyThread implements Runnable{

   public Thread thread;

    MyThread(String threadName){
        this.thread = new Thread(this, threadName);
        this.thread.start();
    }

    @Override
    public void run() {
        System.out.println(this.thread.getName() + " - run thread");
        try{
            for(int count=0; count < 10; count++){
                Thread.sleep(400);
                System.out.println("In "+this.thread.getName() + ", counter: "+count);
            }
        }catch (InterruptedException exc){
            System.out.println(this.thread.getName() + " - crash thread");
        }
        System.out.println(this.thread.getName() + " - end thread");
    }
}
