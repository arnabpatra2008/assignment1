package com;

import kafka.tools.ConsoleConsumer;

import com.Server.Http.LocalHttpServer;
import com.Server.Kakfa.LocalKafkaServerExtConsole;
import com.Server.Kakfa.Comm.Consumer.*;
import com.Server.MyTest;

public class StartLocalServers {


	
	public StartLocalServers() throws InterruptedException {
		
		new LocalHttpServer().start();
		Thread.sleep(5000);


		//Thread.sleep(5000);

		 new MyTest().startKafka();
		Thread.sleep(5000);

		new ConsumerGroup().start();

		 
	}

	public static void main(String[] args) {



		
		try {
			new StartLocalServers();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
