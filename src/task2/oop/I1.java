package task2.oop;

public interface I1 {

    default void i1() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class name - "+this.getClass().getSimpleName()+" Method name - "+methodName);
    }

}