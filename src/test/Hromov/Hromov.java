package test.Hromov;

import java.util.ArrayList;
import java.util.Random;

public class Hromov {


    public static void main(String args[]){
//        Random random = new Random();
//        for(int i =0; i<100; i++){
//            System.out.println(random.nextDouble());
//        }

//        Number[][] couples = Hromov.generationCouplesNumbers(250);
//        for (Number[] row:
//             couples) {
//            for (Number value:
//                    row) {
//                System.out.print(value+",");
//            }
//            System.out.println();
//        }

        Hromov obj = new Hromov();
        obj.mainForLaba2();
    }

    private void mainForLaba2() {
        int num = 100;

        System.out.println("1.	Метод, основанный на центральной предельной теореме ");
        applyMethod(num, "mbmass");

        System.out.println("2.	Метод Бокса-Мюллера ");
        applyMethod(num, "bmass");

        System.out.println("3.	Метод Марсальи-Брея ");
        applyMethod(num, "nmass");

    }

    private void applyMethod(int num, String methodName) {
        ArrayList<Number> array;

        System.out.println("Massive ");

        array = getArrayByType(num, methodName);
        this.printArray(array);

        Sample_mean(array, num);

        System.out.println("Проверка сходения к 0");

        for (int i = 10; i < 35; i += 5) {
            num = 10 * i;

            System.out.println(" N = ");

            array = getArrayByType(num, methodName);
            Sample_mean(array, num);

            System.out.println("--------------");

        }

        System.out.println("Тест на симметрию");

        num = 240;
        array = getArrayByType(num, methodName);
        Symmetry(array, num);
    }

    private ArrayList<Number> getArrayByType(int num, String methodName) {
        ArrayList<Number> array = new ArrayList<Number>();

        if (methodName.equals("mbmass")){
            array = this.RG(array, num);
        }else if(methodName.equals("bmass")){
            array = this.BoxMuller(array, num);
        }else if(methodName.equals("nmass")){
            array = this.MorsaliBrey(array, num);
        }
        return array;
    }


    ////////1.	Метод, основанный на ЦПТ /////////////
    private ArrayList<Number> RG(ArrayList<Number> vec_num, int volume) {
        Random random = new Random();
        int m = 12;
        int tmp = 0;
        Number x = 0;
        Number X[] = new Number[13];
        Number mass[] = new Number[400];
        for (int i = 1; i <= volume; i++) {
            Number sum = 0;
            for (int j = 1; j <= m; j++) {
                Number rand = random.nextDouble();
                X[j] = rand;
                sum = sum.doubleValue() + rand.doubleValue();
            }
            x = (sum.doubleValue() - m / 2) / Math.pow((m / 12),2);
            mass[i] = x;
            vec_num.add(x);
        }
        return vec_num;
    }

    ////2. Метод Бокса-Мюллера////
    private ArrayList<Number> BoxMuller(ArrayList<Number> mass, int num) {
        Random random = new Random();
        Number X[] = new Number[400];
        Number a1, a2;
        double pi = Math.PI;

        for (int i = 1; i <= num; i++) {
//            a1 = random.nextDouble();
//            a2 = random.nextDouble();
            a1 = random.nextDouble();
            a2 = random.nextDouble();
            X[i]  = Math.sqrt(-2 * Math.log(a1.doubleValue()))*Math.sin(2 * pi*a2.doubleValue());
            X[i+1]  = Math.sqrt(-2 * Math.log(a2.doubleValue()))*Math.cos(2 * pi*a2.doubleValue());
            mass.add(X[i]);
        }
        return mass;
    }

