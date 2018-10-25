package Functions.basic;

import Functions.*;

/**
 * Created by Алена on 06.10.2018.
 */
public abstract class TrigonometricFunction implements Function {

    public double getLeftDomainBorder() {
        return -Double.MAX_VALUE;
    }

    public double getRightDomainBorder() {
        return Double.MAX_VALUE;
    }

    public abstract double getFunctionValue(double x);
}
