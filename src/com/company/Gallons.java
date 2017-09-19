package com.company;

import java.util.Scanner;

public class Gallons {

    private static final double COEF = 3.7854;


    public static void main(String[] args) {

        int result = getGloneToLitres();

    }


    /**
     * @return int
     */
    private static int getGloneToLitres(){

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        double liters, gallons;
        int count = 0;
        int countInput = 0;

        while(count == 0 && countInput < 3){
            System.out.println("Введи размер списка переводимых галлонов (но не больше 50):");
            count = reader.nextInt(); // Scans the next token of the input as an int.
            if(count > 50){
                count = 0;
                System.out.println("Вам не захочется просматривать такой длинный список :)");
            }else if(count < 0){
                count = 0;
                System.out.println("Редко встретишь отрциательное количество литров :)");
            }else if(count == 0){
                System.out.println("Кому интересно пересчитывать 0 колличество галлонов?");
            }else{
                System.out.println("Получи результат!)))");
            }

            countInput++;
            if(countInput == 3 && count == 0){
                System.out.println("Как жаль что вы так и не опредилилсь");
                break;
            }
        }

        if(count > 0){
            for(gallons = 1; gallons < count; gallons++){
                liters = gallons*COEF;
                System.out.println(gallons+" галонам соответствует "+liters+" литров;");
                if(gallons%10 == 0){
                    System.out.println();
                }
            }
        }

        return 0;
    }

}
