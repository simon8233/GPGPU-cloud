package server;
import java.io.*;
import java.io.File;

public class Ap_AtServerRun{

	BufferedOutputStream p_sen_cli;
	 Ap_AtServerRun(){
		 File putdir = new File("/home/simon/java/network/server/");
		 DataOutputStream p_sen_cli;
	}

	public void run_process(String[] cmd){

		try{
			//String[] cmd={"/usr/lib64/ccache/gcc","-o","/home/simon/java/network/server/hello_world_server","/home/simon/java/network/server/hello_world_server.c",};
			//String[] cmd={"/home/simon/java/network/server/gcc.sh"};
			Process p1 = Runtime.getRuntime().exec(cmd); 
			String line = "";
			System.out.println("commond over");
			BufferedReader p_in = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			p_sen_cli = new BufferedOutputStream(new DataOutputStream(p1.getOutputStream()));
			
			//p_sen_cli.wr
			while((line = p_in.readLine()) != null){
				System.out.println(line);
			}
			p_in.close();	
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
}
