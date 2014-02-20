package Net_Send_and_Receive;
import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
public class Network_Send {
	
	   private BufferedInputStream file_input_stream = null;
	   
	   private BufferedOutputStream write_to_server_stream = null;
	   
	   private ObjectOutputStream message_to_client_stream = null;
	public Socket ConnectServer(Socket connect_server,String target_adress,int port){
		try{
			connect_server = new Socket(target_adress,port);
			connect_server.setKeepAlive(true);
		}
		catch(IOException ioe){
			System.out.println("no connect to Server");
		}

		return connect_server;
	}
	public void send_Message(Socket connect_client,String message){
		try{
			
			message_to_client_stream = new ObjectOutputStream(connect_client.getOutputStream());
			message_to_client_stream.flush();
			message_to_client_stream.writeObject(message);
		
			
			
			
			
			
		}
		catch(IOException ioe){
			System.out.println("send message error");
		}
	}
	public void send_FileToServer(Socket connect_server,File send_file){
		try{
			write_to_server_stream = new BufferedOutputStream(new DataOutputStream(connect_server.getOutputStream()));
		/* write to Sever Stream
		 *  
		*/
			file_input_stream = new BufferedInputStream(new FileInputStream(send_file));
		/* file be read to file_input stream
		 *  become write_to_server a input 
		 *  
		*/
			
			System.out.print("Sending now \n");
			byte[] f_byte = new byte[1];
			while(file_input_stream.read(f_byte)!=-1){				
				write_to_server_stream.write(f_byte);
				write_to_server_stream.flush();
			}
			connect_server.shutdownOutput();
			
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			
		}
	}
}