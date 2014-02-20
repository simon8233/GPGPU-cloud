package client;
import java.io.*;
import java.net.*;

import Net_Send_and_Receive.Network_Receive;
import Net_Send_and_Receive.Network_Send;

public class Client_start {

	public static void main(String[] args){
		File file = new File("D:\\hello_world_cli.c");
		
		Socket cs = null;
		int port = 9526;
		
		String target_address = "140.128.18.169"; // server address
			
			//try{
			Network_Send send = new Network_Send();
			Network_Receive receive = new Network_Receive();
			
			cs = send.ConnectServer(cs, target_address, port); // connect with Server
			//System.out.println(cs.isConnected());
			send.send_FileToServer(cs,file); // send File to Sever;
			
			
			String message = receive.receive_Message(cs); // receive message
			System.out.println(message);
			message = receive.receive_Message(cs); // receive message
			System.out.println(message);
			message = receive.receive_Message(cs); // receive message
			System.out.println(message);
			message = receive.receive_Message(cs); // receive message
			System.out.println(message);
			//cs.close();
			//}
			/*catch(IOException ioe){
				System.out.println("session no connect");
			}*/

		
		
	}
}
