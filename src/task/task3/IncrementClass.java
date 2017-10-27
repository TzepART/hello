package task.task3;


class IncrementClass {

    private int value = 0;


    synchronized void increment(Integer currentCount, Integer step, String threadName) {

        value = value+step;
        System.out.print("Поток - "+threadName+"; ");
        System.out.println("Значение переменной: " + value);
        notify();
        try {
            //Встаем на ожидание только когда есть условие для выполнения попеременности потока
            if(currentCount < this.getMinCountIteration()){
                wait();
            }
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted main thread");
        }
    }

    private Integer getMinCountIteration(){
        Integer minCount;

        if(FirstTread.count <= SecondThread.count){
            minCount = FirstTread.count;
        }else {
            minCount = SecondThread.count;
        }

        return  minCount;
    }

}
