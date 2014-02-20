package com.proc.resource;
import com.proc.base_type.*;
import java.io.*;
import java.util.*;
public class Machine_List {

	private Physical_Server[] num_serv;	
	private List<String> save_ReadFormFile = new ArrayList<String>();
	
	public Machine_List(){
		File machine_list = new File("/Cloud/list/machine_list.V2");				
		try{
			Scanner scan = new Scanner(machine_list);									
			while(scan.hasNextLine()){
			
				String tmp = scan.nextLine();
				if(tmp.charAt(0)=='#')
					continue; //如果是註解開頭則不讀取資料				
				else{					
					save_ReadFormFile.add(tmp);
					//System.out.println(tmp);
				}
			}
			/*String serial_num , String ip , String port 
						, String num_vm_max , String preOpenVm_num, boolean Physical_Available*/
			num_serv = new Physical_Server[save_ReadFormFile.size()]; // initial machine size
			for(int i = 0 ; i < save_ReadFormFile.size() ; i++){
				String[] Machine_data = save_ReadFormFile.get(i).split(":");		
				num_serv[i] = new Physical_Server(Machine_data[0] // serial_num
				                                 ,Machine_data[1] // ip
				                                 ,Machine_data[2] // port
				                                 ,Machine_data[3] // num_vm_max
				                                 ,Machine_data[4] // preOpenVm_num
				                                 ,true); // Physical_Available true = 可以使用
				System.out.println("************* Print Virtual_Server info *************");
				num_serv[i].printAllVmInfo();
			}
		}
		catch(Exception e){		
			e.printStackTrace();		
		}
		
	}

	public Physical_Server[] getNum_serv() {
		return num_serv;
	}

	public Physical_Server getAvailablePhysicalMachine(){ //得到可用的物理機器
		
		for(int i = 0 ; i < num_serv.length ; i++){			//search 全部的物理機器
			if(num_serv[i].isPhysical_Available()){ //如果可用則回傳
				return num_serv[i]; 
			}		
		}
		return null;  //無可用資源
	
	}
	public Physical_Server getAvailablePhysical_MachineHaveVirtual_SeverNotBeOpen(){ //物理機器上擁有尚未開啟的VM,透過此function回傳Physical_Server
		for(int i = 0 ;i <num_serv.length ; i++){
			for(Virtual_Server vs : num_serv[i].getVirtual_Server_List()){
				if(vs.isOpen()==false&&vs.isVirtual_Available()==false){
					return vs.getParent();
				}
			}
		}
		return null;
	}
	/*public void setUnAvailableMachine(Virtual_Server serv){
		serv.setVirtual_Available(false);
	}*/  // not used

}
