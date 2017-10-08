package task.oop.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Triangle {

    private static final int COUNT_SIDE = 3;

    public static void main(String[] args) {

//        double[] sides = getSidesFromArguments(args);
        double[] sides = getSidesFromConsole();

        try {
            checkAvailableTriangle(sides);
        }catch (Error e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Метод отвечающий обработку данных из параметров
     * @return double[]
     * */
    private static double[] getSidesFromArguments(String[] args) {
//        double[] sides = new double[args.length];
        List<Number> sides = new ArrayList<Number>();


        int i = 0;

        for (String arg: args) {

            try {
                double side = Double.parseDouble(arg);

                if(side < 0){
                    System.out.println("Сторона "+i+": не может быть отрицательным!");
                }else if(side == 0){
                    System.out.println("Сторона "+i+": не может быть равна 0");
                }else{
                    sides.add(side); // Scans the next token of the input as an int.
                }
            }catch (NumberFormatException e){
                System.out.println("Сторона "+i+": Введены неккоректные данные");
            }

            i++;
        }

//        return sides;

        double dSides[] = new double[sides.size()];

        int n = 0;
        for (Number side: sides) {
            dSides[n] = side.doubleValue();
            n++;
        }

        return dSides;

    }

    /**
     * Метод отвечающий за ввод данных
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
     * Проверяет возможность данных массива быть треугольником
     */
    private static void checkAvailableTriangle(double[] sides){

        if(sides.length == 3){

            //получим возможные ошибки
            String[] errors = getErrorsByArraySides(sides);

            if(errors.length == 0){
                System.out.println("Это может быть треугольником!)");
            }else{
                System.out.println("Это не может быть треугольником!(((");
                for (String error: errors) {
                    System.out.println(error);
                }
            }
        }else {
//            throw new Error("Переданный массив не может быть сторонами треугольника");
            throw new Error("Это не может быть треугольником!(((");
        }

    }

    /**
    * Возваращает массив с ошибками
    * */
    private static String[] getErrorsByArraySides(double[] sides){

        // dynamically hold the instances
        List<String> list = new ArrayList<String>();


        for(int i = 0; i < sides.length; i++) {
            double checkSide = sides[i];
            double summ = 0;

            int step = 0;
            for (double side: sides) {
                if(step != i){
                    summ += side;
                }
                step++;
            }
            if(checkSide > summ){
                list.add("Ошибка: Сторона "+(i+1)+" больше суммы двух других сторон;");
            }
        }

        return list.toArray( new String[list.size()] );
    }

}
