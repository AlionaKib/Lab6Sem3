package Functions.basic;

/**
 * Created by Алена on 06.10.2018.
 */
public class Sin extends  TrigonometricFunction {

    public double getFunctionValue(double x) {
        if(x<getLeftDomainBorder() || x>getRightDomainBorder()) throw  new IllegalArgumentException();
        return Math.sin(x);
    }
}
