package task.task5.task2;

class B extends A implements I2 {

    String b1() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        return "Class name - "+this.getClass().getSimpleName()+" Method name - "+methodName+"\n";
    }

}
