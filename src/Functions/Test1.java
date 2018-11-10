package Functions;

import Functions.basic.Cos;
import Functions.basic.Exp;
import Functions.basic.Log;
import Functions.basic.Sin;
import Functions.meta.Composition;
import View.FunctionParameters;
import javafx.scene.control.Tab;
import threads.*;

import java.io.*;
import java.util.concurrent.Semaphore;

import static Functions.Functions.integrate;
import static Functions.Functions.power;
import static Functions.Functions.sum;
import static Functions.TabulatedFunctions.*;

public class Test1 {
    /*private static void printTabulatedFunction(TabulatedFunction func1){
        System.out.println("Your tabulated function:");
        for(int i=0; i<func1.getPointCount(); ++i){
            System.out.println("x: "+func1.getPointX(i)+", y: "+func1.getPointY(i));
        }
    }*/

    public static void main(String[] args) {
        //double[] vals = {0,0.25,1,2.25,4,6.25,9};
            /*TabulatedFunction func1 = new LinkedListTabulatedFunction(0, 3, vals);
            printTabulatedFunction(func1);
            System.out.println("left border = " + func1.getLeftDomainBorder() + ", right border = " + func1.getRightDomainBorder() +
                    ", function value at x=0.25: " + func1.getFunctionValue(0.25));
            func1.addPoint(new FunctionPoint(3.5, 12.25));
            System.out.println("Added point (3.5 12.25)");
            printTabulatedFunction(func1);
            System.out.println("left border = " + func1.getLeftDomainBorder() + ", right border = " + func1.getRightDomainBorder() +
                    ", count of points: " + func1.getPointCount() + ", function value of last point: " + func1.getPointY(func1.getPointCount() - 1));
            func1.addPoint(new FunctionPoint(1.25, 1.5625));
            System.out.println("Added point (1.25 1.5625)");
            printTabulatedFunction(func1);
            System.out.println("left border = " + func1.getLeftDomainBorder() + ", right border = " + func1.getRightDomainBorder() +
                    ", count of points: " + func1.getPointCount() + ", function value of 3 point: " + func1.getPointY(3));
            func1.deletePoint(3);
            System.out.println("Deleted point at index 3");
            printTabulatedFunction(func1);
            System.out.println("left border = " + func1.getLeftDomainBorder() + ", right border = " + func1.getRightDomainBorder() +
                    ", count of points: " + func1.getPointCount() + ", function value of 3 point: " + func1.getPointY(3));
            /*func1.setPoint(4, new FunctionPoint(0.25, 0.0625));
            System.out.println("Setted point (0.25 0.0625) at index 4");
            printTabulatedFunction(func1);
            System.out.println("left border = " + func1.getLeftDomainBorder() + ", right border = " + func1.getRightDomainBorder() +
                    ", count of points: " + func1.getPointCount() + ", function value of 4 point: " + func1.getPointY(4));
            func1.setPoint(7, new FunctionPoint(5, 25));
            System.out.println("Setted point (5 25) at index 7");
            printTabulatedFunction(func1);
            System.out.println("left border = " + func1.getLeftDomainBorder() + ", right border = " + func1.getRightDomainBorder() +
                    ", count of points: " + func1.getPointCount() + ", function value of last point: " + func1.getPointY(func1.getPointCount() - 1));*/

        /*Function s = new Sin();
        Function s1 = new Sin();
        Function c = new Cos();
        double k=0;
        System.out.println("Function value");
        for(int i=0; k < 2*Math.PI; ++i){
            k=0.1*i;
            System.out.println("x = " + k + " sin(x) = " + s.getFunctionValue(k) + ", cos(x) = " + c.getFunctionValue(k));
        }
        TabulatedFunction f1 = tabulate(s, 0, 2*Math.PI, 10);
        TabulatedFunction f2 = tabulate(s1, 0, 2*Math.PI, 10);
        System.out.println("Tabulated function value");
        k=0;
        for(int i=0; k < 2*Math.PI; ++i){
            k=0.1*i;
            System.out.println("x = " + k + " sin(x) = " + f1.getFunctionValue(k) + ", cos(x) = " + f2.getFunctionValue(k));
        }

        Function f3 = sum(power(f1,2),power(f2,2));
        System.out.println("Tabulated square function value");
        k=0;
        for(int i=0; k < 2*Math.PI; ++i){
            k=0.1*i;
            System.out.println("x = " + k + " (sin(x))^2 + (cos(x))^2 = " + f3.getFunctionValue(k));
        }

        TabulatedFunction tabulatedExp = tabulate(new Exp(), 0, 10, 11);
        TabulatedFunction tabulatedLog = tabulate(new Log(Math.E), 1, 11, 11);
        TabulatedFunction tabulatedLogExp = tabulate(new Composition(new Exp(), new Log(Math.E)), 0, 10, 11);
        FileWriter output;
        FileReader input;
        FileOutputStream out;
        FileInputStream in;
        File file;
        ObjectOutputStream obOut;
        ObjectInputStream obIn;
        TabulatedFunction readedTabulatedExp;
        TabulatedFunction readedTabulatedLog;
        TabulatedFunction readedTabulatedLogExp;
        try {
            file = new File("TabulatedExp.txt");
            //file.createNewFile();
            output= new FileWriter(file);
            //outputStream = new FileOutputStream(file);
            //serializeBuilding(dwelling, outputStream);
            //outputStream.flush();
            //outputStream.close();
            writeTabulatedFunction(tabulatedExp, output);
            output.close();
            input = new FileReader(file);
            readedTabulatedExp = readTabulatedFunction(input);
            input.close();
            //System.out.println("Readed: points count " + readedTabulatedExp.getPointCount() + ", left border " + readedTabulatedExp.getLeftDomainBorder() + ", rigth border " + readedTabulatedExp.getRightDomainBorder());

            System.out.println("Tabulated exp:");
            for(int i=0; i<11; ++i){
                System.out.println("x = " + i + ", exp(x) = " + tabulatedExp.getFunctionValue(i) + ", readed exp(x) = " + readedTabulatedExp.getFunctionValue(i));
            }
            file = new File("TabulatedLog.txt");
            out = new FileOutputStream(file);
            outputTabulatedFunction(tabulatedLog,out);
            out.close();
            in = new FileInputStream(file);
            readedTabulatedLog = inputTabulatedFunction(in);
            in.close();
            System.out.println("Tabulated log:");
            for(int i=1; i<12; ++i){
                System.out.println("x = " + i + ", log(x) = " + tabulatedLog.getFunctionValue(i) + ", readed log(x) = " + readedTabulatedLog.getFunctionValue(i));
            }
            file = new File("SerializedTabulatedLogExp.txt");
            out = new FileOutputStream(file);
            obOut = new ObjectOutputStream(out);
            obOut.writeObject(tabulatedLogExp);
            out.close();
            in = new FileInputStream(file);
            obIn = new ObjectInputStream(in);
            readedTabulatedLogExp = (TabulatedFunction) obIn.readObject();
            System.out.println("Tabulated log(exp):");
            for(int i=0; i<11; ++i){
                System.out.println("x = " + i + ", log(exp(x)) = " + tabulatedLogExp.getFunctionValue(i) + ", readed log(exp(x)) = " + readedTabulatedLogExp.getFunctionValue(i));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error1");
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
            System.out.println("Error2");
        }
        /*printTabulatedFunction(f1);
        System.out.println("tabulated sin(x):" + f1.toString());
        if(f1.equals(f2)) System.out.println("f1, f2 is same");
        System.out.println("Hashcode f2 = " + f2.hashCode() + ", hashcode f1 = " + f1.hashCode());
        f2.setPointY(2, 7);
        System.out.println("Setted (1.3962634015954636, 7) at f2");
        if(!f1.equals(f2)) System.out.println("f1, f2 is different");
        System.out.println("Hashcode f2 = " + f2.hashCode() + ", hashcode f1 = " + f1.hashCode());
        try {
            TabulatedFunction f3 =(TabulatedFunction) f1.clone();
            if(f1.equals(f3)) System.out.println("f1, f3 is same");
            System.out.println("Hashcode f3 = " + f3.hashCode() + ", hashcode f1 = " + f1.hashCode());
            f3.setPointY(2, 7);
            System.out.println("Setted (1.3962634015954636, 7) at f3");
            if(!f1.equals(f3)) System.out.println("f1, f3 is different");
            System.out.println("Hashcode f3 = " + f3.hashCode() + ", hashcode f1 = " + f1.hashCode());
        } catch (CloneNotSupportedException e){
            e.getStackTrace();
        }*/

        /*TabulatedFunction tabulatedLogExp = tabulate(new Composition(new Exp(), new Log(Math.E)), 0, 10, 11);
        FileOutputStream fwr;
        FileInputStream fread;
        TabulatedFunction readedLogExp;
        File file;
        try{
            file = new File("LogExp");
            fwr = new FileOutputStream(file);
            outputTabulatedFunction(tabulatedLogExp, fwr);
            fwr.close();
            fread = new FileInputStream(file);
            readedLogExp = inputTabulatedFunction(fread);
            fread.close();
            System.out.println("Write: "+tabulatedLogExp.toString());
            System.out.println("Read: "+readedLogExp.toString());
            readedLogExp.addPoint(new FunctionPoint(2.5, 1));
            System.out.println("New read: "+readedLogExp.toString());
            tabulatedLogExp.addPoint(new FunctionPoint(2.5, 1));
            System.out.println("New: "+tabulatedLogExp.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InappropriateFunctionPointException e) {
            e.printStackTrace();
        }*/
        System.out.println("Integral exp(x) = " + integrate(new Exp(), 0, 1, 0.001));
        //nonThread();
        //simpleTreads();
        complecatedThreads();
    }


