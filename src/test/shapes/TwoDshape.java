package test.shapes;

public class TwoDshape {


    private double width;
    private double height;
    private String name;

    //default constructor
    public TwoDshape() {
        this.width = this.height = 0.0;
        this.name = "none";
    }

    //constructor with parameters
    public TwoDshape(double width, double height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    //constructor for share with width == height
    public TwoDshape(double x, String name) {
        this.width = x;
        this.height = this.width;
        this.name = name;
    }

    //create new shape from other shape
    public TwoDshape(TwoDshape twoDshape) {
        this.width = twoDshape.width;
        this.height = twoDshape.height;
        this.name = twoDshape.name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void showDim(){
        System.out.println("Height"+this.height+"; Width"+this.width);
    }

    double getShapeArea(){
        System.out.println("getShapeArea() must be define");
        return 0.0;
    }
}
