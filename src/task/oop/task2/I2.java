package task.oop.task2;

public interface I2 {

    default void i2() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class name - "+this.getClass().getSimpleName()+" Method name - "+methodName);
    }

}
