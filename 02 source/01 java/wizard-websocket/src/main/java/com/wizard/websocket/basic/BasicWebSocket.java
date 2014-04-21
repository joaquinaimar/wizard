package com.wizard.websocket.basic;

import java.io.IOException;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

@ServerEndpoint(value = "/ws", configurator = SpringConfigurator.class)
public class BasicWebSocket {

	@OnOpen
	public void onOpen() {
	}

	@OnMessage
	public void onMessage(Session session, String msg, boolean last) {
		try {
			session.getBasicRemote().sendText("++++++++++++++++++++++++++++++++++++++"+ msg, last);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Throwable t) {
	}
}
