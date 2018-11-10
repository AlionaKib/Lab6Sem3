package threads;

import Functions.basic.Log;

import static Functions.Functions.integrate;
import static java.lang.Thread.sleep;

public class SimpleIntegrator implements Runnable {
    private Task task;

    public SimpleIntegrator(Task task){
        this.task = task;
    }

    public void run() {
        for (int i = 0; i < task.getTaskCount(); i++) {
            synchronized (this) {
                try {
                    if (task.getFunction() == null) {
                        i--;
                        continue;
                    }
                    System.out.println("Result " + task.getLeftIntegrateBorder() + ' ' + task.getRightIntegrateBorder() + ' '+
                            task.getIntegrateInterval() +' '+ integrate(task.getFunction() , task.getLeftIntegrateBorder(),
                                    task.getRightIntegrateBorder(),task.getIntegrateInterval()));
                    sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }

        }
    }
}
