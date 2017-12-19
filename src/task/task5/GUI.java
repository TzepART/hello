package task.task5;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import task.task5.task1.task1;
import task.task5.task2.task2;
import task.task5.task3.task3;
import task.task5.task4.task4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;

    //кнопки
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    //панели
    private JPanel main_panel;
    private JPanel panel;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    //Заголовки
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    //кнопки RadioButton
    private JRadioButton lab1;
    private JRadioButton lab2;
    private JRadioButton lab3;
    private JRadioButton lab4;

    //поле для ввода арuгументов
    private JTextField input = new JTextField();

    //поле для вывода результатов выполнения программы
    static public JTextArea jta = new JTextArea(10, 10);
    //поле для вывода заданий программ
    static public JTextArea jta2 = new JTextArea(10, 10);

    // полосы прокрутки будут отображаться всегда
    private JScrollPane scroll = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JScrollPane scroll2 = new JScrollPane(jta2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


    // контейнер для размещения компонентов формы
    private Container container = getContentPane();

    protected String textTask1 = "1. Приложение с 3 параметрами. Необходимо определить  могут ли быть \n" +
            "введенные параметры сторонами треугольника и вывести сообщение \n" +
            "в случае, если это невозможно. \n\n" +
            "Введите 3 аргумента через пробел в поле Введите аргументы и нажмите кнопку ОК.";

    protected String textTask2 = "2. Создать приложение с 2 классами- А, B  и 2 интерфейсами I1, I2.\n" +
            "Класс А является родительским для класса B,\n" +
            " Интерфейс I1 является родительским для интерфейса I2.\n" +
            "Класс A реализует интерфейс I1.\n" +
            "Класс В реализует интерфейс I2.\n" +
            "Интерфейс I1 содержит метод i1, класс А  содержит метод а1, интерфейс I2 содержит \n" +
            "метод i2. Класс B содержит метод b1.\n" +
            "Все методы выводят строку с именем своего класса или интерфейса и именем метода.\n" +
            "Создать минимальное число объектов для выполнения всех указанных 4 методов.\n" +
            "и выполнить все эти методы. Присвоить переменным типа I1  каждый из созданных\n" +
            " объектов и еще раз выполнить все прежние методы для\n" +
            " переменной типа I1(используя приведение типов).";

    protected String textTask3 = "3. Создать 2 потока разными способами. Один из этих потоков увеличивает разделенную\n" +
            "между потоками переменную на 1000, а другой на 10 . Потоки работают попеременно.\n" +
            "Цикл для каждого потока выполняется число раз заданное параметром.\n" +
            "Нужно выводить для каждого потока его имя и значение измененной переменной.\n" +
            "Выполнить задание с использованием конструкции synchronized .\n" +
            "Не использовать в этом задании флаги для синхронизации потоков,\n" +
            "а только методы wait и notify. Также не использовать любые задержки для\n" +
            "потоков после начала их работы в виде методов sleep, yield или wait c параметром.\n\n" +
            "Введите 2 аргумента через пробел:\n" +
            "Первый - число циклов для 1го потока, \n" +
            "Второй - число циклов для 2го потока\n" +
            "и нажмите кнопку ОК.";

    protected String textTask4 = "4. Создать приложение с 2 параметрами. 1 параметр задает количество cтудентов,\n" +
            "2-ой параметр задает количество преподавателей.\n" +
            "каждый преподаватель строго по очереди приглашает любого студента.\n" +
            "Как только студент получает приглашение , то поток  студент заканчивается.\n" +
            "Преподаватель становится после этого последним в очереди преподавателей на вызов студента .\n" +
            "Потоки - преподаватели должны работать строго по очереди.\n" +
            "Работа продолжается пока есть хоть один студент и если число преподавателей больше нуля.\n" +
            "Использовать ограничения из задания 3.\n" +
            "Выводить на дисплей имя преподавателя вместе с именем студента.\n" +
            "Выглядеть это будет примерно так: преподаватель1- студент3\n" +
            "преподаватель2- студент1\n" +
            "преподаватель1- студент4\n" +
            "преподаватель2- студент2\n\n" +
            "Введите 2 аргумента через пробел:\n" +
            "Первый - число преподавателей \n" +
            "Второй - число студентов\n" +
            "и нажмите кнопку ОК.";

    // Listener для отслеживания переключения radiobutton
    ActionListener listener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            switch (((JRadioButton) ae.getSource()).getText()) {
                case "Лабораторная работа №1":
                    setEmptyFields();
                    setVisibleButton(1);
                    setTextToTaskTextField(textTask1);

                    //Listener для отслеживания нажатия кнопки button1
                    button1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jta.setText("");
                            String result = task1.getResult(GUI.getArrayArgumentsFromString(input.getText().trim()));
                            jta.append(result);
                        }
                    });
                    break;
                case "Лабораторная работа №2":
                    setEmptyFields();
                    setVisibleButton(2);
                    setTextToTaskTextField(textTask2);

                    //Listener для отслеживания нажатия кнопки button2
                    button2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jta.setText("");
                            String result = task2.getResult();
                            jta.append(result);
                        }
                    });
                    break;
                case "Лабораторная работа №3":
                    setEmptyFields();
                    setVisibleButton(3);
                    setTextToTaskTextField(textTask3);

                    //Listener для отслеживания нажатия кнопки button3
                    button3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jta.setText("");
                            String[] params = GUI.getArrayArgumentsFromString(input.getText().trim());
                            task3.getResult(params, jta);
                        }
                    });
                    break;
                case "Лабораторная работа №4":
                    setEmptyFields();
                    setVisibleButton(4);
                    setTextToTaskTextField(textTask4);

                    //Listener для отслеживания нажатия кнопки button4
                    button4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jta.setText("");
                            String[] params = GUI.getArrayArgumentsFromString(input.getText().trim());
                            task4.getResult(params, jta);
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };

    //конструктор
    GUI() {
        // зададим размеры окна
        super.setSize(830, 585);

        //заголовок
        super.setTitle("Лабораторная работа №5");

        //установим окно по центру экрана
        super.setLocationRelativeTo(null);

        //инициализируем панели
        initPanels();

        //инициализируем заголовки
        initLabels();

        //кнопки RadioButton
        initRadioButtons();

        // создадим ящик и будем укладывать в него элементы
        Box bv = new Box(BoxLayout.Y_AXIS);
        // минимальная ширина текстовых полей
        bv.add(Box.createHorizontalStrut(60));

        //инициализируем отдельные поля

        //поле2
        initPanel2();
        // уберем панель panel2 в ящик
        bv.add(panel2);
        // пустое место в 15 пикселей
        bv.add(Box.createVerticalStrut(15));

        //поле3
        initPanel3();
        // уберем панель panel3 в ящик
        bv.add(panel3);
        bv.add(Box.createVerticalStrut(15));

        //поле4
        initPanel4();
        // уберем панель panel4 в ящик
        bv.add(panel4);

        // устанавливаем положение и добавляем ящик на main_panel
        add(bv, BorderLayout.WEST);
        main_panel.add(bv);

        //установим размеры контейнера
        container.setMinimumSize(new Dimension(830, 600));

        //установим размеры для панелей и добавим их в контейнер
        container.add(BorderLayout.WEST, panel);
        container.add(BorderLayout.CENTER, main_panel);

        // рамка вокруг текстового поля
        jta.setBorder(BorderFactory.createLineBorder(Color.black));
        //сделаем нередактируемыми поля для вывода задания и результатов работы программ
        jta.setEditable(false);
        jta2.setEditable(false);

        // сделаем видимой фрейм
        super.setVisible(true);
        // запретим изменение параметров окна
        super.setResizable(false);
        super.toFront();
    }


    private void initPanel2() {
        //добавим надпись на панель panel2
        panel2.add(label1);

        //установим размеры и цвет рамки scroll-панели и добавим ее на панель panel2
        scroll2.setPreferredSize(new Dimension(610, 165));
        scroll2.setBorder(BorderFactory.createLineBorder(Color.black));
        scroll2.setAlignmentX(LEFT_ALIGNMENT);
        panel2.add(scroll2);
    }

    private void initPanel3() {
        //добавим надпись на панель panel3
        panel3.add(label2);

        // установим цвет рамки и размеры поля ввода параметров
        input.setPreferredSize(new Dimension(610, 30));
        input.setBorder(BorderFactory.createLineBorder(Color.black));
        input.setAlignmentX(LEFT_ALIGNMENT);
        input.setDisabledTextColor(Color.GRAY);

        //добавим надпись на поле ввода на панель panel3
        panel3.add(input);

        //зададим размеры кнопкам
        initButtons();

        //сделаем кнопки невидимыми
        setInvisibleButtons();

        // добавим кнопки на панель panel3
        panel3.add(button1);
        panel3.add(button2);
        panel3.add(button3);
        panel3.add(button4);
    }

    private void initPanel4() {
        //добавим надпись на поле ввода на панель panel4
        panel4.add(label3);

        // установим цвет рамки и размеры панели panel4
        panel4.setBorder(BorderFactory.createLineBorder(Color.black));
        panel4.setPreferredSize(new Dimension(60, 210));

        //установим размеры и цвет рамки scroll-панели и добавим ее на панель panel4
        scroll.setPreferredSize(new Dimension(610, 175));
        scroll.setBorder(BorderFactory.createLineBorder(Color.black));
        scroll.setAlignmentX(LEFT_ALIGNMENT);
        panel4.add(scroll);
    }

    static String[] getArrayArgumentsFromString(String string) {
        return string.split(" ");
    }

    private void initButtons() {
        button1 = new JButton("OK");
        button2 = new JButton("OK");
        button3 = new JButton("OK");
        button4 = new JButton("OK");

        button1.setPreferredSize(new Dimension(90, 30));
        button2.setPreferredSize(new Dimension(90, 30));
        button3.setPreferredSize(new Dimension(90, 30));
        button4.setPreferredSize(new Dimension(90, 30));

        setInvisibleButtons();
    }

    private void initPanels() {
        main_panel = new JPanel();
        panel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        //сделаем видимой главную панель
        main_panel.setVisible(true);
        //добавим панели на фрейм
        super.add(main_panel);
        super.add(panel);

        main_panel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("")));
        // выбираем компоновщик
        main_panel.setLayout(new BorderLayout());

        // установим рамку
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        // выбираем компоновщик
        panel.setLayout(new GridBagLayout());

        // установим цвет рамки и размеры панели panel2
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        panel2.setPreferredSize(new Dimension(60, 200));

        // установим цвет рамки и размеры панели panel3
        panel3.setBorder(BorderFactory.createLineBorder(Color.black));
        panel3.setPreferredSize(new Dimension(60, 100));
    }

    private void initLabels() {
        label1 = new JLabel("Описание задания:");
        label2 = new JLabel("Введите аргументы:");
        label3 = new JLabel("Результат работы программы:");

        //раскрасим надписи
        label1.setForeground(Color.BLUE);
        label2.setForeground(Color.RED);
        label3.setForeground(Color.BLUE);
    }

    private void initRadioButtons(){
        lab1 = new JRadioButton("Лабораторная работа №1");
        lab2 = new JRadioButton("Лабораторная работа №2");
        lab3 = new JRadioButton("Лабораторная работа №3");
        lab4 = new JRadioButton("Лабораторная работа №4");

        //раскрасим надписи к radiobutton
        lab1.setForeground(Color.BLUE);
        lab2.setForeground(Color.BLUE);
        lab3.setForeground(Color.BLUE);
        lab4.setForeground(Color.BLUE);

        //добавим listener к radiobutton
        lab1.addActionListener(listener);
        lab2.addActionListener(listener);
        lab3.addActionListener(listener);
        lab4.addActionListener(listener);

        //создадим группу для radiobutton
        ButtonGroup group = new ButtonGroup();

        // Добавим radiobutton в группу и на панель
        group.add(lab1);
        group.add(lab2);
        group.add(lab3);
        group.add(lab4);

        //установим параметры
        GridBagConstraints gbs = new GridBagConstraints();
        gbs.insets = new Insets(25, 0, 0, 0);
        gbs.gridx = 0;
        gbs.gridy = GridBagConstraints.RELATIVE;
        gbs.anchor = GridBagConstraints.WEST;

        //добавим RadioButtons к panel
        panel.add(lab1, gbs);
        panel.add(lab2, gbs);
        panel.add(lab3, gbs);
        panel.add(lab4, gbs);
    }

    protected void setTextToTaskTextField(String text) {
        jta2.setText(text);
        jta2.setCaretPosition(0);
    }

    private void setVisibleButton(int visibleButton) {
        setInvisibleButtons();
        switch (visibleButton) {
            case 1:
                button1.setVisible(true);
                input.setEnabled(true);
                label2.setForeground(Color.RED);
                break;
            case 2:
                button2.setVisible(true);
                input.setEnabled(false);
                label2.setForeground(Color.GRAY);
                break;
            case 3:
                button3.setVisible(true);
                input.setEnabled(true);
                label2.setForeground(Color.RED);
                break;
            case 4:
                button4.setVisible(true);
                input.setEnabled(true);
                label2.setForeground(Color.RED);
                break;
            default:
                break;
        }

    }

    private void setInvisibleButtons() {
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);
    }


    private void setEmptyFields() {
        jta2.setText("");
        input.setText("");
        jta.setText("");
    }
}
