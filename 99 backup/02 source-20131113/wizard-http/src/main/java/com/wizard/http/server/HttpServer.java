package com.wizard.http.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class HttpServer implements Runnable {

	private int port = 80;

	private Channel channel = null;

	private ExecutorService bossExecutor = null;

	private ExecutorService workerExecutor = null;

	public HttpServer(int port) {
		this.port = port;
	}

	public void start() {

		this.bossExecutor = Executors.newCachedThreadPool();

		this.workerExecutor = Executors.newCachedThreadPool();

		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(this.bossExecutor,
						this.workerExecutor));
		bootstrap.setPipelineFactory(new HttpServerPipelineFactory());
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		this.channel = bootstrap.bind(new InetSocketAddress(this.port));
	}

	public void run() {
		start();
	}

	public void stop() {
		if (null != this.bossExecutor)
			this.bossExecutor.shutdownNow();
		if (null != this.workerExecutor)
			this.workerExecutor.shutdownNow();

		if (null != this.channel) {
			this.channel.unbind();
			this.channel.disconnect();
			this.channel.close();
		}
	}
}
