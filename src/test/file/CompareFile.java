package test.file;

import java.io.FileInputStream;
import java.io.IOException;

public class CompareFile {

    public static void main(String args[]){
        int i = 0, j = 0;

        if (args.length != 2){
            System.out.println("Don't correct count file names;");
            return;
        }

        //compare two files
        try(FileInputStream file1 = new FileInputStream(args[0]);
            FileInputStream file2 = new FileInputStream(args[1])
        ){
            do {
                i = file1.read();
                j = file2.read();
                if(i != j) break;
            }while (i != -1 && j != -1);
            if(i != j){
                System.out.println("file1 and file2 aren't equal");
            }else {
                System.out.println("file1 and file2 are equal");
            }
        }catch (IOException exc){
                System.out.println("Error input/output: "+exc);
        }

    }

}
