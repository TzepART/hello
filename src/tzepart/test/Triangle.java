package tzepart.test;

import java.util.Scanner;


public class Triangle {

    private static final int COUNT_SIDE = 3;

    public static void main(String[] args) {

//        double[] sides = getSidesFromArguments(args);
        double[] sides = getSidesFromConsole();

        checkAvailableTriangle(sides);

    }

    private static double[] getSidesFromArguments(String[] args) {
        double[] sides = new double[args.length];
        int i = 0;

        for (String arg: args) {
            sides[i] = Double.parseDouble(arg);
            i++;
        }

        return sides;
    }

    private static double[] getSidesFromConsole() {

        double[] sides = new double[COUNT_SIDE];
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        int i = 0;

        System.out.println("Введи стороны треугольника:");
        while(i < COUNT_SIDE){
            sides[i] = reader.nextDouble(); // Scans the next token of the input as an int.
            i++;
        }

        return sides;
    }


    /**
     * @return int
     */
    private static int checkAvailableTriangle(double[] sides){

        double a = sides[0];
        double b = sides[1];
        double c = sides[2];


        if(
                (a < (b + c) && a > (b - c)) &&
                (b < (a + c) && b > (a - c)) &&
                (c < (a + b) && c > (a - b))
        ){
            System.out.println("Это может быть треугольником!)");
        }else{
            System.out.println("Это не может быть треугольником!(((");
        }

        return 0;
    }

}
