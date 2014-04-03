package fr.cartooncraft.osunp;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		
		JFrame frame = new JFrame();
		frame.setTitle("osu! - Now Playing");
		frame.setSize(1280, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.green);
		
		JLabel label = new JLabel();
		Font font = new Font("Arial Bold", 64, 32);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		panel.add(label);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
		
		while(true) {
			String s = np(path);
			// Refresh window
			label.setText(s);
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
			String musicName = m.group(1);
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
