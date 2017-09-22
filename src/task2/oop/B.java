package task2.oop;

public class B extends A implements I2{

    public void b1(){
        String metodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class name - "+this.getClass().getSimpleName()+" Method name - "+metodName);
    }

}
