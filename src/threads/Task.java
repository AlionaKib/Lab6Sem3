package threads;

import Functions.Function;

public class Task {
    private Function integrateFunction;
    private double leftIntegrateBorder;
    private double rightIntegrateBorder;
    private double integrateInterval;
    private int taskCount;

    public Task(int taskCount){
        this.taskCount = taskCount;
    }

    public synchronized Function getFunction(){
        return this.integrateFunction;
    }

    public synchronized double getRightIntegrateBorder(){
        return this.rightIntegrateBorder;
    }

    public synchronized double getLeftIntegrateBorder(){
        return this.leftIntegrateBorder;
    }

    public synchronized double getIntegrateInterval(){
        return this.integrateInterval;
    }

    public synchronized void setFunction(Function f){
        this.integrateFunction = f;
    }

    public synchronized void setRightIntegrateBorder(double rightIntegrateBorder){
        this.rightIntegrateBorder = rightIntegrateBorder;
    }

    public synchronized void setLeftIntegrateBorder(double leftIntegrateBorder){
        this.leftIntegrateBorder = leftIntegrateBorder;
    }

    public synchronized void setIntegrateInterval(double integrateInterval){
        this.integrateInterval = integrateInterval;
    }

    public synchronized int getTaskCount(){
        return this.taskCount;
    }
}
