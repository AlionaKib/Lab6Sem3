package Functions.meta;

import Functions.Function;

public class Shift implements Function {
    private Function function;
    private double shiftX;
    private double shiftY;

    public Shift(Function function1, double shiftX, double shiftY) {
        this.function = function;
        this.shiftX = shiftX;
        this.shiftY = shiftY;
    }

    public double getLeftDomainBorder(){
        return this.function.getLeftDomainBorder()+this.shiftX;
    }

    public double getRightDomainBorder() {
        return this.function.getRightDomainBorder()+this.shiftX;

    }

    public double getFunctionValue(double x) {
        return this.function.getFunctionValue(x)+this.shiftY;
    }
}
