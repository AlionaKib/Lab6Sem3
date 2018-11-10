package threads;

import Functions.basic.Log;

import java.util.concurrent.Semaphore;

public class Generator extends Thread {
    /*private Task task;
    private Semaphore semaphore;

    public Generator(Task task, Semaphore semaphore){
        this.task = task;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for(int i=0; i<this.task.getTaskCount(); ++i) {
                try {
                    semaphore.acquire();
                    sleep(1);
                    this.task.setFunction(new Log(Math.random() * 10 + 1));
                    this.task.setLeftIntegrateBorder(Math.random() * 100);
                    this.task.setRightIntegrateBorder(Math.random() * 100 + 100);
                    this.task.setIntegrateInterval(Math.random());
                    System.out.println("Source " + task.getLeftIntegrateBorder() + ' ' + task.getRightIntegrateBorder() +
                            ' ' + task.getIntegrateInterval());
                } catch (InterruptedException e) {
                    System.out.println("Stream was corrupted");
                }
        }
    }*/
    private Task task;
    private Semaphore semaphore;
    public Generator(Task task, Semaphore semaphore){
        this.task=task;
        this.semaphore=semaphore;
    }
    public void run() {
        //int n=task.getCountTask();
        for(int i=0; i<task.getTaskCount(); i++,semaphore.release()){
            try {
                semaphore.acquire();
                sleep(1);
                task.setRightIntegrateBorder(100 + Math.random() * 100);
                task.setLeftIntegrateBorder(Math.random() * 100);
                task.setIntegrateInterval(Math.random());
                task.setFunction(new Log(Math.random()*10 + 1));
                System.out.println("Sourse " + task.getLeftIntegrateBorder() + ' '+
                        task.getRightIntegrateBorder() + ' '+ task.getIntegrateInterval());
            }
            catch (InterruptedException e){
                System.out.println("Stream was corrupted");
            }
        }
    }
}
