package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.TimerTask;

public class Monitor_TimerTask extends TimerTask{
	Execute exec;
	Server[] servs;
	int n;
	int error;
	public Monitor_TimerTask(Server[] servers,int n,int error) {
		this.servs = servers;
		this.n = n;
		this.error = error;
	}
	public void run(){
		
		System.out.println("執行第"+n+"次");
		n++;
		System.out.println("錯誤第"+error+"次");
		exec = new Execute();
		Server serv;
		for(int i = 0;i<servs.length;i++){
			serv = servs[i];			
			String[] cmd = {"ssh",serv.getServer_ip(),"/Cloud/shell_script/Ping.sh","VirtualPC"+(i+1)};			 			
			exec.run_Executeprocess(cmd);			
			System.out.println(exec.getExecuteInformation());
			System.out.println(exec.getExecuteErrInformation());
								
			if(!exec.getExecuteInformation().isEmpty()){
				System.out.println("in ps kill vm");
				error++;
				//String[] ps_grep_cmd = {"ssh",serv.getServer_ip(),"/Cloud/shell_script/RestartVM.sh",serv.getServer_ip(),serv.getPort(),Integer.toString(i+1)};
				String[] ps_grep_cmd = {"/Cloud/shell_script/Remote.sh",serv.getServer_ip(),serv.getPort(),Integer.toString(i+1)};
				
					Execute ps_grep = new Execute();
					ps_grep.run_Executeprocess(ps_grep_cmd);
					System.out.println(ps_grep.getExecuteErrInformation());
					System.out.println(ps_grep.getExecuteInformation());
				
			}
			
		}
		
		
	}
	
	public void kill_remoteProcess(Server serv){
		//ps f -C qemu-system-x86_64
		
	}
}
