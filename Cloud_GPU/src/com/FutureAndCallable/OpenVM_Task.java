package com.FutureAndCallable;
import com.proc.base_type.*;

import java.util.concurrent.*;
public class OpenVM_Task extends Thread{
	
	Virtual_Server v_serv;
	public OpenVM_Task(Virtual_Server serv){
		this.v_serv = serv;
		this.start();
	}
	public void run(){
		v_serv.getParent().OpenVM(v_serv.getParent().getServer_ip(),v_serv);
		System.out.println("OpenVM"+v_serv.getServ_info()+"Virtual"+v_serv.getSerial_num()+"be Open");
	}

}
