package Functions;

import Functions.meta.*;

public class Functions {
    private Functions(){}

    public static Function shift(Function f, double shiftX, double shiftY){
        return new Shift(f, shiftX, shiftY);
    }

    public static Function scale(Function f, double scaleX, double scaleY){
        return new Scale(f, scaleX,scaleY);
    }

    public static Function power(Function f, double power){
        return new Power(f, power);
    }

    public static Function sum(Function f1, Function f2){
        return new Sum(f1, f2);
    }

    public static Function mult(Function f1, Function f2){
        return new Mult(f1,f2);
    }

    public static Function composition(Function f1, Function f2){
        return new Composition(f1, f2);
    }

    public static double integrate(Function f, double leftBorder, double rightBorder, double interval){
        if(leftBorder<f.getLeftDomainBorder() || rightBorder>f.getRightDomainBorder())
            throw new FunctionPointIndexOutOfBoundsException();
        double integral = 0;
        while((leftBorder+interval)<rightBorder){
            integral += (f.getFunctionValue(leftBorder+interval) + f.getFunctionValue(leftBorder))*interval/2;
            leftBorder += interval;
        }
        integral += (f.getFunctionValue(leftBorder) + f.getFunctionValue(rightBorder))*(rightBorder-leftBorder)/2;
        return integral;
    }
}
