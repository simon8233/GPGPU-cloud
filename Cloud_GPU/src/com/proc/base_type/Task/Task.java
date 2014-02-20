package com.proc.base_type.Task;
import java.io.File;
import java.util.*;
import com.proc.base_type.*;

public class Task {

	//private List<String> compilecmd; NOT used  in new version 
	private List<String> executecmd;
	private List<String> moniterCmd;
	protected Virtual_Server server = null;
	private Priority pri;
	
	private File executeFile;
	//private int prcoessid;
	//private String ExecuteName_andPath;
	private int Task_ID;
	
	
	public File getExecuteFile() {
		return executeFile;
	}
	public void setExecuteFile(File executeFile) {
		this.executeFile = executeFile;
	}
	
	public int getTask_ID() {
		return Task_ID;
	}
	public void setTask_ID(int task_ID) {
		Task_ID = task_ID;
	}
	
	public String getTaskInfo(){
		return this.getExecutecmd().toString()+
		this.getServer().getServ_info();		
	}
	public Virtual_Server getServer() {
		return server;
	}
	public void setServer(Virtual_Server serv) {
		
		this.server = serv;
		this.server.setFlag("Start");
		List<String> tmp = new ArrayList<String>();
		tmp.add("ssh"); 
		tmp.add("-p"); 
		tmp.add(serv.getPort());
		tmp.add(serv.getServer_ip());
		/*tmp.add("/usr/bin/gcc");
		tmp.add("-O2"); //-O2 優化指令
		tmp.add("-o");
		tmp.add(getExecuteName_andPath());
		tmp.add(getCode().getPath());
		
		setCompilecmd(new ArrayList<String>(tmp));
		
		tmp  = tmp.subList(0, 4);
		 compiler Initialization
		*/
		tmp.add(getExecuteFile().getAbsolutePath());
				
		setExecutecmd(new ArrayList<String>(tmp));
		
		tmp = tmp.subList(0, 4);
		tmp.add("/Cloud/shell_script/moniter.sh");
		tmp.add(getExecuteFile().getAbsolutePath());
		System.out.println("moniter cmd "+tmp);
		setMoniterCmd(new ArrayList<String>(tmp));
	}
	// inner initial
	/*protected void setCompilecmd(ArrayList<String> compilecmd) {
		this.compilecmd = compilecmd;
		System.out.println(compilecmd);
	}*/
	protected void setExecutecmd(ArrayList<String> executecmd) {
		this.executecmd = executecmd;
		System.out.println(executecmd);
	}
	/*public List<String> getCompilecmd() {
		return compilecmd;
	}*/
	public List<String> getExecutecmd() {
		return executecmd;
	}
	
	
	public List<String> getMoniterCmd() {
		return moniterCmd;
	}
	public void setMoniterCmd(List<String> moniterCmd) {
		System.out.println("setMoniter===="+moniterCmd);
		this.moniterCmd = moniterCmd;
	}
/*	public String getExecuteName_andPath() {
		return ExecuteName_andPath;
	}
	
	public void setExecuteName_andPath(String executeName_andPath) {
		ExecuteName_andPath = executeName_andPath;
	}*/ 

	/*public int getPrcoessid() {
		return prcoessid;
	}
	public void setPrcoessid(int prcoessid) {
		this.prcoessid = prcoessid;
	}*/
	
	
	
	public Priority getPri() {
		return pri;
	}
	public void setPri(Priority pri) {
		this.pri = pri;
	}
	/*public File getCode() {
		return code;
	}*/  // Not used
/*	public void setCode(File code) {
		this.code = code;
		ExecuteName_andPath = code.getParent()+"/"+code.getName().substring(0,code.getName().lastIndexOf('.'));
		
	}*/
	public String toString(){
		return getExecuteFile().getName();
	}
	
	

	
}
