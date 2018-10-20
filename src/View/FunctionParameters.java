package View;

import javax.swing.*;
import java.awt.event.*;

public class FunctionParameters extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField leftDomainBorder;
    private JTextField rightDomainBorder;
    private JSpinner spinnerPointsCount;
    private static final int OK = 1;
    private static final int CANCEL = 0;
    private int dialogStatus;

    public FunctionParameters() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
        spinnerModel.setMinimum(2);
        spinnerModel.setValue(new Integer(2));
        spinnerModel.setStepSize(1);
        spinnerPointsCount.setModel(spinnerModel);

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        /*buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (getLeftDomainBorder() > getRightDomainBorder()) {
                        JOptionPane.showMessageDialog(null, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
                        leftDomainBorder.setText("");
                        rightDomainBorder.setText("");
                    }
                    else {
                        setVisible(false);
                        dialogStatus = OK;
                    }
                }catch(NumberFormatException e1){
                    e1.getStackTrace();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dialogStatus = CANCEL;
            }
        });*/

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dialogStatus = CANCEL;
            }
        });

        // call onCancel() when cross is clicked
        /*addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);*/

        setModal(true);
        setResizable(false);
        pack();
        //setVisible(true);
    }

    public int showDialog(){
        setVisible(true);
        return this.dialogStatus;
    }

    public double getLeftDomainBorder(){
        return Double.parseDouble(this.leftDomainBorder.getText());
    }

    public double getRightDomainBorder(){
        return Double.parseDouble(this.rightDomainBorder.getText());
    }

    public int getPointsCount(){
        return (int)spinnerPointsCount.getValue();
    }


   /* public static void main(String[] args)
    {
        // Подключение украшений для окон
        //JFrame.setDefaultLookAndFeelDecorated(true);
        new FunctionParameters();
    }*/

}
