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

/**
 *
 * @author ajp
 */
public class Receiver extends UnicastRemoteObject implements IReceiver {

    private static Registry registry = null;
    private static IReceiver serverApp = null;

    public Receiver() throws RemoteException {
        super();
    }
    

    @Override
    public void delivery(Mensagem msg) throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10992);

            serverApp = (IReceiver) registry.lookup("ServerApp");
            System.out.println("Enviando mensagem para receiver.");
            serverApp.delivery(msg);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
