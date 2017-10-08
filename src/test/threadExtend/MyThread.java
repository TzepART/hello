package test.threadExtend;

class MyThread extends Thread{


    MyThread(String threadName){
        super(threadName);//set Name to thread
        this.start();//begin thread
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " - run thread");
        try{
            for(int count=0; count < 10; count++){
                Thread.sleep(400);
                System.out.println("In "+this.getName() + ", counter: "+count);
            }
        }catch (InterruptedException exc){
            System.out.println(this.getName() + " - crash thread");
        }
        System.out.println(this.getName() + " - end thread");
    }
}
