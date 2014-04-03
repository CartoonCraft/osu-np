package fr.cartooncraft.osunp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) {
		
		int i = 0;
		String path = "";
		while(i < args.length) {
			path += args[i];
			i++;
			if(i != args.length) 
				path += " ";
		}
		
		if(path == "")
			path = new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"\\osunp.txt";
		
		while(true) {
			System.out.println(np(path));
			try {
				Thread.currentThread();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String np(String path) {
		String osuName = TryWithHWND.getOsuWindowName();
		
		Pattern p = Pattern.compile("^osu!  - (.+)$");
		
		Matcher m = p.matcher(osuName);
		if(m.matches()) {	
			String musicName = m.group(1)+"                                         ";
			File file = new File(path);
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(musicName);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return musicName;
		}
		else {
			File file = new File(path);
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(" ");
				writer.newLine();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		}
		
	}

}
