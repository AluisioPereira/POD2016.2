/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Aluísio
 */
public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("Cliente: conectando servidor....");
        Socket s = new Socket("localhost", 10999);
        System.out.println("Cliente: enviando mensagem...");
        s.getOutputStream().write("Olá ".getBytes());
        s.getOutputStream().flush();
        System.out.println("Cliente: encerra socket...");
        s.close();
    }

}
