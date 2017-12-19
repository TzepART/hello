package task.task5.task4;


import javax.swing.*;

public class task4 {

    public static void getResult(String[] args, JTextArea jta) {
        Integer countTeachers;
        Integer countStudents;

        try {
            countTeachers = Integer.parseInt(args[0]);
            countStudents = Integer.parseInt(args[1]);
        }catch (NumberFormatException exc){
            jta.append("Неверный формат аргументов");
            return;
        }catch(ArrayIndexOutOfBoundsException e){
            jta.append("Ошибка в количестве параметров");
            return;
        }

        if(countTeachers == 0){
            jta.append("Число учтелей 0");
        }else if(countStudents == 0){
            jta.append("Число студентов 0");
        }else{
            ClassRoom classRoom = new ClassRoom(countStudents,jta);
            Teacher[] teachers = getTeachers(countTeachers, classRoom);

            //запускаем первый
            teachers[0].start();

//            for (Teacher teacher: teachers){
////                jta.append(teacher.getName()+" - "+teacher.getNextTeacher().getName());
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
