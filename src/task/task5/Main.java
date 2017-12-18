package task.task5;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args)
    {
        GUI gui = new GUI();
        //звкрываем апрограмму при закрытии окна
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}