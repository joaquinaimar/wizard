package com.wizard.websocket.basic.entrance;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;

public class EntranceMessageInbound extends MessageInbound {
	
	public EntranceMessageInbound() {
		
	}

	@Override
	protected void onBinaryMessage(ByteBuffer buffer) throws IOException {

	}

	@Override
	protected void onTextMessage(CharBuffer buffer) throws IOException {
		System.err.println(buffer);
	}

}
