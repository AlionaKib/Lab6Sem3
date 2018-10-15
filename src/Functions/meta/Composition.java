package Functions.meta;

import Functions.Function;

public class Composition implements Function {
    private Function function1;
    private Function function2;

    public Composition(Function whatCompose, Function whereCompose) {
        this.function1 = whatCompose;
        this.function2 = whereCompose;
    }

    public double getLeftDomainBorder(){
        return this.function1.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return this.function1.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return this.function2.getFunctionValue(this.function1.getFunctionValue(x));
    }
}
