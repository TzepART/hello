package task.task4;

class Teacher extends Thread {

    private ClassRoom classRoom;

    Teacher(String name, ClassRoom classRoom) {
        super(name);
        this.classRoom = classRoom;
    }

    @Override
    public void run() {
        while(this.classRoom.isCanInvite()){
            this.classRoom.invite(this.getName());
        }
    }

}
