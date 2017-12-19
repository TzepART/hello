package task.task5.task2;

class A implements I1 {

    String a1() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        return "Class name - "+this.getClass().getSimpleName()+" Method name - "+methodName+"\n";
    }

}
