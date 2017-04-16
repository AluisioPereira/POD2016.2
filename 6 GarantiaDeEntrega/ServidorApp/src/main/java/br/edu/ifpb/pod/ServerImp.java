/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public class ServerImp extends UnicastRemoteObject implements IReceiver {

    private static Registry registry = null;
    private static IReceiver serverApp = null;

    private ArrayList<Mensagem> msgs;

    public ServerImp() throws RemoteException {
        super();
    }

    public ArrayList<Mensagem> getMsgs() throws RemoteException {
        return msgs;
    }

    public void setMsgs(ArrayList<Mensagem> msgs) throws RemoteException {
        this.msgs = msgs;
    }

    @Override
    public void delivery(Mensagem msg) throws RemoteException {
        try {
            msgs.add(msg);
            registry = LocateRegistry.getRegistry("localhost", 10992);

            serverApp = (IReceiver) registry.lookup("ServerApp");
            System.out.println("Enviando mensagem para receiver.");

            System.out.println("Mensagem: ");
            for (int i = 0; i < msgs.size(); i++) {
                System.out.println("Mensaem: " + msgs.get(i));
            };

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
