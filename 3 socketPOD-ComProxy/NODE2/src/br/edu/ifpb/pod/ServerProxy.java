package br.edu.ifpb.pod;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Aluísio
 */
public class ServerProxy {

    /**
     * @param args the command line arguments
     *
     */
    public static String proxy(String text) throws IOException {
       // 192.168.1.110
        String HOST = "localhost";
        Integer PORT = 10998;
        Socket socket = new Socket(HOST, PORT);
        OutputStream output = socket.getOutputStream();
        output.write(text.getBytes());
        System.out.println("Encaminhando node3");
        InputStream input = socket.getInputStream();
        byte[] b = new byte[1024];
        input.read(b);
        socket.close();
        return new String(b);
    }

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        ServerSocket sv = new ServerSocket();
        sv.bind(new InetSocketAddress(10999));
        while (true) {
            Socket s = sv.accept();
            System.out.println("Server: cliente conectado....");
            InputStream i = s.getInputStream();
            byte[] b = new byte[1024];
            i.read(b);
            System.out.println("Mensagem enviada pelo cliente: ");
            String text = new String(b);
            System.out.println("Requisição recebido:" + text);
            String txt = proxy(text);
            System.out.println("Resposta recebida: " + txt);
            OutputStream output = s.getOutputStream();
            output.write(txt.getBytes());
            System.out.println("Resposta recebido:" + text);
            s.close();
        }
    }

}
