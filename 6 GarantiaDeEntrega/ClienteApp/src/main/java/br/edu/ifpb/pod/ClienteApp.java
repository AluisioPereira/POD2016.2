/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ajp
 */
public class ClienteApp implements IClienteApp {

    private static Registry registry = null;
    private static IClienteApp sender = null;

    private Mensagem msg;

    public ClienteApp() {
    }

    public ClienteApp(Mensagem msg) {
        this.msg = msg;
    }

    @Override
    public void sendHello(Mensagem msg) throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry("localhost", 10999);

            sender = (IClienteApp) registry.lookup("Sender");
            System.out.println("Enviando mensagem para sender.");

            sender.sendHello(msg);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void print(Mensagem msg) throws RemoteException {
        sender.print(msg);
        System.out.println("Mensagem: " + msg.toString());
    }

}
