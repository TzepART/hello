package task.task4;

class Teacher extends Thread {

    Teacher(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.print(this.getName());
    }

}
