package test.shapes;

public class DynShapes {
    public static void main(String args[]){

        TwoDshape shapes[] = new TwoDshape[5];

        shapes[0] = new Triangle("contur",8,12);
        shapes[1] = new Rectangle(10);
        shapes[3] = new Rectangle(10,4);
        shapes[2] = new Triangle(7);
        shapes[4] = new TwoDshape(10,20, "Figure");

        for (TwoDshape shape: shapes) {
            System.out.println("Object - "+ shape.getName());
            System.out.println("Area - " + shape.getShapeArea());
            System.out.println();
        }
    }
}
