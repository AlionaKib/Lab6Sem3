package View;

import Functions.ArrayTabulatedFunction;
import Functions.basic.Exp;
import Functions.basic.Log;
import Functions.meta.Composition;

import javax.swing.*;
import java.awt.event.*;

import static Functions.TabulatedFunctions.tabulate;

public class FunctionTable extends JFrame {
    private JMenuBar menuBar;
    private JPanel contentPane;
    private JButton addPointButton;
    private JButton deletePointButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTable tableFunction;
    private FunctionParameters functionParameters;
    private FunctionDocument functionDocument;
    private JFileChooser fileChooser;

    public FunctionTable() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(true);
        functionParameters = new FunctionParameters();
        fileChooser = new JFileChooser();
        functionDocument = new FunctionDocument();
        functionDocument.newFunction(0,9,10);
        //System.out.println(functionDocument.toString());
        tableFunction = new JTable(new FunctionTableModel(functionDocument, this));
        this.menuBar = new JMenuBar();
        menuBar.add(createMenuItems("File", "New", "Save as", "Save", "Open", "Exit"));
        menuBar.add(createMenuItems("Tabulate", "sin(x)", "cos(x)", "log(x)"));
        setJMenuBar(menuBar);
        setSize(500, 300);
    }

    private JMenu createMenuItems(String ... items) {
        JMenu menu = new JMenu(items[0]);
        for (int i = 1; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            menu.add(item);
        }
        return menu;
    }

    public static void main(String[] args) {
        FunctionTable dialog = new FunctionTable();
        dialog.pack();
        dialog.setVisible(true);
    }
}
