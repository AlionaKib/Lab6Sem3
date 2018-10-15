package Functions.meta;

import Functions.Function;

/**
 * Created by Алена on 06.10.2018.
 */
public class Scale implements Function {
    private Function function;
    private double scaleX;
    private double scaleY;

    public Scale(Function function1, double scaleX, double scaleY) {
        this.function = function;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public double getLeftDomainBorder(){
        if(scaleX>=0)
            return this.function.getLeftDomainBorder() + (this.function.getRightDomainBorder()-this.function.getLeftDomainBorder())*(1-scaleX)/2;
        return -(this.function.getRightDomainBorder() - (this.function.getRightDomainBorder()-this.function.getLeftDomainBorder())*(1-scaleX)/2);
    }

    public double getRightDomainBorder() {
        if(scaleX>=0)
            return this.function.getRightDomainBorder() - (this.function.getRightDomainBorder()-this.function.getLeftDomainBorder())*(1-scaleX)/2;
        return -(this.function.getLeftDomainBorder() + (this.function.getRightDomainBorder()-this.function.getLeftDomainBorder())*(1-scaleX)/2);

    }

    public double getFunctionValue(double x) {
        return this.function.getFunctionValue(x)*this.scaleY;
    }
}
