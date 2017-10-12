package task.task3;

class SecondThread extends Thread{

    IncrementClass ttOb;

    public SecondThread(String name, IncrementClass tt) {
        this.ttOb = tt;
        this.start();
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
