package task.task5.task1;

import java.util.ArrayList;
import java.util.List;


public class task1 {

    private static final int COUNT_SIDE = 3;

    public static String getResult(String[] args) {

        String message;

        try {
            double[] sides = getSidesFromArguments(args);
            message = checkAvailableTriangle(sides);
        }catch (Error e){
            message = "Это не может быть треугольником!(((\n";
            message = message.concat(e.getMessage());
        }

        return message;
    }

    /**
     * Метод отвечающий обработку данных из параметров
     * @return double[]
     * */
    private static double[] getSidesFromArguments(String[] args) throws Error {
        List<Number> sides = new ArrayList<Number>();
        String errorMessage = "";


        int i = 0;

        for (String arg: args) {

            try {
                double side = Double.parseDouble(arg);

                if(side < 0){
                    errorMessage = errorMessage.concat("Сторона "+(i+1)+": не может быть отрицательным!\n");
                }else if(side == 0){
                    errorMessage = errorMessage.concat("Сторона "+(i+1)+": не может быть равна 0\n");
                }else{
                    sides.add(side); // Scans the next token of the input as an int.
                }
            }catch (NumberFormatException e){
                errorMessage = errorMessage.concat("Сторона "+(i+1)+": Введены неккоректные данные\n");
            }

            i++;
        }

        if(!errorMessage.isEmpty()){
            throw new Error(errorMessage);
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
     * Проверяет возможность данных массива быть треугольником
     */
    private static String checkAvailableTriangle(double[] sides) throws Error {
        String result;

        if(sides.length == COUNT_SIDE){

            //получим возможные ошибки
            String[] errors = getErrorsByArraySides(sides);

            if(errors.length == 0){
                result = "Это может быть треугольником!)";
            }else{
                result = "Это не может быть треугольником!(((\n";
                for (String error: errors) {
                    result = result.concat(error+"\n");
                }
            }
        }else {
            throw new Error("Это не может быть треугольником!(((");
        }
        return result;
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
