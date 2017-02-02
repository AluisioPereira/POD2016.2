package br.edu.ifpb.pod;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ajp
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // instanciar os elementos principais
        Register register = new Register();
        Notifier notifierr = new Notifier(register);
        MessageManager manager = new MessageManager();
        TaskManager taskManager = new TaskManager(register, manager, notifierr);
        // programar o background

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        //EXCUTAR UMA THREAD depois dos primeiros 
        executor.scheduleAtFixedRate(taskManager, 2000, 5000, TimeUnit.MILLISECONDS);

        //Executor executor = Executors.newSingleThreadExecutor();
    }

}
