package br.edu.ifpb.pod;

import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * publicador protocolo de comunicação
 *
 * @author ajp
 */
public class Publisher {
// ip de laerton se comunicando com outra maquina

    private final static String EVENTBUS_HOST = "192.168.43.254";
    private final static Integer EVENTBUS_PORT = 10999;
    private final String identify;

    public Publisher(String indentify) {
        this.identify = indentify;
    }

    public void publish(String subscriberId, String text) {
        Socket socket = new Socket();
        try {
            // abrir comunicação
            socket = new Socket(EVENTBUS_HOST, EVENTBUS_PORT); // escrever mensagem;
            Protocol protol = new Protocol();
            protol.setPublisher(identify);
            protol.setSubscriber(subscriberId);
            protol.setMessage(text);
            socket.getOutputStream().write(protol.requestData());

            // reber um OK!
            byte[] b = new byte[4];
            socket.getInputStream().read(b);//<-- na internet existe a possíbilidade de vir apenas um byte não pode ser 
//        telimitado a resposta e se entrar em um loop para até chegada do fim do toket
            if ("#OK#".equals(new String(b))) {
                System.out.println("Mensagem publicada com sucesso.");
            } else {
                System.out.println("Mensagem não publicad. Tente novamente");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // encerrar conexão
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
