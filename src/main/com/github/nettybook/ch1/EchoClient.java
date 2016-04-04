package com.github.nettybook.ch1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoClient {
	public static void main(String[] args) throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(bossGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch){
						ChannelPipeline p = ch.pipeline();
						p.addLast(new EchoClientHandler());
					}
				});
			
			ChannelFuture f = b.connect("localhost", 8888).sync();
			
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
		}
	}
}
