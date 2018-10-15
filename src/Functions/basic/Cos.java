package Functions.basic;

/**
 * Created by Алена on 06.10.2018.
 */
public class Cos extends TrigonometricFunction {

    public double getFunctionValue(double x) {
        if(x<getLeftDomainBorder() || x>getRightDomainBorder()) throw  new IllegalArgumentException();
        return Math.cos(x);
    }
}
