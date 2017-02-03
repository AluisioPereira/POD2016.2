/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.net.Socket;

/**
 * Notificador do subscriber
 *
 * @author ajp
 */
public class Notifier {

    private final static String TOKEN = "---123456---";

    private final Register register;

    public Notifier(Register register) {
        this.register = register;
    }

    /**
     * Notifica um determinado subscriber com uma mensagem específica
     *
     * @param subscriberId - identificador do susbcriber
     * @param message - mensagem a ser notificada
     * @throws IOException
     */
    public void notify(String subscriberId, Message message) throws IOException {
        //
        Socket socket = register.find(subscriberId);
        //
        try {
            socket.getOutputStream().write(TOKEN.getBytes()); //início da mensagem
            socket.getOutputStream().write(message.getText().getBytes());
            socket.getOutputStream().write(TOKEN.getBytes()); // fim da mensagem

            //
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
