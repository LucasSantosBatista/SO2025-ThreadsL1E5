package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadPing extends Thread {
	String servidor;
	
	public ThreadPing(String servidor) {
		this.servidor = servidor;
	}
	
	private String getOS() {
		return System.getProperty("os.name");
	}
	
	public void run() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ping -4 -c 10 ");
		buffer.append("www." + servidor + ".com.br");
		if (!getOS().contains("Linux")) {
			System.out.println("Aplicação não suportada neste sistema operacional!");
		} else {
			try {
				Process p = Runtime.getRuntime().exec(buffer.toString().split(" "));
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

				String line = "";

				while ((line = reader.readLine()) != null) {
					if (line.contains("avg") && (line.contains("ms"))) {
						String[] divideLinha = line.split("=");
						String divideValores = divideLinha[1];
						String[] media = divideValores.split("/");
						
						System.out.println(servidor.toUpperCase() + " ping médio = " + media[1] + " ms");
					} else if (line.contains(" ms")) {
						String[] mostraTempo = line.split("=");
						System.out.println(servidor.toUpperCase() + " = " + mostraTempo[mostraTempo.length - 1] + " ms");
					}
				}
				
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
