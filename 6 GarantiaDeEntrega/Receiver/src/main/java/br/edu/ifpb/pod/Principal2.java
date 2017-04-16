/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ajp
 */
public class Principal2 {

    public static void main(String[] args) {
        try {
            IReceiver sender = new Receiver();
            Registry registry = LocateRegistry.createRegistry(10991);
            System.out.println("Registrando...");
            registry.bind("Reseiver", sender);
            System.out.println("Reseiver - pronto para ouvir...");            

        } catch (Exception e) {
            System.err.println("Reseiver - não foi possível estabelecer comunicação.");
            e.printStackTrace();
        }
    }

}
