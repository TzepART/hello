package task.task5.task3;


import javax.swing.*;

public class task3 {

    public static void getResult(String args[], JTextArea jta){

        try {
            Integer countFirst = Integer.parseInt(args[0]);
            Integer countSecond = Integer.parseInt(args[1]);

            IncrementClass incrementObj = new IncrementClass(jta);
            FirstTread mt1 = new FirstTread("FirstTread", incrementObj, countFirst);
            SecondThread mt2 = new SecondThread("SecondThread", incrementObj, countSecond);

            mt2.start();
            mt1.thread.start();
        }catch(ArrayIndexOutOfBoundsException e){
            jta.append("Ошибка в количестве параметров");
        }catch(NumberFormatException e){
            jta.append("Ошибка в формате введенного параметра");
        }catch(Exception e){
            jta.append(e.toString());
        }
    }
}
