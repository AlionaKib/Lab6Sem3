package Functions.meta;

import Functions.Function;

/**
 * Created by Алена on 06.10.2018.
 */
public class Power implements Function {
    private Function function;
    private double degree;

    public Power(Function function1, double degree) {
        this.function = function1;
        this.degree = degree;
    }

    public double getLeftDomainBorder(){
        return this.function.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return this.function.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return Math.pow(this.function.getFunctionValue(x),this.degree);
    }
}
