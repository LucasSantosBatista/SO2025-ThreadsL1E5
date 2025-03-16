package view;

import controller.ThreadPing;

public class Principal {
	public static void main(String[] args) {
		ThreadPing uol = new ThreadPing("uol");
		ThreadPing terra = new ThreadPing("terra");
		ThreadPing google = new ThreadPing("google");
		
		uol.start();
		terra.start();
		google.start();
	}
}
