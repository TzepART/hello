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

        if(countTeachers == 0){
            System.out.println("Число учтелей 0");
        }else if(countStudents == 0){
            System.out.println("Число студентов 0");
        }else{
            ClassRoom classRoom = new ClassRoom(countStudents);
            Teacher[] teachers = getTeachers(countTeachers, classRoom);
            teachers[0].start();

//            for (Teacher teacher: teachers){
////                System.out.println(teacher.getName()+" - "+teacher.getNextTeacher().getName());
//                teacher.start();
//                try {
//                    teacher.sleep(5);
//                }catch(InterruptedException e){
//
//                }
//            }
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
            teachers[i] = new Teacher("Преподаватель"+(i+1), classRoom);
        }

        //установим ссылки на следующих преподователей
        for(int i=0; i < countTeachers; i++){
            if(!(i == (countTeachers - 1))){
                teachers[i].setNextTeacher(teachers[i+1]);
            }else{
                teachers[i].setNextTeacher(teachers[0]);
            }
        }

        return teachers;
    }
}
