package tzepart.test;

public class GuessChar {

    public static void main(String args[])
        throws java.io.IOException {
            char ch, ignore, answer= 'S';

            do{
                System.out.println("Задана буква A-Z");
                System.out.print("Попробуй угадать: ");

                //Получите символ с клаватуры
                ch = (char) System.in.read();

                // Отбросьте все остальные символы во входном буфере
                do{
                    ignore = (char) System.in.read();
                }while(ignore != '\n');

                String message;
                if(ch == answer){
                    message = "You guessed!!))";
                }else {
                    message = "Буква находится ";
                    if(ch < answer){
                        message += "ближе к концу алфавита";
                    }else {
                        message += "ближе к началу алфавита";
                    }
                }
                System.out.println(message);
            }while (answer != ch);


    }

}
