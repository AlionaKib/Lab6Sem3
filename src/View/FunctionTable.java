package View;

import Functions.ArrayTabulatedFunction;
import Functions.basic.Exp;
import Functions.basic.Log;
import Functions.meta.Composition;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;

import static Functions.TabulatedFunctions.tabulate;

public class FunctionTable extends JFrame {
    private JMenuBar menuBar;
    private JPanel contentPane;
    private JTable tableFunction;
    private JScrollPane scrollPaneTable;
    private JButton addPointButton;
    private JButton deletePointButton;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JLabel newPointX;
    private JLabel newPointY;
    private FunctionParameters functionParameters;
    private FunctionDocument functionDocument;
    private JFileChooser fileChooser;

    public FunctionTable() {
        contentPane = new JPanel();
        setContentPane(contentPane);
        setResizable(true);
        GridBagLayout gbl = new GridBagLayout();
        contentPane.setLayout(gbl);
        functionParameters = new FunctionParameters();
        fileChooser = new JFileChooser();
        functionDocument = new FunctionDocument();
        functionDocument.newFunction(0,2,2);
        FunctionTableModel tabelFunctionModel = new FunctionTableModel( functionDocument, this);
        tableFunction = new JTable(tabelFunctionModel);
        tableFunction.setPreferredScrollableViewportSize(new Dimension(500,100));
        scrollPaneTable = new JScrollPane(tableFunction);
        //getContentPane().add(tableFunction);
        //contentPane.add(scrollPaneTable);
        newPointX = new JLabel("New point x");
        newPointY = new JLabel("New point y");
        textFieldX = new JTextField();
        textFieldX.setPreferredSize(new Dimension(300,20));
        textFieldY = new JTextField();
        textFieldY.setPreferredSize(new Dimension(300,20));
        addPointButton = new JButton("Add point");
        deletePointButton = new JButton("Delete point");
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1.0;
        c.weighty = 100.0;
        gbl.setConstraints(scrollPaneTable,c);
        contentPane.add(scrollPaneTable);
        /*gbl.setConstraints(tableFunction,c);
        contentPane.add(tableFunction);*/
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        gbl.setConstraints(newPointX,c);
        contentPane.add(newPointX);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 100.0;
        gbl.setConstraints(textFieldX,c);
        contentPane.add(textFieldX);
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1.0;
        gbl.setConstraints(addPointButton,c);
        contentPane.add(addPointButton);
        c.gridx = 0;
        c.gridy = 2;
        gbl.setConstraints(newPointY,c);
        contentPane.add(newPointY);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 100.0;
        gbl.setConstraints(textFieldY,c);
        contentPane.add(textFieldY);
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 1.0;
        gbl.setConstraints(deletePointButton,c);
        contentPane.add(deletePointButton);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        menuBar = new JMenuBar();
        menuBar.add(createMenuItems("File", "New", "Save as", "Save", "Open", "Exit"));
        menuBar.add(createMenuItems("Tabulate", "sin(x)", "cos(x)", "log(x)"));
        setJMenuBar(menuBar);


        menuBar.getMenu(0).getItem(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*functionDocument.newFunction(1,4,2);
                tabelFunctionModel.fireTableDataChanged();
                System.out.println(tableFunction.getRowCount());*/
                if(functionParameters.showDialog() == FunctionParameters.OK) {
                    //System.out.println("function was changed");
                    functionDocument.newFunction(functionParameters.getLeftDomainBorder(),
                            functionParameters.getRightDomainBorder(), functionParameters.getPointsCount());
                    tabelFunctionModel.fireTableDataChanged();
                    /*scrollPaneTable.repaint();
                    //tableFunction.revalidate();
                    tableFunction.repaint();
                    scrollPaneTable.repaint();
                    System.out.println("do something");*/
                }
                }
            });


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
