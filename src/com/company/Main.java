package com.company;

import java.util.Scanner;
import java.awt.*;
import javax.swing.*;


public class Main extends JPanel {

    private static final double COEF = 3.7854;

    private String message;


    public static void main(String[] args) {

        String message;
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        System.out.println("Введи размер списка переводимых галлонов (но не больше 50):");
        message = reader.nextLine(); // Scans the next token of the input as an int.

        Main main = new Main();

        main.viewMessage(message);
    }

    private String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public void paint(Graphics g) {
        Dimension d = this.getPreferredSize();
        int fontSize = 20;

        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

        g.setColor(Color.black);

        g.drawString(this.getMessage(), 10, 20);
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