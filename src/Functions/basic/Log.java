package Functions.basic;

import Functions.Function;

/**
 * Created by Алена on 06.10.2018.
 */
public class Log implements Function {
    private double base;

    public Log(double base){
        this.base = base;
    }

    public double getLeftDomainBorder(){
        return Double.MIN_VALUE;
    }

    public double getRightDomainBorder(){
        return Double.MAX_VALUE;
    }

    public double getFunctionValue(double x){
        if(x<getLeftDomainBorder() || x>getRightDomainBorder()) throw  new IllegalArgumentException();
        return Math.log(x)/Math.log(base);
    }
}