    ////3. Метод Морсальи - Брея////
    private ArrayList<Number> MorsaliBrey(ArrayList<Number> mass, int num) {
        Random random = new Random();
        Number X[] = new Number[400];
        Number a1, a2;
        double pi = Math.PI;
        boolean btmp = true;

        for (int i = 1; i <= num; i++) {
            btmp = true;
            a1 = 0;
            double s = 0;
            while (btmp) {

                a1 = random.nextDouble();
                a2 = random.nextDouble();

                s = Math.pow((a1.doubleValue() * 2 - 1), 2) + Math.pow((a2.doubleValue() * 2 - 1), 2);
                if (s > 0 && s < 1) { btmp = false; }
            }
            X[i]  = (a1.doubleValue() * 2 - 1) * Math.sqrt((-2 * Math.log(s)) / s);
            mass.add(X[i]);
        }
        return mass;
    }

    private void Sample_mean(ArrayList<Number> mass, int num) {
        double s = 0;
        double skv = 0;
        Number sred = 0;
        Number disp = 0;
        for (int i = 0; i < num; i++) {
            double value = mass.get(i).doubleValue();
            s = s + value;
            skv = skv + Math.pow(value, 2);
        }
        sred = s / num;
        disp = (skv - Math.pow(sred.doubleValue(), 2)) / num;

        System.out.println(" Sample_mean ");
        System.out.println(" M  = "+sred);
        System.out.println("Disp ");
        System.out.println(" D  = "+disp);
    }


    //////////Симметричность распределения/////////////
    private void Symmetry(ArrayList<Number> mass, int num) {
        double[] hit = new double[8];
        ArrayList<Number> tmp = new ArrayList<Number>();
        int Sum = 0;
        double Xi = 0;
        Number[] xi = new Number[8];
        String[] interval = {"000","001","010","011","100","101","110","111"};
        int m = num / 3;
        for (int i = 0; i < 8; i++) {
            hit[i] = 0;
            xi[i] = 0;
        }
        for (int i = 0; i < num; i++) {
            if (mass.get(i).doubleValue() < 0){
                tmp.add(0);
            } else {
                tmp.add(1);
            }
        }
        for (int i = 0; i < num - 2; i++) {
            if (tmp.get(i).doubleValue() == 0) {
                if (tmp.get(i + 1).doubleValue() == 0) {
                    if (tmp.get(i + 2).doubleValue() == 0) { hit[0]+=1;}
                    else { hit[1] += 1; }
                }
                if (tmp.get(i + 1).doubleValue() == 1) {
                    if (tmp.get(i + 2).doubleValue() == 0) { hit[2] += 1; }
                    else { hit[3] += 1; }
                }
            }
            if (tmp.get(i).doubleValue() == 1) {
                if (tmp.get(i + 1).doubleValue() == 0) {
                    if (tmp.get(i + 2).doubleValue() == 0) { hit[4] += 1 ; }
                    else { hit[5] += 1; }
                }
                if (tmp.get(i + 1).doubleValue() == 1) {
                    if (tmp.get(i + 2).doubleValue() == 0) { hit[6] += 1; }
                    else { hit[7] += 1; }
                }
            }
            i += 2;
        }
        System.out.println("Инт-л Попад-е  Xi2");

        double qw = m  / 8;
        for (int i = 0; i < 8; i++) {
            xi[i] = (Math.pow((hit[i] - qw), 2)) / qw;
            Sum += hit[i];
            Xi += xi[i].doubleValue();
            System.out.println("   "+interval[i]+"  "+hit[i]+"   "+xi[i]);
        }
        System.out.println("Сумма попад-й  = " + Sum);
        System.out.println(" ; Сумма Xi2 = " + Xi);
    }

    private void printArray(ArrayList<Number> arrayList){
        for (Number item:
                arrayList) {
            System.out.println(item);
        }
    }


    public static Number[][] generationCouplesNumbers(int countCouples){
        Number[][] couple = new Number[countCouples][2];

        for (int i = 0; i < countCouples; i++){
            Random random = new Random();
            Number randValue1 = random.nextDouble();
            Number randValue2 = random.nextDouble();
            couple[i][0] = randValue1;
            couple[i][1] = randValue2;
        }

        return couple;
    }

}
