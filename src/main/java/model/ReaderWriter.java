package model;

import java.io.*;
import java.util.*;
import java.util.Map.*;
import java.util.Iterator;
public class ReaderWriter{

	public void read(String filename, TreeMap<String, String> users){
		try{
			FileInputStream fstream = new FileInputStream(filename);
	   		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	   		String str;
	   		while ((str = br.readLine()) != null){
				String[] arr = str.split(":");
				if(arr[1].equals("-")){arr[1] = null;}
				users.put(arr[0], arr[1]);
	   		}
		}catch (IOException e){
		   		System.out.println("Error");
		}
	}

	public void write(String filename, TreeMap<String, String> users) {
		try(FileWriter writer = new FileWriter(filename, false)){
			for(Entry entry: users.entrySet()) {
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();
				writer.write(key + ":"+  value);
				writer.append('\n');
			}    
		    writer.flush();
		}
		catch(IOException ex){
		         
			System.out.println(ex.getMessage());
		} 
	} 


	public void readSubjects(String fileName, ArrayList<String> subjects)throws IOException{
		try{
			FileInputStream fstream = new FileInputStream(fileName);
	   		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	   		String str;
	   		while ((str = br.readLine()) != null){
				subjects.add(str);
	   		}
		}catch (IOException e){
			System.out.println("Error");
		}
	}

	public void writeSubjects(String fileName, ArrayList<String> subjects){
		try(FileWriter writer = new FileWriter(fileName, false)){
			for(String str : subjects) {
				writer.write(str);
				writer.append('\n');
			}    
		    writer.flush();
		}
		catch(IOException ex){
		         
			System.out.println(ex.getMessage());
		} 
	}
}


