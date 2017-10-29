package task.task4;

/**
* Каждый преподаватель строго по очереди приглашает любого студента.
* Как только студент получает приглашение, то поток  студент заканчивается.
* Преподаватель становится после этого последним в очереди преподавателей на вызов студента.
* Потоки - преподаватели должны работать строго по очереди.
* Работа продолжается пока есть хоть один студент и если число преподавателей больше нуля.
* */


public class ClassRoom {

    private Integer countTeachers;
    private Integer countStudents;

    public ClassRoom(Integer countTeachers, Integer countStudents) {
        this.countTeachers = countTeachers;
        this.countStudents = countStudents;
    }

    public void inviteTeachersByStudents(){
        Teacher[] teachers = this.getTeachers(this.countTeachers);
        Student[] students = this.getStudents(this.countStudents);


    }

    /**
     * @param countTeachers
     * @return Teacher[]
     */
    public Teacher[] getTeachers(Integer countTeachers){
        Teacher[] teachers = new Teacher[countTeachers];

        //создадим пул потоков учителей
        for(int i=0; i < countTeachers; i++){
            teachers[i] = new Teacher("Преподаватель"+i);
        }

        return teachers;
    }

    /**
     * @param countStudents
     * @return Student[]
     */
    public Student[] getStudents(Integer countStudents){
        Student[] students = new Student[countStudents];
        //создадим пул потоков студунтов
        for(int i=0; i < countStudents; i++){
            students[i] = new Student("Студент"+i);
        }

        return students;
    }
}
