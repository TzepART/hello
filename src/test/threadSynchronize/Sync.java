package test.threadSynchronize;

public class Sync {
    public static void main(String args[]){
        int a[] = {1,2,3,4,5};

        MyThread mt1 = new MyThread("Child #1", a);
        MyThread mt2 = new MyThread("Child #2", a);
    }
}
