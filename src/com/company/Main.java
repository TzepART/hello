package com.company;

import java.util.Scanner;
import java.awt.*;
import javax.swing.*;


public class Main extends JPanel {

    private static final double COEF = 3.7854;

    private String message;


    public static void main(String[] args) {

        String message;
        int result = getGloneToLitres();

        if(result == 0){
            message = "Все клевански!!!";
        }else{
            message = "Что-то пошло не так";
        }

        Main main = new Main();

        main.viewMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void paint(Graphics g) {
        Dimension d = this.getPreferredSize();
        int fontSize = 20;

        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

        g.setColor(Color.black);

        g.drawString(this.getMessage(), 10, 20);
    }

    /**
     * @return int
     */
    private static int getGloneToLitres(){

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        double liters, gallons;
        int count = 0;
        int countInput = 0;

        while(count == 0 || countInput < 3){
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

    private void viewMessage(String message){

        this.message = message;
        JFrame frame = new JFrame("Result");
        Main main = new Main();
        main.setMessage(message);
        frame.getContentPane().add(main);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);
    }


}