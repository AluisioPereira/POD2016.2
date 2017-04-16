/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author ajp
 */
public class ServerApp {

    public static void main(String[] args) {
        try {
            IReceiver server = new ServerImp();
            Registry registry = LocateRegistry.createRegistry(10992);
            System.out.println("Registrando...");
            registry.bind("ServerApp", server);
            System.out.println("ServerApp - pronto para ouvir...");

        } catch (Exception e) {
            System.err.println("ServerApp - não foi possível estabelecer comunicação.");
            e.printStackTrace();
        }

    }

}
