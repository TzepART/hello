package task.oop.task2;

class A implements I1{

    void a1(){
        String metodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class name - "+this.getClass().getSimpleName()+" Method name - "+metodName);
    }

}
