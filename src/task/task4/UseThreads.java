package task.task4;


public class UseThreads {

    public static void main(String[] args) {
        Integer countTeachers = 0;
        Integer countStudents = 0;

        try {
            countTeachers = Integer.parseInt(args[0]);
            countStudents = Integer.parseInt(args[1]);
        }catch (NumberFormatException exc){
            System.out.println("Неверный формат аргументов");
        }

        ClassRoom classRoom = new ClassRoom(countStudents);
        Teacher[] teachers = getTeachers(countTeachers, classRoom);

        for (Teacher teacher: teachers){
            teacher.start();
        }
    }


    /**
     * @param countTeachers
     * @return Teacher[]
     */
    public static Teacher[] getTeachers(Integer countTeachers, ClassRoom classRoom){
        Teacher[] teachers = new Teacher[countTeachers];

        //создадим пул потоков учителей
        for(int i=0; i < countTeachers; i++){
            teachers[i] = new Teacher("Преподаватель"+i, classRoom);
        }

        return teachers;
    }
}
