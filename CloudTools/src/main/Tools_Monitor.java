package main;
import java.io.*;
import java.util.Scanner;
import java.util.Timer;


public class Tools_Monitor {
	
	
	public static void main(String[] args){
		int Testcount = 0;
		Server[] num_serv;
		File serv_file = new File("/Cloud/list/Monitor_Server_list");
		try{
			//System.out.println("這是第"+Testcount+"次測試");
			Scanner scan = new Scanner(serv_file);						
			num_serv  = new Server[Integer.parseInt(scan.nextLine())];
			
			System.out.println(num_serv.length);
			
			for(int i = 0;i< num_serv.length;i++){
				num_serv[i] = new Server();				
				num_serv[i].setServer_ip(scan.nextLine());
				num_serv[i].setPort(scan.nextLine());
				num_serv[i].setAvailable(Boolean.parseBoolean(scan.nextLine()));
				num_serv[i].setID(i);
			}
			
			Monitor_TimerTask test = new Monitor_TimerTask(num_serv,1,0);
			Timer timer = new Timer();
			timer.schedule(test,0 ,40000);
			
			
			
		}
		catch(Exception e){		
			e.printStackTrace();		
		}
		
		
		
	}
	public void monitor(Server[] servers){
		
	}
}
