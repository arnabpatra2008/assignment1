package com.Server.Kakfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.kafka.common.utils.SystemTime;
import org.apache.kafka.connect.data.Time;

import kafka.server.KafkaConfig;
import kafka.api.FetchRequest; 
import kafka.javaapi.consumer.SimpleConsumer; 
import kafka.javaapi.message.ByteBufferMessageSet; 
 
import kafka.message.Message; 
import kafka.message.MessageAndOffset; 
import kafka.producer.ProducerConfig; 
import kafka.server.KafkaConfig;
import kafka.server.KafkaServer;
import kafka.server.KafkaServerStartable;


public class LocalKafkaServerExtConsole {
	
	
	private String KAFKA_DIR; 
    private static final int BATCH_SIZE = 10; 
    private static final int MAX_MESSAGE_SIZE = 500; 
    private static final int GOOD_MESSAGE_SIZE = 100; 
    private static final int BAD_MESSAGE_SIZE = 1000; 
    private static final int KAFKA_BROKER_ID = 0; 
    private static final int KAFKA_BROKER_PORT = 9090; 
    private static final String KAFKA_TOPIC = "test"; 
 
    private int messageNumber = 0; 
 
    private KafkaServerStartable server; 
	
	public LocalKafkaServerExtConsole() 
		{

			try 
		        { 
		            File f = new File("C:\\kafka_2.11-0.11.0.0\\bin\\windows");
		            ProcessBuilder pb = new ProcessBuilder("cmd", "/c","start","kafka-server-start.bat","C:\\kafka_2.11-0.11.0.0\\config\\server.properties");
		            
		            pb.directory(f);
		            Process p = pb.start();
		            
		            p.waitFor(); 
		           
		            System.out.println(p.getInputStream());
		
		        }
		        catch(IOException e1) {e1.printStackTrace();} 
		        catch(InterruptedException e2) {e2.printStackTrace();} 
		
		
		
		
	
		}
	
	
	public void startServer() { 
      
    }
	
	
	public static void main(String[] args) {


		new LocalKafkaServerExtConsole().startServer();

	}
	 
	 
	 
		
	}


