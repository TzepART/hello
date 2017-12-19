package task.task5.task3;


class IncrementClass {

    private int value = 0;

    private boolean wait = true;


    synchronized void increment(Integer step, String threadName) {

        value = value+step;
        System.out.print("Поток - "+threadName+"; ");
        System.out.println("Значение переменной: " + value);
        notify();
        try {
            //Встаем на ожидание только когда есть условие для выполнения попеременности потока
            if(this.isWait()){
                wait();
            }
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted main thread");
        }
    }


    boolean isWait() {
        return this.wait;
    }

    void setWait(boolean wait) {
        this.wait = wait;
    }
}
