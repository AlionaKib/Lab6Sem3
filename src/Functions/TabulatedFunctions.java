package Functions;

import java.io.*;

public class TabulatedFunctions {

    private TabulatedFunctions(){}

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount){
        if(leftX<function.getLeftDomainBorder() || rightX>function.getRightDomainBorder()) throw  new IllegalArgumentException();
        TabulatedFunction func = new ArrayTabulatedFunction(leftX, rightX, pointsCount);
        for(int i=0; i<func.getPointCount(); ++i){
            func.setPointY(i,function.getFunctionValue(func.getPointX(i)));
        }
        System.out.println("lefty = " + function.getFunctionValue(func.getLeftDomainBorder()));
        return func;
    }

    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out){
        try {
            DataOutputStream out1 = new DataOutputStream(out);
            out1.writeInt(function.getPointCount());
            //System.out.println("Output " + function.getPointCount());
            for(int i=0; i<function.getPointCount(); i++) {
                out1.writeDouble(function.getPointX(i));
                out1.writeDouble(function.getPointY(i));
                //System.out.println("Output x = " + function.getPointX(i) + ", y = " + function.getPointY(i));
            }
            //System.out.println("Output done");
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static TabulatedFunction inputTabulatedFunction(InputStream in){
        TabulatedFunction tabFunc = null;
        DataInputStream in1;
        try {
            in1 = new DataInputStream(in);
            FunctionPoint points[] = new FunctionPoint[in1.readInt()];
            //System.out.println("Readed " + points.length);
            for(int i = 0; i<points.length; i++){
                points[i] = new FunctionPoint(in1.readDouble(), in1.readDouble());
                //System.out.println("Readed x = " + points[i].getX() + ", y = " + points[i].getY());
            }
            tabFunc = new LinkedListTabulatedFunction(points);
            //System.out.println("Intput done");
            return tabFunc;
        } catch(IOException e){
            System.out.println(e.getMessage()+", return null");
            return tabFunc;
        }
    }

    public static void writeTabulatedFunction(TabulatedFunction function, Writer out){
        PrintWriter p = new PrintWriter(out);
        p.print(function.getPointCount());
        p.print(' ');
        for (int i = 0; i < function.getPointCount(); i++) {
            p.print(function.getPointX(i));
            p.print(' ');
            p.print(function.getPointY(i));
            p.print(' ');
        }

        //System.out.println("Writing done");
    }

    public static TabulatedFunction readTabulatedFunction(Reader in){
        TabulatedFunction tabFunc= null;
        StreamTokenizer in1;
        int rooms;
        double area;
        try {
            in1 = new StreamTokenizer(in);
            in1.parseNumbers();
            in1.nextToken();
            FunctionPoint points[] = new FunctionPoint[(int)in1.nval];
            //System.out.println("Readed " + points.length);
            for(int i = 0; i<points.length; i++){
                in1.nextToken();
                points[i] = new FunctionPoint(in1.nval, 0);
                in1.nextToken();
                points[i].setY(in1.nval);
                //System.out.println("Readed x = " + points[i].getX() + ", y = " + points[i].getY());
                }
            tabFunc = new LinkedListTabulatedFunction(points);
            return tabFunc;
        } catch (IOException e) {
            System.out.println(e.getMessage()+", return null");
            return tabFunc;
        }
    }
}
