package View;

import Functions.ArrayTabulatedFunction;
import Functions.Function;
import Functions.FunctionPoint;
import Functions.basic.Exp;
import Functions.basic.Log;
import Functions.meta.Composition;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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
    private FunctionLoader functionLoader;

    public FunctionTable() {
        contentPane = new JPanel();
        setContentPane(contentPane);
        setResizable(true);
        GridBagLayout gbl = new GridBagLayout();
        contentPane.setLayout(gbl);
        functionParameters = new FunctionParameters();
        fileChooser = new JFileChooser();
        //functionLoader = new FunctionLoader();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text document", "txt");
        fileChooser.setFileFilter(filter);
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Class files", "class");
        fileChooser.setFileFilter(filter1);
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
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        menuBar = new JMenuBar();
        menuBar.add(createMenuItems("File", "New", "Save as", "Save", "Open", "Exit"));
        menuBar.add(createMenuItems("Tabulate", "Load"));
        setJMenuBar(menuBar);

        menuBar.getMenu(1). getItem(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isLoad = true;
                System.out.println("Clicked tabulate");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = (int) fileChooser.showDialog(FunctionTable.this, "Open");
                if(result == JFileChooser.APPROVE_OPTION){
                    try {
                        functionLoader = new FunctionLoader();
                        Function loadedFunction = (Function)functionLoader.getClassFromFile(fileChooser.getSelectedFile()).newInstance();
                        if(functionParameters.showDialog() == FunctionParameters.OK) {
                            functionDocument.tabulateFunction(loadedFunction, functionParameters.getLeftDomainBorder(),
                                    functionParameters.getRightDomainBorder(), functionParameters.getPointsCount());
                            tabelFunctionModel.fireTableDataChanged();
                            isLoad = true;
                        }
                    }  catch (IllegalAccessException e1) {
                        JOptionPane.showMessageDialog(FunctionTable.this, "Access error", "Error", JOptionPane.ERROR_MESSAGE);
                        isLoad = false;
                    } catch (InstantiationException e1) {
                        JOptionPane.showMessageDialog(FunctionTable.this, "Wrong function file class", "Error", JOptionPane.ERROR_MESSAGE);
                        isLoad = false;
                    }  /*catch (ClassNotFoundException e1) {
                        JOptionPane.showMessageDialog(FunctionTable.this, "Class not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }*/
                    if(isLoad) JOptionPane.showMessageDialog(FunctionTable.this, "Function tabulated", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        menuBar.getMenu(0).getItem(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(functionParameters.showDialog() == FunctionParameters.OK) {
                    functionDocument.newFunction(functionParameters.getLeftDomainBorder(),
                            functionParameters.getRightDomainBorder(), functionParameters.getPointsCount());
                    tabelFunctionModel.fireTableDataChanged();
                }
                }
            });

        menuBar.getMenu(0).getItem(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Save file");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = (int) fileChooser.showSaveDialog(FunctionTable.this);
                if(result == JFileChooser.APPROVE_OPTION){
                    try {
                        functionDocument.saveFunctionAs(fileChooser.getSelectedFile());
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(FunctionTable.this, "Can't write function into file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(FunctionTable.this, "Function saved", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        menuBar.getMenu(0).getItem(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(functionDocument.fileNameAssigned){
                    try {
                        functionDocument.saveFunction();
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(FunctionTable.this, "Can't write function into file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                JOptionPane.showMessageDialog(FunctionTable.this, "File do not exist. Please, click save as and choose file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        menuBar.getMenu(0).getItem(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Open file");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = (int) fileChooser.showDialog(FunctionTable.this, "Open");
                if(result == JFileChooser.APPROVE_OPTION){
                    try {
                        functionDocument.loadFunction(fileChooser.getSelectedFile());
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(FunctionTable.this, "Can't load function from file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    tabelFunctionModel.fireTableDataChanged();
                    JOptionPane.showMessageDialog(FunctionTable.this, "Function loaded", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        menuBar.getMenu(0).getItem(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("do smth");
                if(functionDocument.modified) {
                    System.out.println("Not changed");
                    int result = JOptionPane.showConfirmDialog(FunctionTable.this, "Exit without saving?", "Exit", JOptionPane.WARNING_MESSAGE);
                    if(result == JOptionPane.OK_OPTION) {
                        FunctionTable.this.dispose();
                        System.exit(0);
                    }
                } else {
                    FunctionTable.this.dispose();
                    System.exit(0);
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(functionDocument.modified) {
                    System.out.println("Not changed");
                    int result = JOptionPane.showConfirmDialog(FunctionTable.this, "Exit without saving?", "Exit", JOptionPane.WARNING_MESSAGE);
                    if(result == JOptionPane.OK_OPTION) {
                        FunctionTable.this.dispose();
                        System.exit(0);
                    }
                } else {
                    FunctionTable.this.dispose();
                    System.exit(0);
                }
            }
        });

        deletePointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    functionDocument.deletePoint(tableFunction.getSelectedRow());
                    tabelFunctionModel.fireTableDataChanged();
                }catch(Throwable e1){
                    JOptionPane.showMessageDialog(FunctionTable.this, "Can't delete point", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    functionDocument.addPoint(new FunctionPoint(Double.parseDouble(textFieldX.getText()),Double.parseDouble(textFieldY.getText())));
                    tabelFunctionModel.fireTableDataChanged();
                }catch(Throwable e1){
                    JOptionPane.showMessageDialog(FunctionTable.this, "Can't add point into function", "Error", JOptionPane.ERROR_MESSAGE);
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