    public static void nonThread() {
        Task task = new Task(100);
        for (int i = 0; i < task.getTaskCount(); ++i) {
            task.setFunction(new Log(Math.random() * 10 + 1));
            task.setLeftIntegrateBorder(Math.random() * 100);
            task.setRightIntegrateBorder(Math.random() * 100 + 100);
            task.setIntegrateInterval(Math.random());
            System.out.println("Source " + task.getLeftIntegrateBorder() + ' ' + task.getRightIntegrateBorder() + ' ' + task.getIntegrateInterval());
            double integral = integrate(task.getFunction(), task.getLeftIntegrateBorder(), task.getRightIntegrateBorder(), task.getIntegrateInterval());
            System.out.println("Result " + task.getLeftIntegrateBorder() + ' ' + task.getRightIntegrateBorder() +
                    ' ' + task.getIntegrateInterval() + ' ' + integral);
        }
    }

    public static void simpleTreads() {
        /*Task task = new Task(100);
        Thread generator = new Thread(new SimpleGenerator(task));
        Thread integrator = new Thread(new SimpleIntegrator(task));
        //generator.setPriority(Thread.MAX_PRIORITY);
        //integrator.setPriority(Thread.MIN_PRIORITY);
        generator.start();
        integrator.start();
        System.out.println();*/
        Task t = new Task(100+(int)(Math.random()*100));
        Thread g = new Thread(new SimpleGenerator(t));
        Thread i = new Thread(new SimpleIntegrator(t));
        //g.setPriority(Thread.MIN_PRIORITY);
        //i.setPriority(Thread.MAX_PRIORITY);
        g.start();
        i.start();
        System.out.println();
    }

    public static void complecatedThreads() {
        Task task = new Task(100+(int)(Math.random()*100));
        Semaphore semaphore = new Semaphore(1, true);
        Thread generator =new Thread((new Generator(task, semaphore)));
        //g.setPriority(Thread.MAX_PRIORITY);
        Thread integrator = new Thread((new Integrator(task, semaphore)));
        //i.setPriority(Thread.MIN_PRIORITY);
        generator.start();
        integrator.start();
        try {
            Thread.sleep(50);
            generator.interrupt();
            integrator.interrupt();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
