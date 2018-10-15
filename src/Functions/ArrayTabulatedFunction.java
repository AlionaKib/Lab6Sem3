package Functions;

import java.io.*;

/**
 * Created by Алена on 15.09.2018.
 */
public class ArrayTabulatedFunction implements TabulatedFunction, /*Serializable,*/ Externalizable {
    private FunctionPoint[] functionPoints;

    public ArrayTabulatedFunction(){}

    public ArrayTabulatedFunction(FunctionPoint[] functionPoints){
        if(functionPoints.length<2) throw new IllegalArgumentException();
        this.functionPoints = new FunctionPoint[functionPoints.length];
        for(int i = 0; i<functionPoints.length-1; ++i){
            if(functionPoints[i].getX()>functionPoints[i+1].getX()) throw new IllegalArgumentException();
            this.functionPoints[i] = functionPoints[i];
        }
        this.functionPoints[functionPoints.length-1] = functionPoints[functionPoints.length-1];
    }

    public ArrayTabulatedFunction(double leftX, double rigxtX, int pointsCount){
        if(leftX >= rigxtX || pointsCount < 2)
            throw new IllegalArgumentException();
        this.functionPoints = new FunctionPoint[pointsCount];
        FunctionPoint point = new FunctionPoint(leftX, 0);
        for (int i=0; i<functionPoints.length-1; ++i){
            this.functionPoints[i] = point;
            point = new FunctionPoint(functionPoints[i].getX()+(rigxtX-leftX)/(pointsCount-1),0);
        }
        this.functionPoints[functionPoints.length-1] = new FunctionPoint(rigxtX, 0);
    }

    public ArrayTabulatedFunction(double leftX, double rightX, double[] value){
        this(leftX, rightX, value.length);
        for(int i=0; i<value.length; ++i){
            functionPoints[i].setY(value[i]);
        }
    }

    public double getLeftDomainBorder(){
        return this.functionPoints[0].getX();
    }

    public double getRightDomainBorder(){
        return this.functionPoints[functionPoints.length-1].getX();
    }

    public double getFunctionValue(double x){
        int k=0;
        if(x>getLeftDomainBorder() && x < getRightDomainBorder()){
            for (int i=0; i<getPointCount(); ++i, ++k){
                if(functionPoints[k].getX()>x) break;
            }
            return (x - functionPoints[k-1].getX())*(functionPoints[k].getY()-functionPoints[k-1].getY())/
                    (functionPoints[k].getX()-functionPoints[k-1].getX())+functionPoints[k-1].getY();
        }
        if(Math.abs(x-getLeftDomainBorder())<Double.MIN_VALUE) return getPointY(0);
        if(Math.abs(x-getRightDomainBorder())<Double.MIN_VALUE)return getPointY(getPointCount()-1);
        return Double.NaN;
    }

    public int getPointCount(){
        return functionPoints.length;
    }

    public FunctionPoint getPoint(int index){
        if(index < 0 || index>=functionPoints.length)
            throw new FunctionPointIndexOutOfBoundsException();
        return this.functionPoints[index];
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException{
        setPointX(index, point.getX());
        this.functionPoints[index] = point;
    }

    public double getPointX(int index) {
        if(index < 0 || index>=functionPoints.length)
            throw new FunctionPointIndexOutOfBoundsException();
        return this.functionPoints[index].getX();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException{
        if(index < 0 || index>=functionPoints.length)
            throw new FunctionPointIndexOutOfBoundsException();
        if(index!=0 && index!=getPointCount()-1 && (x < functionPoints[index-1].getX()||
                x > functionPoints[index+1].getX()))
            throw new InappropriateFunctionPointException();
        this.functionPoints[index].setX(x);
    }

    public double getPointY(int index){
        if(index < 0 || index>=functionPoints.length)
            throw new FunctionPointIndexOutOfBoundsException();
        return this.functionPoints[index].getY();
    }

    public void setPointY(int index, double y){
        if(index < 0 || index>=functionPoints.length)
            throw new FunctionPointIndexOutOfBoundsException();
        functionPoints[index].setY(y);
    }

    public void deletePoint(int index){
        if(index < 0 || index >= functionPoints.length)
            throw new FunctionPointIndexOutOfBoundsException();
        if (functionPoints.length <3)
            throw new IllegalStateException();
        FunctionPoint[] temp = new FunctionPoint[functionPoints.length-1];
        System.arraycopy(functionPoints, 0,temp,0,index);
        System.arraycopy(functionPoints, index+1,temp,index,functionPoints.length-index-1);
        functionPoints = temp;
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException{
        if(point.getX()<getRightDomainBorder() && point.getX()>getLeftDomainBorder())
            for(int i = 0; i<functionPoints.length; ++i) {
                if (Math.abs(functionPoints[i].getX() - point.getX()) < Double.MIN_VALUE)
                    throw new InappropriateFunctionPointException();
            }
        FunctionPoint[] temp = new FunctionPoint[functionPoints.length+1];
        System.arraycopy(functionPoints, 0,temp,0,functionPoints.length);
        temp[temp.length-1] = point;
        functionPoints = temp;
        sortPoints();
    }

    private void sortPoints(){
        FunctionPoint point;
        for(int i=0; i < functionPoints.length; ++i)
            for (int j=0; j < functionPoints.length-i-1; ++j)
                if(functionPoints[j].getX()>functionPoints[j+1].getX()){
                    point = functionPoints[j];
                    functionPoints[j] = functionPoints[j+1];
                    functionPoints[j+1] = point;
                }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getPointCount());
        for(int i=0; i < getPointCount(); ++i){
            out.writeObject(functionPoints[i]);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.functionPoints = new FunctionPoint[in.readInt()];
        for(int i=0; i<getPointCount(); ++i){
            functionPoints[i] = (FunctionPoint)in.readObject();
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append('{');
        for(int i=0; i<getPointCount(); ++i) {
            sb.append(functionPoints[i].toString());
            if (i == getPointCount()-1) break;
            sb.append(", ");
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TabulatedFunction))
            return false;
        TabulatedFunction function = (TabulatedFunction) obj;
        if(getPointCount()!=function.getPointCount())
            return false;
        for (int i=0; i < getPointCount(); i++)
            if (!getPoint(i).equals(function.getPoint(i)))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 37*result + getPointCount();
        for(int i = 0; i< getPointCount(); ++i){
            result = 37*result + functionPoints[i].hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        TabulatedFunction tf;
        FunctionPoint fp[] = new FunctionPoint[getPointCount()];
        for(int i = 0; i<getPointCount(); ++i){
            fp[i] = new FunctionPoint(getPointX(i), getPointY(i));
        }
        tf = new ArrayTabulatedFunction(fp);
        return tf;
    }
}
