package threads;

import Functions.basic.Log;

import static java.lang.Thread.sleep;

public class SimpleGenerator implements Runnable {
    private Task task;

    public SimpleGenerator(Task task){
        this.task = task;
    }

    public void run() {
        for(int i=0; i<task.getTaskCount(); i++){
            synchronized (this) {
                try {
                    task.setRightIntegrateBorder(100 + Math.random() * 100);
                    task.setLeftIntegrateBorder(Math.random() * 100);
                    task.setIntegrateInterval(Math.random());
                    task.setFunction(new Log(Math.random()*10 + 1));
                    System.out.println("Sourse " + task.getLeftIntegrateBorder() + ' '+
                            task.getRightIntegrateBorder() + ' '+ task.getIntegrateInterval());
                    sleep(80);
                }
                catch (InterruptedException e){
                    e.printStackTrace();

                }
            }


        }
    }
}
