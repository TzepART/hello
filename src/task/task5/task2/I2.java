package task.task5.task2;

public interface I2 {

    default String i2() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        return "Class name - "+this.getClass().getSimpleName()+" Method name - "+methodName+"\n";
    }

}
