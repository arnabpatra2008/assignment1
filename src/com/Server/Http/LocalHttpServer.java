package com.Server.Http;

import static io.netty.buffer.Unpooled.copiedBuffer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.Person.Person;
import com.google.gson.Gson;
import com.poroto.PersonList.PersonProto;
import com.poroto.PersonList.PersonProtoBuilder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpVersion;

public class LocalHttpServer {
	
		  
	  private ChannelFuture channel;
	    private final EventLoopGroup masterGroup;
	    private final EventLoopGroup slaveGroup;
	    
	    private final Gson gs = new Gson();
	    private Person person;
	    
	    public LocalHttpServer()
	    {
	        masterGroup = new NioEventLoopGroup();
	        slaveGroup = new NioEventLoopGroup();        
	    }

	    public void start()
	    {
	        Runtime.getRuntime().addShutdownHook(new Thread(){
	            @Override
	            public void run() { shutdown(); }
	        });
	        
	        try
	        {
	            final ServerBootstrap bootstrap =
	                new ServerBootstrap()
	                    .group(masterGroup, slaveGroup)
	                    .channel(NioServerSocketChannel.class)
	                    .childHandler(new ChannelInitializer<SocketChannel>(){
	                        @Override
	                        public void initChannel(final SocketChannel ch) throws Exception
	                        {
	                            ch.pipeline().addLast("codec", new HttpServerCodec());
	                            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(512*1024));
	                            ch.pipeline().addLast("request", new ChannelInboundHandlerAdapter()
	                            {
	                                @Override
	                                public void channelRead(ChannelHandlerContext ctx, Object msg)
	                                        throws Exception
	                                {
	                                    if (msg instanceof FullHttpRequest)
	                                    {
	                                        final FullHttpRequest request = (FullHttpRequest) msg;
	                                        
	                                     
	                                        /*BELOW IS THE CONTENT FOR HTML FILE READER */
	                                        /*--STARTS--*/
	                                        
	                                      //System.out.println("BEFORE");
	                                      //System.out.println(request.getUri());
	                                      //System.out.println("intermission");
	                                      //System.out.println(msg);
	                                        
	                                        try
	                                        {
	                                        	ByteBuf data = request.content();
	                                        	 // System.out.println("POST/PUT length: " + data.readableBytes());
		  	                                     // System.out.println("POST/PUT as string: ");
		  	                                     // System.out.println("-- DATA --");
		  	                                  //   System.out.println(data.toString(StandardCharsets.UTF_8));
		  	                                     // System.out.println(gs.fromJson(data.toString(StandardCharsets.UTF_8), Person.class));
		  	                                  //   System.out.println(" Person DETAILS ");
		  	                                     person = gs.fromJson(data.toString(StandardCharsets.UTF_8), Person.class);
		  	                                  //   System.out.println("FIRST NAME : "+ person.getfirst_name());
		  	                                  //   System.out.println("LAST NAME : "+ person.getlast_name());
		  	                                     
		  	                                   new PersonProtoBuilder(person);
		  	                                     
	                                        }
	                                        catch(NullPointerException npe){System.out.println(npe);}
	                                        catch(Exception e){System.out.println(e);}
	                                        	 
	                                        
	                                       
	                                        
	                                      
	                                        
	                                        
	                                        
	                                        /*--ENDS--*/
	                                        
	                                        final String responseMessage = "<html><head></head><body><form><p><label for=\"first_name\">First Name:</label><input type=\"text\" name=\"first_name\" id=\"fname\"></p><p><label for=\"last_name\">Last Name:</label><input type=\"text\" name=\"last_name\" id=\"lname\"></p><input value=\"Click Me\" type=\"Button\" onclick=\"submitform2POST()\"></form><script>function submitform(){var myObj;myObj = {\"first_name\":document.getElementById(\"fname\").value,\"last_name\":document.getElementById(\"lname\").value};}function submitform2POST(){var xhr = new XMLHttpRequest();var url = \"http://localhost:8008\";xhr.open(\"POST\", url, true);xhr.setRequestHeader(\"Content-type\", \"application/json\");xhr.onreadystatechange = function () {if (xhr.readyState === 4 && xhr.status === 200){var json = JSON.parse(xhr.responseText);}};var data =JSON.stringify({\"first_name\":document.getElementById(\"fname\").value, \"last_name\":document.getElementById(\"lname\").value });xhr.send(data);}</script></body></html>";

	                                      //  final String responseMessage = fileReader();
	                                        
	                                        
	                                        FullHttpResponse response = new DefaultFullHttpResponse(
	                                            HttpVersion.HTTP_1_1,
	                                            HttpResponseStatus.OK,
	                                            copiedBuffer(responseMessage.getBytes())
	                                        );
	    
	                                        if (HttpHeaders.isKeepAlive(request))
	                                        {
	                                            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
	                                        }
	                                        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8");
	                                        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, responseMessage.length());
	                                        
	                                        ctx.writeAndFlush(response);
	                                        
	                                    }
	                                    else
	                                    {
	                                        super.channelRead(ctx, msg);
	                                    }
	                                }
	    
	                                @Override
	                                public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
	                                {
	                                    ctx.flush();
	                                }
	    
	                                @Override
	                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	                                        throws Exception
	                                {
	                                    ctx.writeAndFlush(new DefaultFullHttpResponse(
	                                        HttpVersion.HTTP_1_1,
	                                        HttpResponseStatus.INTERNAL_SERVER_ERROR,
	                                        copiedBuffer(cause.getMessage().getBytes())
	                                    ));
	                                }                                    
	                            });
	                        }
	                    })
	                    .option(ChannelOption.SO_BACKLOG, 128)
	                    .childOption(ChannelOption.SO_KEEPALIVE, true);
	            channel = bootstrap.bind(8008).sync();
	            //channels.add(bootstrap.bind(8080).sync());
	        }
	        catch (final InterruptedException e) { }
	    }
	    
	    public void shutdown()
	    {
	        slaveGroup.shutdownGracefully();
	        masterGroup.shutdownGracefully();

	        try
	        {
	            channel.channel().closeFuture().sync();
	        }
	        catch (InterruptedException e) { }
	    }
	    
	    
	    public String fileReader()
		{
			String returnString = null;
			try{
				String s;
				FileReader fr=new FileReader("H://Auzmor_project_supportings//index.html");
				BufferedReader br= new BufferedReader(fr);
				StringBuilder content=new StringBuilder(1024);
				while((s=br.readLine())!=null)
				    {
				    
					content.append(s);
				    } 

				    // System.out.println("content is"+content);
				     
				     returnString = content.toString();
				   }
				  catch(Exception ex)
				   {
					  
				    }
			
			return returnString;
		}

	    public static void main(String[] args)
	    {
	        new LocalHttpServer().start();
	    }
	}