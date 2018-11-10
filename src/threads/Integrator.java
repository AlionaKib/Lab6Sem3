package threads;

import java.util.concurrent.Semaphore;

import static Functions.Functions.integrate;

public class Integrator extends Thread{
    /*private Task task;
    private Semaphore semaphore;

    public Integrator(Task task, Semaphore semaphore) {
        this.task = task;
        this.semaphore=semaphore;
    }

    public void run() {
        for(int i=0; i<task.getTaskCount(); ++i){
                try{
                    semaphore.acquire();
                    sleep(1);
                    if (task.getFunction() == null) {
                        i--;
                        continue;
                    }
                    double integral = integrate(task.getFunction(), task.getLeftIntegrateBorder(), task.getRightIntegrateBorder(),
                            task.getIntegrateInterval());
                    System.out.println("Result " + task.getLeftIntegrateBorder() + ' ' + task.getRightIntegrateBorder() +
                            ' ' + task.getIntegrateInterval() + ' ' + integral);
                } catch (InterruptedException e) {
                    System.out.println("Stream was corrupted");
                }
        }
    }*/
    private Task task;
    private Semaphore semaphore;
    public Integrator(Task task, Semaphore semaphore) {
        this.task = task;
        this.semaphore=semaphore;
    }

    public void run() {
        for (int i = 0; i < task.getTaskCount(); i++, semaphore.release()) {
            try {
                semaphore.acquire();
                sleep(1);
                if (task.getFunction() == null) {
                    i--;
                    continue;
                }
                System.out.println("Result " + task.getLeftIntegrateBorder() + ' ' + task.getRightIntegrateBorder() + ' '+
                        task.getIntegrateInterval() +' '+ integrate(task.getFunction() , task.getLeftIntegrateBorder(),
                        task.getRightIntegrateBorder(),task.getIntegrateInterval()));
            } catch (InterruptedException e) {
                System.out.println("Stream was corrupted");
            }
        }
    }
}
