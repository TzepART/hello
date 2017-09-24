package task2.oop;


public class Main {

    public static void main(String[] args) {

        B objectB = new B();
        objectB.i1();
        objectB.b1();
        objectB.a1();
        objectB.i2();

        I1 objectI1;

        objectI1 = (B) objectB;//Явное приведение
        objectI1.i1();
        objectB.b1();
        objectB.a1();
        objectB.i2();
    }

}
