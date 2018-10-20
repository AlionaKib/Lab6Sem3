package View;

import Functions.InappropriateFunctionPointException;
import Functions.TabulatedFunction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FunctionTableModel extends DefaultTableModel {
    private TabulatedFunction function;
    private Component component;

    public FunctionTableModel(TabulatedFunction function, Component component){
        super();
        this.function = function;
        this.component = component;
    }

    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        if (column!=2 && column !=1)throw  new IndexOutOfBoundsException();
        if(column==1) return "x";
        return "y";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex!=0 && columnIndex !=1)throw  new IndexOutOfBoundsException();
        return  Double.class;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if(row<0 || column<0) throw new IndexOutOfBoundsException();
        if(getRowCount()<row || getColumnCount()<column) return false;
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (column!=2 && column !=1)throw  new IndexOutOfBoundsException();
        if(column==1) return this.function.getPointX(row);
        return this.function.getPointY(row);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        try {
            if (column != 2 && column != 1) throw new IndexOutOfBoundsException();
            if (column == 1) this.function.setPointX(row, (double) aValue);
            this.function.setPointY(row, (double) aValue);
        }catch(InappropriateFunctionPointException e){
            JOptionPane.showMessageDialog(this.component, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
