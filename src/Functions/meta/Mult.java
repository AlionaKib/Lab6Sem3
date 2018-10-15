package Functions.meta;

import Functions.Function;

/**
 * Created by Алена on 06.10.2018.
 */
public class Mult implements Function {
    private Function function1;
    private Function function2;

    public Mult(Function function1, Function function2) {
        if(function1.getLeftDomainBorder() > function2.getRightDomainBorder() ||
                function2.getLeftDomainBorder()> function1.getRightDomainBorder())
            throw  new IllegalArgumentException();
        this.function1 = function1;
        this.function2 = function2;
    }

    public double getLeftDomainBorder(){
        return Math.max(this.function1.getRightDomainBorder(), this.function2.getRightDomainBorder());
    }

    public double getRightDomainBorder() {
        return Math.min(this.function1.getRightDomainBorder(), this.function2.getRightDomainBorder());
    }

    public double getFunctionValue(double x) {
        return this.function1.getFunctionValue(x)*this.function2.getFunctionValue(x);
    }
}
