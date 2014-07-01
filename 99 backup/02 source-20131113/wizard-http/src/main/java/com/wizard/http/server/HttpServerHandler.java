package com.wizard.http.server;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.wizard.http.thread.HttpHandlerErrorThread;
import com.wizard.http.thread.HttpHandlerOkThread;

public class HttpServerHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		new Thread(new HttpHandlerOkThread(e)).start();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		new Thread(new HttpHandlerErrorThread(ctx, e)).start();
	}

}