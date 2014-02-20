package com.proc.base_type.Task;
import java.util.*;

import com.proc.base_type.*;

public class Cuda_Task extends Task{

	public Cuda_Task(){
		super();
	}
	
	public void setServer(Virtual_Server serv) {
		
		server = serv;
		server.setFlag("Start_CUDA");
		List<String> tmp = new ArrayList<String>();
		tmp.add("ssh");
		tmp.add("-p");
		tmp.add(serv.getPort());
		tmp.add(serv.getServer_ip());
		/*
		tmp.add("/opt/cuda/bin/nvcc");
		tmp.add("-O2");
		tmp.add("-o");
		tmp.add(getExecuteName_andPath());
		tmp.add(getCode().getPath());
	
		setCompilecmd(new ArrayList<String>(tmp));
		
		tmp  = tmp.subList(0, 4);*/
		tmp.add(getExecuteFile().getAbsolutePath());
				
		setExecutecmd(new ArrayList<String>(tmp));
		

		tmp = tmp.subList(0, 4);
		tmp.add("/Cloud/shell_script/moniter.sh");
		tmp.add(getExecuteFile().getAbsolutePath());
		
		setMoniterCmd(new ArrayList<String>(tmp));
	}
	
	
}
