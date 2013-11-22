package com.wizard.util.net;

import java.io.IOException;
import java.net.ServerSocket;

public final class SocketUtil {

	private SocketUtil() {
		throw new RuntimeException("Cannot create util class instance!");
	}

	public static ServerSocket listen(int port) {
		try {
			return new ServerSocket(port);
		} catch (IOException e) {
			return null;
		}
	}
}
