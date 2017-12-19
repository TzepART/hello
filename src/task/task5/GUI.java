package task.task5;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import task.task5.task1.task1;
import task.task5.task2.task2;
import task.task5.task3.task3;

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
    private JPanel main_panel = new JPanel();
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();

    //лабели
    private JLabel label1 = new JLabel("Описание задания:");
    private JLabel label2 = new JLabel("Введите аргументы:");
    private JLabel label3 = new JLabel("Результат работы программы:");

    //кнопки RadioButton
    private JRadioButton lab1 = new JRadioButton("Лабораторная работа №1");
    private JRadioButton lab2 = new JRadioButton("Лабораторная работа №2");
    private JRadioButton lab3 = new JRadioButton("Лабораторная работа №3");
    private JRadioButton lab4 = new JRadioButton("Лабораторная работа №4");

    //поле для ввода арuгументов
    private JTextField input = new JTextField();

    //поле для вывода результатов выполнения программы
    static public JTextArea jta = new JTextArea(10, 10);
    //поле для вывода заданий программ
    static public JTextArea jta2 = new JTextArea(10, 10);

    // полосы прокрутки будут отображаться всегда
    private JScrollPane scroll = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JScrollPane scroll2 = new JScrollPane(jta2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    // переменная для передачи параметров
    String args;

    // контейнер для размещения компонентов формы
    private Container container = getContentPane();

    //конструктор
    GUI() {
        // зададим размеры окна
        super.setSize(830, 585);
        //заголовок
        super.setTitle("Лабораторная работа №5");
        //сделаем видимой главную панель
        main_panel.setVisible(true);
        //добавим панели на фрейм
        super.add(main_panel);
        super.add(panel);
        //установим окно по центру экрана
        super.setLocationRelativeTo(null);

        main_panel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("")));
        // выбираем компоновщик
        main_panel.setLayout(new BorderLayout());

        // установим зеленую рамку
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        // выбираем компоновщик
        panel.setLayout(new GridBagLayout());

        //установим параметры
        GridBagConstraints gbs = new GridBagConstraints();
        gbs.insets = new Insets(25,0,0,0);
        gbs.gridx = 0;
        gbs.gridy = GridBagConstraints.RELATIVE;
        gbs.anchor = GridBagConstraints.WEST;

        //создадим группу для radiobutton
        ButtonGroup group = new ButtonGroup();

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

        // Добавим radiobutton в группу и на панель
        group.add(lab1);
        group.add(lab2);
        group.add(lab3);
        group.add(lab4);
        panel.add(lab1,gbs);
        panel.add(lab2,gbs);
        panel.add(lab3,gbs);
        panel.add(lab4,gbs);

        // создадим ящик и будем укладывать в него элементы
        Box bv = new Box(BoxLayout.Y_AXIS);

        // минимальная ширина текстовых полей
        bv.add(Box.createHorizontalStrut(60));

        //раскрасим надписи
        label1.setForeground(Color.BLUE);
        label2.setForeground(Color.RED);
        label3.setForeground(Color.BLUE);

        // рамка вокруг текстового поля
        jta.setBorder(BorderFactory.createLineBorder(Color.black));

        // установим цвет рамки и размеры панели panel2
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        panel2.setPreferredSize(new Dimension(60,200));

        //добавим надпись на панель panel2
        panel2.add(label1);

        //установим размеры и цвет рамки scroll-панели и добавим ее на панель panel2
        scroll2.setPreferredSize(new Dimension(610,165));
        scroll2.setBorder(BorderFactory.createLineBorder(Color.black));
        scroll2.setAlignmentX(LEFT_ALIGNMENT);
        panel2.add(scroll2);

        // уберем панель panel2 в ящик
        bv.add(panel2);
        // пустое место в 15 пикселей
        bv.add(Box.createVerticalStrut(15));

        // установим цвет рамки и размеры панели panel3
        panel3.setBorder(BorderFactory.createLineBorder(Color.black));
        panel3.setPreferredSize(new Dimension(60,100));

        //добавим надпись на панель panel3
        panel3.add(label2);

        // установим цвет рамки и размеры поля ввода параметров
        input.setPreferredSize(new Dimension(610,30));
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

        // уберем панель panel3 в ящик
        bv.add(panel3);
        bv.add(Box.createVerticalStrut(15));

        // установим цвет рамки и размеры панели panel4
        panel4.setBorder(BorderFactory.createLineBorder(Color.black));
        panel4.setPreferredSize(new Dimension(60,210));

        //добавим надпись на поле ввода на панель panel4
        panel4.add(label3);

        //установим размеры и цвет рамки scroll-панели и добавим ее на панель panel4
        scroll.setPreferredSize(new Dimension(610,175));
        scroll.setBorder(BorderFactory.createLineBorder(Color.black));
        scroll.setAlignmentX(LEFT_ALIGNMENT);
        panel4.add(scroll);

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

        //сделаем нередактируемыми поля для вывода задания и результатов работы программ
        jta.setEditable(false);
        jta2.setEditable(false);

        // сделаем видимой фрейм
        super.setVisible(true);
        // запретим изменение параметров окна
        super.setResizable(false);
        super.toFront();
    }


    // Listener для отслеживания переключения radiobutton
    ActionListener listener = new ActionListener() {

        private String textTask1 = "1. Приложение с 3 параметрами. Необходимо определить  могут ли быть \n" +
                "введенные параметры сторонами треугольника и вывести сообщение \n" +
                "в случае, если это невозможно. \n\n" +
                "Введите 3 аргумента через пробел в поле Введите аргументы и нажмите кнопку ОК.";

        private String textTask2 = "2. Создать приложение с 2 классами- А, B  и 2 интерфейсами I1, I2.\n"+
                "Класс А является родительским для класса B,\n"+
                " Интерфейс I1 является родительским для интерфейса I2.\n"+
                "Класс A реализует интерфейс I1.\n"+
                "Класс В реализует интерфейс I2.\n"+
                "Интерфейс I1 содержит метод i1, класс А  содержит метод а1, интерфейс I2 содержит \n"+
                "метод i2. Класс B содержит метод b1.\n"+
                "Все методы выводят строку с именем своего класса или интерфейса и именем метода.\n"+
                "Создать минимальное число объектов для выполнения всех указанных 4 методов.\n"+
                "и выполнить все эти методы. Присвоить переменным типа I1  каждый из созданных\n"+
                " объектов и еще раз выполнить все прежние методы для\n"+
                " переменной типа I1(используя приведение типов).";

        private String textTask3 = "3. Создать 2 потока разными способами. Один из этих потоков увеличивает разделенную\n"+
                "между потоками переменную на 1000, а другой на 10 . Потоки работают попеременно.\n"+
                "Цикл для каждого потока выполняется число раз заданное параметром.\n"+
                "Нужно выводить для каждого потока его имя и значение измененной переменной.\n"+
                "Выполнить задание с использованием конструкции synchronized .\n"+
                "Не использовать в этом задании флаги для синхронизации потоков,\n"+
                "а только методы wait и notify. Также не использовать любые задержки для\n"+
                "потоков после начала их работы в виде методов sleep, yield или wait c параметром.";

        private String textTask4 = "4. Создать приложение с 3 потоками для следующей задачи: 3 работника выполняют следующую работу: \r\n" +
                "1-ый копает яму, 2-ой сажает дерево, 3-ий подвязывает саженец к кольям. \r\n" +
                "Работа идет строго по очереди: пока не подвязан саженец 1 -ый рабочий отдыхает(т.е. поток находится \r\n" +
                "в состоянии ожидания), пока не вскопана яма отдыхает 2 рабочий , \r\n" +
                "и пока не посажено дерево 3 -й работник отдыхает. Число саженцев задается параметром. \r\n" +
                "Использовать ограничения из задания 3. \r\n" +
                "Выводить на дисплей номер работника и номер саженца. \n\n" +
                "Аргумент задает количество саженцев, введите его в поле Введите аргументы и нажмите кнопку ОК.";


        @Override
        public void actionPerformed(ActionEvent ae) {
            switch ( ((JRadioButton)ae.getSource()).getText() ) {
                case "Лабораторная работа №1" :
                    setEmptyFields();
                    setVisibleButton(1);
                    setTextToTaskTextField(textTask1);

                    //Listener для отслеживания нажатия кнопки button1
                    button1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                            jta.setText("");
                            String result = task1.getResult(GUI.getArrayArgumentsFromString(input.getText().trim()));
                            GUI.jta.append(result);
                        }
                    });
                    break;
                case "Лабораторная работа №2" :
                    setEmptyFields();
                    setVisibleButton(2);
                    setTextToTaskTextField(textTask2);

                    //Listener для отслеживания нажатия кнопки button2
                    button2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                            jta.setText("");
                            String result = task2.getResult();
                            GUI.jta.append(result);
                        }
                    });
                    break;
                case "Лабораторная работа №3" :
                    setEmptyFields();
                    args = null;
                    setVisibleButton(3);
                    setTextToTaskTextField(textTask3);

                    //Listener для отслеживания нажатия кнопки button3
                    button3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                            jta.setText("");
                            String[] params = GUI.getArrayArgumentsFromString(input.getText().trim());
                            task3.getResult(params,jta);
                        }
                    });
                    break;
                case "Лабораторная работа №4" :
                    setEmptyFields();
                    args = null;
                    setVisibleButton(4);
                    setTextToTaskTextField(textTask4);

                    //Listener для отслеживания нажатия кнопки button4
                    button4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                            jta.setText("");
                            args = input.getText().trim();
//                            Lab4.main(args);
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };

    static String[] getArrayArgumentsFromString(String string){
        return string.split(" ");
    }

    protected void setTextToTaskTextField(String text) {
        jta2.setText(text);
        jta2.setCaretPosition(0);
    }

    private void setVisibleButton(int visibleButton) {
        setInvisibleButtons();
        switch (visibleButton){
            case 1 :
                button1.setVisible(true);
                input.setEnabled(true);
                label2.setForeground(Color.RED);
                break;
            case 2 :
                button2.setVisible(true);
                input.setEnabled(false);
                label2.setForeground(Color.GRAY);
                break;
            case 3 :
                button3.setVisible(true);
                input.setEnabled(true);
                label2.setForeground(Color.RED);
                break;
            case 4 :
                button3.setVisible(true);
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

    private void initButtons() {
        button1 = new JButton("OK");
        button2 = new JButton("OK");
        button3 = new JButton("OK");
        button4 = new JButton("OK");

        button1.setPreferredSize(new Dimension(90,30));
        button2.setPreferredSize(new Dimension(90,30));
        button3.setPreferredSize(new Dimension(90,30));
        button4.setPreferredSize(new Dimension(90,30));

        setInvisibleButtons();
    }

}
