package task2.oop;

public class A implements I1{

    public void a1(){
        String metodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class name - "+this.getClass().getSimpleName()+" Method name - "+metodName);
    }

}
