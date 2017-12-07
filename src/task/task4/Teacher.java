package task.task4;

final class Teacher extends Thread {

    private ClassRoom classRoom;
    private Teacher nextTeacher;

    Teacher(String name, ClassRoom classRoom) {
        super(name);
        this.classRoom = classRoom;
    }

    @Override
    public void run() {
        while (classRoom.isCanInvite()) {
            classRoom.invite(this);

            //Встаем на ожидание
            synchronized(this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted thread");
                }
            }
        }
    }

    public void setNextTeacher(Teacher nextTeacher) {
        this.nextTeacher = nextTeacher;
    }

    public Teacher getNextTeacher() {
        return this.nextTeacher;
    }
}
