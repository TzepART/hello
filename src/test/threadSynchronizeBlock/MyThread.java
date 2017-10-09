package test.threadSynchronizeBlock;

public class MyThread implements Runnable{

    Thread thread;
    static SummArray sa = new SummArray();
    int a[];
    int answer;

    public MyThread(String name, int nums[]) {
        this.thread = new Thread(this, name);
        this.a = nums;
        this.thread.start();
    }

    @Override
    public void run() {
        int sum;

        System.out.println(this.thread.getName()+" run");

        synchronized (this.sa){
            this.answer = sa.SummArray(this.a);
        }
        System.out.println("Summ for "+this.thread.getName()+": "+this.answer);

        System.out.println(this.thread.getName()+" stop");

    }
}
