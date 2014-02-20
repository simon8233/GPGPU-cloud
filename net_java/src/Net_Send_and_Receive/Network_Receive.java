package Net_Send_and_Receive;
import java.net.*;
import java.io.*;
public class Network_Receive {

	private BufferedInputStream receive_form_client = null;
	private BufferedOutputStream write_local_file = null;
	private ObjectInputStream receive_message_stream = null;
	public void receive_FileFormClient(Socket connect_client , File put_file){
		//put_file = new File("/home/simon/java/network/server/hello_world_server.c");
		byte[] f_byte = new byte[1]; // temp for Buff
		try{
			receive_form_client = new BufferedInputStream(new DataInputStream (connect_client.getInputStream()));								
			write_local_file = new BufferedOutputStream(new FileOutputStream(put_file));
			while(receive_form_client.read(f_byte)!=-1){				
				write_local_file.write(f_byte);	
			}
			write_local_file.close();
		}
		catch(IOException ioe){
			System.out.println("receive_fasle");
			
		}
		
	}
	public String receive_Message(Socket connect_client) {
		try{
			//System.out.println("connect !?"+connect_client.isConnected());
			String message = null;
			receive_message_stream = new ObjectInputStream(connect_client.getInputStream());
			
			message = (String) receive_message_stream.readObject();
			
			return message;
		}
		catch(Exception ioe){
			System.out.println("receive message error");
			ioe.printStackTrace();
			return null;
		}
	}
	
}
