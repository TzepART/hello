package tzepart.test;

import java.util.InputMismatchException;
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

    /**
     * method for input data
     * @return double[]
     * */
    private static double[] getSidesFromConsole() {

        double[] sides = new double[COUNT_SIDE];

        int i = 0;

        System.out.println("Введи стороны треугольника:");
        while(i < COUNT_SIDE){

            System.out.println("Введи размер стороны - "+(i+1));
            double side;

            Scanner reader = new Scanner(System.in);  // Reading from System.in
//            double side = reader.nextDouble();

            try{
                side = reader.nextDouble();
                if(side < 0){
                    System.out.println("Размер не может быть отрицательным!");
                }else if(side == 0){
                    System.out.println("Сторона не может быть равна 0");
                }else{
                    sides[i] = side; // Scans the next token of the input as an int.
                    i++;
                }

            }catch (InputMismatchException t){
                System.out.println("Введены неккоректные данные");
            }
        }

        return sides;
    }


    /**
     *
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
