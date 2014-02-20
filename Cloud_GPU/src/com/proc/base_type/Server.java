package com.proc.base_type;

public abstract class Server {
	private String serial_num;
	private String Server_ip;
	private String port;
	
	
	//private boolean available;
	//private int ID;
	/*
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}*/
	/*public boolean isAvailable() {
		return available;
	//}
	public void setAvailable(boolean available) {
		this.available = available;
	}*/ //v3 deldata
	
	public String getSerial_num() {
		return serial_num;
	}
	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}
	public String getServer_ip() {
		return Server_ip;
	}
	public void setServer_ip(String server_ip) {
		Server_ip = server_ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getServ_info(){
		String serv_info= port+" "+Server_ip;
		return serv_info;
	}
	
}
