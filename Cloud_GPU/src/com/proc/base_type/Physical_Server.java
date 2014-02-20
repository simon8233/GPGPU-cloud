package com.proc.base_type;


import com.proc.compile.*;
public class Physical_Server extends Server{
	
	private int Max_Vm_Num;
	private int PreOpenVm_Num;
	
	private Virtual_Server[] Virtual_Server_List ;
	private boolean Physical_Available;
	
	public Physical_Server(String serial_num , String ip , String port 
						, String num_vm_max , String preOpenVm_num, boolean Physical_Available
						){
		// Physical initial
		this.setSerial_num(serial_num); //主機序號 
		this.setServer_ip(ip); // ip 
		this.setPort(port); // port
		this.setPreOpenVm_Num(Integer.parseInt(preOpenVm_num)); //預先需開啟的數量
		this.setPhysical_Available(Physical_Available);
		// Virtual initial 
		this.Max_Vm_Num = Integer.parseInt(num_vm_max);
		Virtual_Server_List = new Virtual_Server[Max_Vm_Num];
		int port_SeqVM_start = 10020;//目前採取固定的port作為連線使用
		
		PreOpenVMonPhysicalSev(ip,PreOpenVm_Num); // 預先使用另外一個script 來開啟VM 
		
		for(int i = 0;i< Max_Vm_Num ;i++){
			if(i < PreOpenVm_Num){
				Virtual_Server_List[i] = new Virtual_Server(Integer.toString(i),
									 getServer_ip(),Integer.toString((port_SeqVM_start)),
									     true,true,this);	
										//initialization  available VM_information
			}
			else{
				Virtual_Server_List[i] = new Virtual_Server(Integer.toString(i),
									 getServer_ip(),Integer.toString((port_SeqVM_start))
										,false,false,this);
									  //initialization  Unavailable VM_information
			}
			
			port_SeqVM_start++;
		}
		
		
				
	}
	
	
	/*public boolean SearchShutdownTimer(){ // 用來檢查最後一份工作 由於工作採多工，所以我們必須檢查是否有其他task是被設定為True,
										 //  代表說這份工作還在wait關機時間,所以意味著這不是最後一份工作.
		for(int i = 0; i < Max_Vm_Num ; i++){
			if(Virtual_Server_List[i].isCanNotShutdown_timer()==true){
				return false;
			}		
		}
		return true;
	}*/
	
	public Virtual_Server[] getVirtual_Server_List() {
		return Virtual_Server_List;
	}

	public void printAllVmInfo(){
		for(int i = 0 ; i < Max_Vm_Num ;i++){
			
			System.out.println(Virtual_Server_List[i].getServ_info());
		}
			System.out.println(this.getServ_info()+"Physical_Server Status = "+this.isPhysical_Available());
		

	}
	synchronized public Integer ServerNum_MoreEqualPreOpenVm(String Task_info){ // Phy中的VM是否有任何工作。
		int count = 0;
		for(int i = 0 ; i < Max_Vm_Num ;i++){
			if(Virtual_Server_List[i].isOpen()){				
					count++;
					System.out.print("Count"+count);
				
			}
		}		
		if(count > this.PreOpenVm_Num){ //如果有超過list中所撰寫預設開啟VM數量 則 進入 設定shutdown Tag
			count = count - PreOpenVm_Num;
			for(int i = 0 ; i < count ;i++){
				if(Virtual_Server_List[i].isOpen()){
					if(Virtual_Server_List[i].isVirtual_Available()){
						Virtual_Server_List[i].setFlag("shutdown"+Task_info);						
					}
				}
			}
			System.out.println("Y"+"ServerNum_MoreEqualPreOpenVM");
			return count; //count 則是需要關閉的數量 ,與被設定需要關機器的機器			
		}			
		else{
			System.out.println("N"+"ServerNum_MoreEqualPreOpenVM");
			return null;
		}
	}
	public boolean checkFlagisNotbeChange(String TaskInfo,int shutdown_num){
		                                  // 目前工作總數 , 下達關機時的taskID回傳需要關機幾台的數字
		System.out.println("checkFlagisNotbeChange"+"需要關閉的數量"+shutdown_num+"目前的Task ID"+TaskInfo);
		String tmp = "shutdown"+TaskInfo;
		int count = 0;
		for(int i = 0 ; i < Max_Vm_Num ;i++){
			if(Virtual_Server_List[i].isOpen()){
				if(Virtual_Server_List[i].isVirtual_Available()){
					if(Virtual_Server_List[i].getFlag().equals(tmp)){ // 若Flag 與先shutdown+taskInfo的值相同則增加count
						count++;
					}
				}
			}
		}
		if(count == shutdown_num){
			System.out.println("Y"+"checkFlagisNotbeChange"+TaskInfo);
			for(Virtual_Server vs: Virtual_Server_List){
				if(vs.getFlag().equals(tmp)){
					vs.setVirtual_Available(false);//用來防止又有人送入工作
					System.out.println(vs.getFlag());
				}
			}
			return true; //進入執行關機script 
		}
		else{
			System.out.println("N"+"checkFlagisNotbeChange"+TaskInfo);
			for(Virtual_Server vs: Virtual_Server_List){
				System.out.println(vs.getFlag());

			}
			return false;
		}
		
	}
	public boolean IsMorePreOPENVM(){
		int count = 0;
		for(int i = 0 ; i < Max_Vm_Num ;i++){
			if(Virtual_Server_List[i].isOpen()){
				count++;
			}
		}
		if(count > this.PreOpenVm_Num)
			return true;		
		else
			return false;
		
	}
	synchronized public void SearchShutdownVM(String TaskInfo){ // 此function 必須注意synchronized 造成的效能影響
		String tmp = "shutdown"+TaskInfo;
		
		for(int i = 0; i < Max_Vm_Num ; i++){
			if(Virtual_Server_List[i].isOpen()&&Virtual_Server_List[i].getFlag().equals(tmp)){
				//Virtual_Server_List[i].setVirtual_Available(false); //給予不可用的狀態 來解決再中途被人送工作的可能
				ShutdownVmonPhysicalServ(Virtual_Server_List[i].getParent().getServer_ip(),Virtual_Server_List[i]); // 
				System.out.println(Virtual_Server_List[i].getServer_ip()+"VritaulPC"+Virtual_Server_List[i].getSerial_num());				
			}		
		}		
	}
	public void ShutdownVmonPhysicalServ(String ip,Virtual_Server vs){
		Execute_OutProgram exec = new Execute_OutProgram();
		String[] shutdown_machine = {"/Cloud/shell_script/shutdownVM.sh",ip,vs.getSerial_num()};
		exec.run_Executeprocess(shutdown_machine);
		vs.setVirtual_Available(false);
		vs.setOpen(false);
	}
	
