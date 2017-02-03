/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gerenciador de tarefas
 *
 * @author ajp
 */
public class TaskManager implements Runnable {

    private final Register register;
    private final MessageManager messageManager;
    private final Notifier notifier;

    public TaskManager(Register register, MessageManager messageManager, Notifier notifier) {
        this.register = register;
        this.messageManager = messageManager;
        this.notifier = notifier;
    }

    /**
     *
     * @return
     */
    private List<String> listSubscribers() {
        List<String> subscribersIds = register.listAll();
        return Collections.unmodifiableList(subscribersIds);
    }

    /**
     *
     * @param subscriber
     * @return
     */
    private List<Message> listMessages(String subscriber) {
        List<Message> messages = messageManager.find(subscriber);
        return Collections.unmodifiableList(messages);
    }

    /**
     *
     * @param subscriber
     * @param message
     * @throws IOException
     */
    private void notifyAndRemoveMessage(String subscriber, Message message) throws IOException {
        notifier.notify(subscriber, message);
        messageManager.unplublisher(message);

    }

    @Override
    public void run() {
        //log
        System.out.println("Iniciando a tarefa");
        System.out.println("Listar suscritores");
        //listar subscritores
        List<String> subscribers = listSubscribers();
        System.out.println("Quantidade de subscritores: " + subscribers.size());

        //-- para cada suscritor
        for (String subscriber : subscribers) {
            // log
            System.out.println("Verificar se existem mensagems para: " + subscriber);
            //-- verificar se existe mensagens
            List<Message> messages = listMessages(subscriber);
            //log
            System.out.println("Existem" + messages.size() + "mensagens para " + subscriber);

            //-- caso exista, notifica o subscritor
            for (Message message : messages) {
                //log
                System.out.println("Enviando e descartando a mensagem " + message.getIdentify());
                try {
                    notifyAndRemoveMessage(subscriber, message);
                } catch (IOException ex) {
                    Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

}
