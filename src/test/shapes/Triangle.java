package test.shapes;

public class Triangle extends TwoDshape {

    private String style;

    public Triangle() {
        super();
        this.style = "none";
    }

    public Triangle(String style, double width, double height) {
        super(width, height, "Triangle");
        this.style = style;
    }

    public Triangle(double x) {
        super(x, "Triangle");
        this.style = "Painted";
    }

    public Triangle(Triangle triangle) {
        super(triangle);
        this.style = triangle.style;
    }

    @Override
    double getShapeArea() {
        return this.getWidth()*this.getHeight()/2;
    }

    @Override
    void showDim() {
        System.out.println("Triangle "+this.style);
    }
}
