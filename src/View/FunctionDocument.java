package View;

import Functions.Function;
import Functions.FunctionPoint;
import Functions.InappropriateFunctionPointException;
import Functions.TabulatedFunction;

import java.io.File;

public class FunctionDocument implements TabulatedFunction {
    private Function currentFunction;
    private File currentFile;

    @Override
    public int getPointCount() {
        return 0;
    }

    @Override
    public FunctionPoint getPoint(int index) {
        return null;
    }

    @Override
    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {

    }

    @Override
    public double getPointX(int index) {
        return 0;
    }

    @Override
    public void setPointX(int index, double x) throws InappropriateFunctionPointException {

    }

    @Override
    public double getPointY(int index) {
        return 0;
    }

    @Override
    public void setPointY(int index, double y) {

    }

    @Override
    public void deletePoint(int index) {

    }

    @Override
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public double getLeftDomainBorder() {
        return 0;
    }

    @Override
    public double getRightDomainBorder() {
        return 0;
    }

    @Override
    public double getFunctionValue(double x) {
        return 0;
    }
}
