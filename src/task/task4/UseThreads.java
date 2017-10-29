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


//        Teacher teacher = new Teacher();
//        Student student = new Student();
    }
}
