package com.proc.base_type;

public class Virtual_Server extends Server{
	private boolean Virtual_Available; //  task再執行中 = true , task 沒執行 = false
	private boolean isOpen; // 檢查是否已經開機 
//	private boolean CanNotShutdown_timer = false; 
	private Physical_Server Parent;
	private String Flag = "noTask";
	public Virtual_Server(String serial_num,String ip,String port,boolean available, 
						boolean isOpen,Physical_Server Parent) {
		
		this.setSerial_num(serial_num);
		this.setServer_ip(ip);
		this.setPort(port);
		this.setVirtual_Available(available);
		this.setOpen(isOpen);
		this.setParent(Parent);
		
	} // 此為初始預先開機的虛擬機器
	
	

	public String getServ_info(){
		String serv_info= "Virtual_PC"+this.getSerial_num()+" "+this.getPort()+" "+this.getServer_ip()+"Virtual_Available="+this.isVirtual_Available()+"OPEN?"+this.isOpen();
		return serv_info;
	}
 


	public String getFlag() {
		return Flag;
	}



	public void setFlag(String flag) {
		Flag = flag;
	}



	/*
	public boolean isCanNotShutdown_timer() {
		return CanNotShutdown_timer;
	}


	public void setCanNotShutdown_timer(boolean canNotShutdown_timer) {
		CanNotShutdown_timer = canNotShutdown_timer;
	}
*/
	public Physical_Server getParent() {
		return Parent;
	}
	public void setParent(Physical_Server parent) {
		Parent = parent;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public boolean isVirtual_Available() {
		return Virtual_Available;
	}
	public void setVirtual_Available(boolean virtual_Available) {
		Virtual_Available = virtual_Available;
	}


	
}
