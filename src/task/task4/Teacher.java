package task.task4;

class Teacher extends Thread {

    private ClassRoom classRoom;
    private int teacherNumber;

    Teacher(String name, ClassRoom classRoom, int teacherNumber) {
        super(name);
        this.classRoom = classRoom;
        this.teacherNumber = teacherNumber;
    }

    @Override
    public void run() {
        while(this.classRoom.isCanInvite()){
            if(ClassRoom.nextTeacher == this.teacherNumber){
                this.classRoom.invite(this.getName());
            }
        }
    }

}
