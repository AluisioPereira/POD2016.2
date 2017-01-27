/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Aluísio
 */
public class Client {

    public static void main(String[] args) throws IOException {
        //IP pedro 192.168.1.104
        //IP aristofanio 192.168.1.110
        String HOST = "localhost";
        Integer PORT = 10999;
        Socket socket = new Socket(HOST, PORT);
        OutputStream output = socket.getOutputStream();
        output.write("Enviada por NODE1!".getBytes());
        InputStream input = socket.getInputStream();
        byte[] b = new byte[2024];
        input.read(b);
        String text = new String(b);
        System.out.println(text);
        socket.close();
    }
}
