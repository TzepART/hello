package task.task5.task2;


public class task2 {

    public static String getResult() {

        String result = "";

        B objectB = new B();
        result = result.concat(objectB.i1());
        result = result.concat(objectB.b1());
        result = result.concat(objectB.a1());
        result = result.concat(objectB.i2());

        I1 objectI1;

        objectI1 = objectB;

        result = result.concat(objectI1.i1());
        result = result.concat(((B)objectI1).b1());
        result = result.concat(((B)objectI1).a1());
        result = result.concat(((B)objectI1).i2());

        return result;
    }

}
