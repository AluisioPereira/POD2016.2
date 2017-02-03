/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * Registrador de subscritores (interessados).
 *
 * @author ajp
 */
public class Register {

    private Map<String, Socket> repository = new HashMap<String, Socket>();

    /**
     * Registro de um subscriber em memória
     *
     * @param subscriverId - identificador do subscriber
     * @param subscriberSocket = socket da conexão com subscriber
     */
    public void register(String subscriverId, Socket subscriberSocket) {
        repository.put(subscriverId, subscriberSocket);
    }

    /**
     * Remover o subcritor (subscriber) da memória
     *
     * @param subscriberId - identificador do subscriber
     */
    public void unregister(String subscriberId) {
        repository.remove(subscriberId);
    }

    /**
     * recupera a conexão com o subscriber
     *
     * @param subscriberId - identificador do subscriber
     * @return
     */
    public Socket find(String subscriberId) {
        return repository.get(subscriberId);
    }

    /**
     * Lista de todos os identificadores dos subscribers
     *
     * @return
     */
    public List<String> listAll() {
        List<String> result = new ArrayList<String>();
        Set<String> keys = repository.keySet();
        for (String k : keys) {
            result.add(k);

        }
        return result;
    }
}
