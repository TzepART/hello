package task.task5.task4;

import javax.swing.*;
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
    private JTextArea jta;

    ClassRoom(Integer countStudents, JTextArea jta) {
        this.students = new ArrayList<Student>(countStudents);
        this.jta = jta;
        this.setStudents(countStudents);
    }


    public void invite(Teacher teacher) {

        //индекс рандомного студента
        int idx = new Random().nextInt(this.students.size());

        //выводим имя преподаваел и приглашенного студента
        Student student = this.outputTeacherAndStudentNames(teacher,idx);

        // удалям поток вызванного студента
        removeStudent(idx, student);

        //уведомляем поток следующего преподавателя
        synchronized(teacher.getNextTeacher()) {
            //если поток уже запущем передаем уведомление
            //если не запущен запускаем
            if(teacher.getNextTeacher().isAlive()){
                teacher.getNextTeacher().notify();
            }else{
                teacher.getNextTeacher().start();
            }
        }
    }


    /**
     * @param teacher
     * @param idx
     * @return
     */
    private Student outputTeacherAndStudentNames(Teacher teacher,int idx) {
        // получить рандомного студента

        Student student = this.students.get(idx);

        //вывести имя рандомного студента
        //вывести имя преподавателя
        jta.append(teacher.getName()+" - "+student.getName()+"\n");
//        System.out.println(teacher.getName()+" - "+student.getName()+"\n");


        return student;
    }

    /**
     * @param idx
     * @param student
     */
    private void removeStudent(int idx, Student student) {
        //завершить поток студента
        //удалить из массива студентов
        student.interrupt();
        this.students.remove(idx);
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

    public JTextArea getJta() {
        return jta;
    }
}
