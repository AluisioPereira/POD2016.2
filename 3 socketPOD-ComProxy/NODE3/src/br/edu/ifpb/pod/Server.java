package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aluísio
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        ServerSocket sv = new ServerSocket();
        sv.bind(new InetSocketAddress(10998));
        while (true) {
            Socket s = sv.accept();
            System.out.println("Server: cliente conectado....");
            InputStream i = s.getInputStream();
            byte[] b = new byte[1024];
            i.read(b);
            System.out.println("Mensagem enviada pelo cliente: ");
            // System.out.println(new String(b).trim());
            String text = new String(b);
            System.out.println("Requisição recebido:" + text);
            OutputStream output = s.getOutputStream();
            output.write("Recebido por Aluisio!".getBytes());
            System.out.println("Resposta recebido:" + text);
            s.close();
        }
        //  sv.close();
    }

}
