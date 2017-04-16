/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ajp
 */
public class Sender extends UnicastRemoteObject implements IClienteApp {

    private Mensagem msg;

    public Sender() throws RemoteException {
        super();
    }

    public Sender(Mensagem msg) throws RemoteException {
        this.msg = msg;
    }

    public Mensagem getMsg() throws RemoteException {
        return msg;
    }

    public void setMsg(Mensagem msg) throws RemoteException {
        this.msg = msg;
    }

    @Override
    public void sendHello(Mensagem msg) throws RemoteException {
        
        try {
            
            Task task = new Task();
            System.out.println("Criou task.");
            task.addMsg(msg);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void print(Mensagem msg) throws RemoteException {
        System.out.println("Mensagem: " + msg.toString());
    }

}
