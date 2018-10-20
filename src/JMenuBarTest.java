import javax.swing.*;
import java.awt.event.KeyEvent;

public class JMenuBarTest extends JFrame
{
    private static final long serialVersionUID = 1L;
    //--------------------------------------------------------
    /**
     * Функция создания выпадающего меню
     * @param items описание меню
     * @return JMenu выпадающее меню
     */
    private JMenu createMenuItems(String ... items) {
        JMenu menu = new JMenu(items[0]);
        for (int i = 1; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            menu.add(item);
        }
        return menu;
    }
    //--------------------------------------------------------
    /**
     * Функция создания выпадающего меню с вложенными подпунктами
     * @return
     */
    private JMenu createSubmenus()
    {
        JMenu text = new JMenu("Text");
        // и несколько вложенных меню
        JMenu style = new JMenu("Style");
        JMenuItem bold = new JMenuItem("Fat");
        JMenuItem italic = new JMenuItem("Cursive");
        JMenu font = new JMenu("Font");
        JMenuItem arial = new JMenuItem("Arial");
        JMenuItem times = new JMenuItem("Times");
        font.add(arial); font.add(times);
        // размещаем все в нужном порядке
        style.add(bold);
        style.add(italic);
        style.addSeparator();
        style.add(font);
        text.add(style);
        return text;
    }
    //--------------------------------------------------------
    public JMenuBarTest() {
        super("Systen menu");
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        // создаем строку главного меню
        JMenuBar menuBar = new JMenuBar();
        // Создание меню "Файл"
        menuBar.add(createMenuItems("File", "New", "Save as", "Save", "Open", "Exit"));
        menuBar.add(createMenuItems("Tabulate", "sin(x)", "cos(x)", "log(x)"));
        // JMenuBar использует блочное расположение (заполнитель вполне уместен)
        //menuBar.add(Box.createHorizontalGlue());
        // Разместим в строке меню не выпадающее меню, а надпись со значком
        //JLabel exit = new JLabel(new ImageIcon("images/exit.png"));
        //exit.setText("Выход");
        //exit.setBorder(BorderFactory.createEtchedBorder());
        //menuBar.add(exit);

        // поместим меню в наше окно
        setJMenuBar(menuBar);
        // выводим окно на экран
        setSize(300, 200);
        setVisible(true);
    }
    //--------------------------------------------------------
   /* public static void main(String[] args)
    {
        // Подключение украшений для окон
        //JFrame.setDefaultLookAndFeelDecorated(true);
        new JMenuBarTest();
    }*/
}