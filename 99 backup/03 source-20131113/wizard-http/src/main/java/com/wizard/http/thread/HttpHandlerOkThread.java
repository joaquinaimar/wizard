package com.wizard.http.thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

public class HttpHandlerOkThread implements Runnable {

	private MessageEvent event = null;

	public HttpHandlerOkThread(MessageEvent event) {
		this.event = event;
	}

	@Override
	public void run() {
		//HttpRequest request = (HttpRequest) event.getMessage();
		// String fileName = request.getUri().replaceFirst("/", "");
		//
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.OK);
		ChannelBuffer buffer = null;
		// try {
		// InputStream in = new FileInputStream(fileName);
		int b = 2048;
		// byte[] bytes = new byte[b];
		buffer = new DynamicChannelBuffer(b);
		// while (in.read(bytes) != -1) {
		buffer.writeBytes("hello".getBytes());
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		response.setContent(buffer);
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		response.setHeader("Content-Length", response.getContent()
				.writerIndex());
		Channel ch = event.getChannel();
		ch.write(response);
	}
}