	synchronized public Virtual_Server SearchNotBootVM(){
		
		//System.out.println("simon 我快睡著拉");
		for(int i = 0 ; i < Max_Vm_Num ; i++){
			if(Virtual_Server_List[i].isOpen()==false){
				Virtual_Server_List[i].setOpen(true);
				return Virtual_Server_List[i];
			}			
		}		
		return null;		
	}
	public void OpenVM(String ip ,Virtual_Server vs){					
			OpenVMonPhysicalServ(ip,vs);
			vs.getParent().setPhysical_Available(true);		
	}
	public void OpenVMonPhysicalServ(String ip ,Virtual_Server vs){
		Execute_OutProgram exec = new Execute_OutProgram();
		String[] startmachine = {"/Cloud/shell_script/addMachine.sh",ip,vs.getSerial_num()};
		exec.run_Executeprocess(startmachine);
		vs.setVirtual_Available(true);
		vs.setOpen(true);
	}
	private void PreOpenVMonPhysicalSev(String ip ,int PreOpenVm_Num){ //Pre 才對
		Execute_OutProgram exec = new Execute_OutProgram();
		String[] startmachine = {"/Cloud/shell_script/addALLMachine.sh",ip,Integer.toString(PreOpenVm_Num-1)};
																		//script 0 to n-1 / = n
		
		exec.run_Executeprocess(startmachine);		
	}
	public boolean isPhysical_Available() {
	/*	for(Virtual_Server vs:Virtual_Server_List){
			if(vs.isVirtual_Available()){
				return true;
			}
		}
		return false;*/
		return Physical_Available;
	}
	public void setPhysical_Available(boolean physical_Available) {
		Physical_Available = physical_Available;
	}
	
	
	public Virtual_Server getAvailableVM(){
		for(int i = 0 ; i < Max_Vm_Num ; i++){
			if(Virtual_Server_List[i].isVirtual_Available()){
				Virtual_Server_List[i].setVirtual_Available(false);//此時便將Virtual_Server 關閉不讓他人使用
			return Virtual_Server_List[i];
			}
		}
		return null;		
	}
	
	
	
	public int getMax_Vm_Num() {
		return Max_Vm_Num;
	}
	public void setMax_Vm_Num(int max_Vm_Num) {
		Max_Vm_Num = max_Vm_Num;
	}
	public int getPreOpenVm_Num() {
		return PreOpenVm_Num;
	}
	public void setPreOpenVm_Num(int preOpenVm_Num) {
		PreOpenVm_Num = preOpenVm_Num;
	}
	
	
	
}
