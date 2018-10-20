package View;

import Functions.*;

import java.io.*;

import static Functions.TabulatedFunctions.readTabulatedFunction;
import static Functions.TabulatedFunctions.tabulate;
import static Functions.TabulatedFunctions.writeTabulatedFunction;

public class FunctionDocument implements TabulatedFunction {
    private TabulatedFunction currentFunction;
    private File currentFile;
    private boolean modified = false;
    private boolean fileNameAssigned = false;

    public FunctionDocument(){
        currentFile = null;
        currentFunction = null;
    }

    public FunctionDocument(TabulatedFunction function, String fileName){
        this.currentFunction = function;
        currentFile = new File(fileName);
        fileNameAssigned = true;
    }

    public void newFunction(double leftX, double rightX, int pointsCount){
        currentFunction = new ArrayTabulatedFunction(leftX, rightX, pointsCount);
        modified = true;
        System.out.println(currentFunction.toString());
    }

    public void saveFunction(){
        if(fileNameAssigned) return;
        FileWriter fwr;
        try{
            fwr = new FileWriter(currentFile);
            writeTabulatedFunction(currentFunction, fwr);
            fwr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        modified = false;
    }

    public void saveFunctionAs(String fileName){
        if(currentFunction == null) return;
        currentFile = new File(fileName);
        saveFunction();
        fileNameAssigned = true;
    }

    public void loadFunction(String fileName){
        currentFile = new File(fileName);
        FileReader frd;
        try{
            frd = new FileReader(currentFile);
            currentFunction = readTabulatedFunction(frd);
            frd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        modified = false;
        fileNameAssigned = true;
    }

    public void tabulateFunction(Function function, double leftX, double rightX, int pointsCount){
        currentFunction = tabulate(function, leftX, rightX, pointsCount);
    }

    @Override
    public int getPointCount() {
        return this.currentFunction.getPointCount();
    }

    @Override
    public FunctionPoint getPoint(int index) {
        return this.currentFunction.getPoint(index);
    }

    @Override
    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {
        this.currentFunction.setPoint(index, point);
        modified = true;
    }

    @Override
    public double getPointX(int index) {
        return this.currentFunction.getPointX(index);
    }

    @Override
    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        this.currentFunction.setPointX(index, x);
        modified = true;
    }

    @Override
    public double getPointY(int index) {
        return this.currentFunction.getPointY(index);
    }

    @Override
    public void setPointY(int index, double y) {
        this.currentFunction.setPointY(index, y);
        modified = true;
    }

    @Override
    public void deletePoint(int index) {
        this.currentFunction.deletePoint(index);
        modified = true;
    }

    @Override
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        this.currentFunction.addPoint(point);
        modified = true;
    }


    @Override
    public double getLeftDomainBorder() {
        return this.currentFunction.getLeftDomainBorder();
    }

    @Override
    public double getRightDomainBorder() {
        return this.currentFunction.getRightDomainBorder();
    }

    @Override
    public double getFunctionValue(double x) {
        return this.currentFunction.getFunctionValue(x);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new FunctionDocument((TabulatedFunction) currentFunction.clone(), currentFile.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof FunctionDocument)) return false;
        FunctionDocument fd = (FunctionDocument) obj;
        return fd.currentFunction.equals(this.currentFunction) && fd.currentFile.equals(this.currentFile);
    }

    @Override
    public int hashCode() {
        return this.currentFunction.hashCode();
    }

    @Override
    public String toString() {
        return this.currentFunction.toString();
    }
}
