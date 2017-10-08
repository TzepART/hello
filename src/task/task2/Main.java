package task.task2;


public class Main {

    public static void main(String[] args) {

        B objectB = new B();
        objectB.i1();
        objectB.b1();
        objectB.a1();
        objectB.i2();

        I1 objectI1;

        objectI1 = objectB;
        objectI1.i1();

        ((B)objectI1).b1();
        ((B)objectI1).a1();
        ((B)objectI1).i2();
    }

}
