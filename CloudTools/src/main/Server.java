package main;

public class Server {
	
	private String Server_ip;
	private String port;
	private boolean available;
	private int ID;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
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
		String serv_info= port+" "+Server_ip+" "+Boolean.toString(available);
		return serv_info;
	}
	
}
