package task.task2;

class B extends A implements I2{

    void b1(){
        String metodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class name - "+this.getClass().getSimpleName()+" Method name - "+metodName);
    }

}
