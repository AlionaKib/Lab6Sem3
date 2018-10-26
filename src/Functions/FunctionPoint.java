package Functions;

import java.io.*;

/**
 * Created by Алена on 15.09.2018.
 */
public class FunctionPoint implements Serializable/*, Externalizable */{
    private double x;
    private double y;

    public FunctionPoint (double x, double y){
        this.x = x;
        this.y = y;
    }

    public FunctionPoint(FunctionPoint o){
        this.x = o.x;
        this.y = o.y;
    }

    public FunctionPoint(){
        this.x = 0;
        this.y = 0;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

   /* @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(x);
        out.writeDouble(y);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.x = in.readDouble();
        this.y = in.readDouble();
    }*/

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append('(').append(this.x).append(", ").append(this.y).append(')');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(getClass()!=obj.getClass())
            return false;
        FunctionPoint point = (FunctionPoint) obj;
        return (Math.abs(this.x-point.getX())<Double.MIN_VALUE && Math.abs(this.y-point.getY())<Double.MIN_VALUE);

    }

    @Override
    public int hashCode() {
        int result = 1;
        long c1 = Double.doubleToLongBits(this.x);
        long c2 = Double.doubleToLongBits(this.y);
        return 37*(37*result + (int)((c1)^((c1)>>>32))) + (int)((c2)^((c2)>>>32));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new FunctionPoint(getX(), getY());
    }
}
