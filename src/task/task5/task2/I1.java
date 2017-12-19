package task.task5.task2;

public interface I1 {

    default String i1() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        return "Class name - "+this.getClass().getSimpleName()+" Method name - "+methodName+"\n";
    }

}
