package test.threadWait;

public class TickTock {

    String state;

    synchronized void tick(boolean running){
        if (!running){
            state = "ticked";
            notify(); //notify waiting threads
            return;
        }

        System.out.print("Tick ");

        state = "ticked"; //set current state after takt "Tick"
        notify(); //allow execute method tock()
        try {
            while (!state.equals("tocked")){
                wait(); // wait end method tock()
            }
        }catch (InterruptedException exc){
            System.out.print("Interrupted thread");
        }
    }


    synchronized void tock(boolean running){
        if (!running){
            state = "tocked";
            notify(); //notify waiting threads
            return;
        }

        System.out.println("Tock ");

        state = "tocked"; //set current state after takt "Tick"
        notify(); //allow execute method tick()
        try {
            while (!state.equals("ticked")){
                wait(); // wait end method tick()
            }
        }catch (InterruptedException exc){
            System.out.print("Interrupted thread");
        }
    }


}
