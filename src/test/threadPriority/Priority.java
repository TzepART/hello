package test.threadPriority;

class Priority implements Runnable{

    int count;
    Thread thread;

    static boolean stop = false;
    static String currentName;

    public Priority(String name) {
        this.thread = new Thread(this, name);
        this.count = 0;
        currentName = name;

    }

    @Override
    public void run() {
        System.out.println(this.thread.getName()+" - run");
        do{
            count++;
            if(currentName.compareTo(this.thread.getName()) != 0){
                currentName = thread.getName();
//                System.out.println("In "+currentName);
            }

        }while(stop == false && count < 10e6);
        stop = true;

        System.out.println(this.thread.getName()+" - stop");

    }
}
