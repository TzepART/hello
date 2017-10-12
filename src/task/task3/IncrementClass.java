package task.task3;

public class IncrementClass {

    String value;

    synchronized void tick(boolean running){
        if (!running){
            value = "ticked";
            notify(); //notify waiting threads
            return;
        }

        System.out.print("Tick ");

        value = "ticked"; //set current value after takt "Tick"
        notify(); //allow execute method tock()
        try {
            while (!value.equals("tocked")){
                wait(); // wait end method tock()
            }
        }catch (InterruptedException exc){
            System.out.print("Interrupted thread");
        }
    }


    synchronized void tock(boolean running){
        if (!running){
            value = "tocked";
            notify(); //notify waiting threads
            return;
        }

        System.out.println("Tock ");

        value = "tocked"; //set current value after takt "Tick"
        notify(); //allow execute method tick()
        try {
            while (!value.equals("ticked")){
                wait(); // wait end method tick()
            }
        }catch (InterruptedException exc){
            System.out.print("Interrupted thread");
        }
    }


}
