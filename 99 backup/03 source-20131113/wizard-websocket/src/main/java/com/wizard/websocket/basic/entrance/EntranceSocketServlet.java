package com.wizard.websocket.basic.entrance;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@WebServlet("/entrance")
public class EntranceSocketServlet extends WebSocketServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6260026174009421243L;

	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {
		return new EntranceMessageInbound();
	}

}
