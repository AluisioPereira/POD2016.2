/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.util.Random;

/**
 *
 * @author ajp
 */
public class Cliente {

    public static void main(String[] args) {

        try {
            Random r = new Random();
            int id = r.nextInt(1000);
            Mensagem m = new Mensagem(id, "Olá mundo!");
            System.out.println("Abrindo conexão com ClienteApp");
            ClienteApp ca = new ClienteApp(m);
            ca.sendHello(m);
            ca.print(m);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
