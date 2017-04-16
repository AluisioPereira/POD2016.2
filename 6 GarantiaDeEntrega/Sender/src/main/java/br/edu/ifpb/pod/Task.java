/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public class Task implements Runnable{

    private ArrayList<Mensagem> msgs;

    private static Registry registry = null;
    private static IReceiver receiver = null;

    public ArrayList<Mensagem> getMsgs() {
        return msgs;
    }

    public void setMsgs(ArrayList<Mensagem> msgs) {
        this.msgs = msgs;
    }
    
    public void addMsg(Mensagem msg){
        msgs.add(msg);
    }

    @Override
    public void run() {
        boolean v = true;
        while (v) {
            try {
                for (int i = 0; i <= msgs.size(); i++) {
                    if (!msgs.isEmpty()) {
                        sendToReceiver(msgs.get(i));
                    } else {
                        v = false;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public boolean sendToReceiver(Mensagem msg) throws RemoteException {

        try {
            registry = LocateRegistry.getRegistry("localhost", 10991);

            receiver = (IReceiver) registry.lookup("Reseiver");
            System.out.println("Enviando mensagem para receiver.");
            receiver.request(msg);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
