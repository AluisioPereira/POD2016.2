/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author ajp
 */
public class MessageManager {

    private List<Message> repository = new ArrayList<Message>();

    /**
     * publica a mensage a ser encaminhada
     *
     * @param messagem - mensagem a ser encaminhada.
     */
    public void publish(Message messagem) {
        repository.add(messagem);
    }

    /**
     * Remover a messagen
     *
     * @param message - mensagem a ser removida
     */
    public void unplublisher(Message message) {
        repository.remove(message);
    }

    /**
     * Localizar mensagens para um determinado subscriber
     *
     * @param subscriberId -identificador do subscriber
     * @return
     */

    public List<Message> find(String subscriberId) {

        List<Message> result = new ArrayList<Message>();
        for (Message message : repository) {
            if (message.getSubscriberId().equals(subscriberId)) {
                result.add(message);
            }
        }
        return result;

    }
}
