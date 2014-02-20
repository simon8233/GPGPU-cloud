package main;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;
public class TestMoniter {

	public static void main(String[] args) throws IOException{
		List<String> cmd = new ArrayList<String>();
		cmd.add("top");
		cmd.add("-b");		
		cmd.add("-p");
		cmd.add("2985");
		ProcessBuilder pb = new ProcessBuilder(cmd);
		Process p1 = pb.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
		String line;
		while((line = br.readLine()) != null){
			if(line.trim().endsWith("Xvnc"))
			System.out.println(line);
		}
	}
}
