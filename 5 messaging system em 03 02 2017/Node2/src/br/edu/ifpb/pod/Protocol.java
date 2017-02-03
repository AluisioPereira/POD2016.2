/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import javafx.beans.binding.StringBinding;

/**
 * Protocolo de comunicação com o barramento de eventos
 *
 * @author Aluísio
 */
public class Protocol {

    private final static String TOKEN = "---123456---";
    private String pub;
    private String msg;
    private String sub;
    
    public void setPublisher (String publisher){
        this.pub=publisher;
    }

    public void setMessage(String message) {
        this.msg=message;

    }

    public void setSubscriber(String suscriber) {
        this.sub=suscriber;

    }

    public byte[] requestData() {
        StringBuilder sb = new StringBuilder();
        sb.append(TOKEN);
        sb.append(pub);
        sb.append("|");
        sb.append(sub);
        sb.append("|");
        sb.append(msg);
        sb.append(TOKEN);
        return sb.toString().getBytes();
    }
}
