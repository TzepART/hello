package task.task5.task3;


import javax.swing.*;

class IncrementClass {

    private int value = 0;

    private boolean wait = true;

    private JTextArea jta;


    public IncrementClass(JTextArea jta) {
        this.jta = jta;
    }

    synchronized void increment(Integer step, String threadName) {

        value = value+step;
        jta.append("Поток - "+threadName+"; ");
        jta.append("Значение переменной: " + value+"\n");
//        System.out.println("Поток - "+threadName+"; "+"Значение переменной: " + value+"\n");

        notify();
        try {
            //Встаем на ожидание только когда есть условие для выполнения попеременности потока
            if(this.isWait()){
                wait();
            }
        }
        catch (InterruptedException e) {
            jta.append("Interrupted main thread");
        }
    }


    boolean isWait() {
        return this.wait;
    }

    void setWait(boolean wait) {
        this.wait = wait;
    }
}
