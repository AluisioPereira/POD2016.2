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
public class Principal {

    public static void main(String[] args) {

        try {
            IClienteApp clienteapp = new Sender();
            Registry registry = LocateRegistry.createRegistry(10999);
            System.out.println("Registrando...");
            registry.bind("Sender", clienteapp);
            System.out.println("Sender - pronto para ouvir...");
        } catch (Exception e) {
            System.err.println("Sender - não foi possível estabelecer comunicação.");
            e.printStackTrace();
        }

    }

}
