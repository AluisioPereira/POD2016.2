package br.edu.ifpb.pod;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ajp
 */
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Subscriber {
	private final static String EVENTBUS_HOST = "localhost";
	private final static Integer EVENTBUS_PORT = 10998;
	private final static String TOKEN = "---123456---";
	public final static String ID = "aluisio-subscriber@hotmail.com";
	
	private Socket connect() throws UnknownHostException, IOException{
		Socket socket = new Socket(EVENTBUS_HOST, EVENTBUS_PORT);
		return socket;
	}
	
	private void send(Socket socket) throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append(TOKEN);
		sb.append(ID);
		sb.append(TOKEN);
		socket.getOutputStream().write(sb.toString().getBytes());
	}
	
	private String wait(Socket socket) throws IOException{
		byte[] b = new byte[1024];
		socket.getInputStream().read(b);
		return new String(b).trim();
	}
	
	/**
	 * Converter a mensagem em dados estruturados:
	 * - [0] publisherId
	 * - [1] text
	 * 
	 * @param msg
	 * @return
	 */
	private String[] convert(String msg){
		return msg.replaceAll(TOKEN, "").split("\\|");
	}
	
	public void subscribe(){
		try {
			//connect sever
			Socket socket = connect();
			//register
			send(socket);
			//wait messages
			boolean waiting  = true;
			while(waiting){
				String msg = wait(socket);
				String[] data = convert(msg);//protocols
				notify(data[0], data[1]);
			}
			//encerrar
			socket.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void notify(String pubId, String msg){
		System.out.println(String.format("Mensagem recebida de %s: %s", pubId, msg));
	}

}
