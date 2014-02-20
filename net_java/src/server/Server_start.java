package server;
import java.util.*;
import java.io.*;
import java.net.*;
import Net_Send_and_Receive.Network_Receive;
import Net_Send_and_Receive.Network_Send;
class listenClient implements Runnable{
	
	private ServerSocket ss;
	
	public listenClient(ServerSocket ss){
		this.ss = ss;
	}
	File receive_file = null;
	public void run(){
		while(true){
			try{
				File test = new File("/home/simon/java/network/server/hello_world_server.c");
				Ap_AtServerRun apRun = new Ap_AtServerRun();
				Network_Receive receive = new Network_Receive();
				Network_Send send = new Network_Send();
				Socket cs = ss.accept();
				
				System.out.println("Server accept Client ...");								
				receive.receive_FileFormClient(cs, test);
				
				System.out.println("sending : receive file on server");
				send.send_Message(cs, "receive file on server");
				System.out.println("sending : compile file");
				send.send_Message(cs, "compile file ");
				
				String[] cmd1={"/usr/lib64/ccache/gcc","-o","/home/simon/java/network/server/hello_world_server","/home/simon/java/network/server/hello_world_server.c",};
				apRun.run_process(cmd1);
				System.out.println("sending : execute program");
				send.send_Message(cs, "execute program ");
				String[] cmd2 = {"/home/simon/java/network/server/hello_world_server"};
				apRun.run_process(cmd2);
				send.send_Message(cs, "execute program over");
				
				/*
					
				send.send_Message(cs, "receive file on server");
				String outData = "Server Information: connecting and receive file on server."+"\n";	

				
				send.send_Message(cs, "compile file ");
				
				*/
				
				
				
				//send.send_Message(cs, "message form server send");
				//System.out.println(outData);
				
			
									
				
			}
			catch(IOException ioe){
				
			}
		}
	}
	
}
public class Server_start {
public static void main(String[] args)throws IOException{
		
		System.out.println("Server Start...");
		int port = 9526; 
		ServerSocket ss = new ServerSocket(port);
		Thread thread = new Thread(new listenClient(ss));
		thread.start();

		
	}

}
