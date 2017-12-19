package task.task5.task4;

final class Teacher extends Thread {

    private ClassRoom classRoom;
    private Teacher nextTeacher;

    Teacher(String name, ClassRoom classRoom) {
        super(name);
        this.classRoom = classRoom;
    }

    @Override
    public void run() {
        while (true) {
            if(classRoom.isCanInvite()){
                classRoom.invite(this);
                //Встаем на ожидание
                synchronized(this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        classRoom.getJta().append("Interrupted thread");
                    }
                }
            }else{
                //как только все студенты закончились выводим из wait следующий поток
                //чтобы он мог завершиться и завршаем поток
                synchronized(this.nextTeacher) {
                    this.nextTeacher.notify();
                }
               return;
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
