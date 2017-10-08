package test.shapes;

public class Rectangle extends TwoDshape {


    public Rectangle() {
        super();
    }

    public Rectangle(double width, double height) {
        super(width, height, "Rectangle");
    }

    public Rectangle(double x) {
        super(x, "Rectangle");
    }

    public Rectangle(Rectangle triangle) {
        super(triangle);
    }

    boolean isSquare(){
        if(this.getHeight() == this.getWidth()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    double getShapeArea() {
        return this.getWidth()*this.getHeight();
    }

}
