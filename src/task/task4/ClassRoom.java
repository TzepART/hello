package task.task4;

import java.util.ArrayList;
import java.util.Random;

/**
* Каждый преподаватель строго по очереди приглашает любого студента.
* Как только студент получает приглашение, то поток  студент заканчивается.
* Преподаватель становится после этого последним в очереди преподавателей на вызов студента.
* Потоки - преподаватели должны работать строго по очереди.
* Работа продолжается пока есть хоть один студент и если число преподавателей больше нуля.
* */


public class ClassRoom {

    private ArrayList<Student> students;

    ClassRoom(Integer countStudents) {
        this.students = new ArrayList<Student>(countStudents);
        this.setStudents(countStudents);
    }


    synchronized void invite(Teacher teacher) {
        if (this.isCanInvite()) {
            // получить рандомного студента
            int idx = new Random().nextInt(this.students.size());
            Student student = this.students.get(idx);

            //вывести имя рандомного студента
            //вывести имя преподавателя
            System.out.print(teacher.getName());
            System.out.print(" - ");
            System.out.println(student.getName());

            //завершить поток студента
            //удалить из массива студентов
            student.interrupt();
            this.students.remove(idx);

            synchronized (teacher.getNextTeacher()) {
//                teacher.getNextTeacher().notify();
                teacher.getNextTeacher().run();
            }

            //Встаем на ожидание
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted main thread");
            }
        }
    }


    /**
     * @param countStudents
     * @return void
     */
    public void setStudents(Integer countStudents){
        //создадим пул потоков студунтов
        for(int i=0; i < countStudents; i++){
            Student student = new Student("Студент"+(i+1));
            student.start();
            this.students.add(student);
        }
    }

    public boolean isCanInvite(){
        if(this.students.size() > 0){
            return true;
        }else {
            return false;
        }
    }
}
