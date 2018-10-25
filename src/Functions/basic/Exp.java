package Functions.basic;

import Functions.*;

/**
 * Created by Алена on 06.10.2018.
 */
public class Exp implements Function {
    public double getLeftDomainBorder(){
        return Double.MIN_EXPONENT;
    }
    public double getRightDomainBorder(){
        return Double.MAX_EXPONENT;
    }
    public double getFunctionValue(double x){
        if(x<getLeftDomainBorder() || x>getRightDomainBorder()) throw  new IllegalArgumentException();
        return Math.exp(x);
    }
}
