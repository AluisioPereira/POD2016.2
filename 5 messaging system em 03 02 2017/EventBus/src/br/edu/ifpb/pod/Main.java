package br.edu.ifpb.pod;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ajp
 */
public class Main {

    /**
     * Extrai os dados da mensagem [0] publisherId [1] subscriberId [2] text
     *
     * @param text
     * @return
     */
    private static String[] extractMsg(String text) {
        String t = "---123456---";
        if (text.startsWith(t) && text.endsWith(t)) {
            return text.replaceAll(t, "").split("\\|");

        }
        throw new RuntimeException("Mensagem estrutura incorretamente.");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
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
        ServerSocket severSocket = new ServerSocket(10999);
        while (true) {
            Socket clientSocket = severSocket.accept();
            byte[] b = new byte[1024];
            clientSocket.getInputStream().read(b, 0, 1024);
            
            String texMessage = new String(b).trim();
            System.out.println("Mensagem recebida: "+texMessage);
            // recupera a mensagem
            String extractedTextMessage[] = extractMsg(texMessage);
            String publisherId = extractedTextMessage[0];//0
            String subscriberId = extractedTextMessage[1];//1
            String text = extractedTextMessage[2];//0
            // persistir a mensagem
            String msgId = UUID.randomUUID().toString();
            Message message = new Message(msgId, publisherId, subscriberId, text);
            manager.publish(message);
            //informar ao publicador que a mensagem foi bulicada
            clientSocket.getOutputStream().write("#OK#".getBytes());
            //encerra a conexão;
            clientSocket.close();
        }
    }

}
