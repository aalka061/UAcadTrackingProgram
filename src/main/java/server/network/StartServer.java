package main.java.server.network;

import main.java.utilities.Config;

public class StartServer {
	public static void main(String[] argv) {
		System.out.println("Starting server ...");
		new LibServer(Config.DEFAULT_PORT);
	}
}
