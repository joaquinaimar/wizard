package com.wizard.http;

import com.wizard.http.server.HttpServer;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		HttpServer server = new HttpServer(8080);
		server.start();
		server.stop();
	}
}
