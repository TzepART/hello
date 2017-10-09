package test.threadSynchronize;

public class SummArray {

    private int sum;

    synchronized int SummArray(int nums[]){
        this.sum = 0;

        for (int num:nums) {
            this.sum+=num;
            System.out.println("Current value summ for "+Thread.currentThread().getName()+": "+this.sum);

            try{
                Thread.sleep(10);
            }catch (InterruptedException exc){
                System.out.println("Interrupted main thread");
            }
        }

        return this.sum;
    }

}
