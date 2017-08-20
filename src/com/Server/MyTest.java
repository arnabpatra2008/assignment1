package com.Server;

import java.util.Properties;

import com.Server.Http.LocalHttpServer;
import com.Server.Kakfa.KafkaLocal;

public class MyTest {

	
	static KafkaLocal kafka;
	 
	
	public static void startKafka(){
		Properties kafkaProperties = new Properties();
		Properties zkProperties = new Properties();
		
		try {
			//load properties
			//zkProperties.load(Class.class.getResourceAsStream("/zklocal.properties"));
			
			
			zkProperties.setProperty("tickTime","2000");
			zkProperties.setProperty("initLimit","10");
			zkProperties.setProperty("syncLimit","5");
			zkProperties.setProperty("dataDir","C:\\zookeeper-3.5.3-beta\\data");
			zkProperties.setProperty("clientPort","2181");
			
			
			
			//kafkaProperties.load(Class.class.getResourceAsStream("/kafkalocal.properties"));
			kafkaProperties.setProperty("hostname", "localhost"); 
			kafkaProperties.setProperty("port", "9092"); 
			//kafkaProperties.setProperty("broker.id", "0"); 
			//kafkaProperties.setProperty("log.dir", "C:\\kafka_2.11-0.11.0.0\\logs"); 
		    //props.setProperty("enable.zookeeper", "false"); 
			//kafkaProperties.setProperty("zookeeper.connect", "localhost:2181"); 
			//flush every message. 
			//kafkaProperties.setProperty("log.flush.interval", "1"); 
			//flush every 1ms 
			//kafkaProperties.setProperty("log.default.flush.scheduler.interval.ms", "1"); 
			
			kafkaProperties.setProperty("broker.id", "0");
			kafkaProperties.setProperty("num.network.threads", "3");
			kafkaProperties.setProperty("num.io.threads", "8");
			kafkaProperties.setProperty("socket.send.buffer.bytes", "102400");
			kafkaProperties.setProperty("socket.receive.buffer.bytes", "102400");
			kafkaProperties.setProperty("socket.request.max.bytes", "104857600");
			kafkaProperties.setProperty("log.dirs", "C:\\kafka_2.11-0.11.0.0\\kafka-logs");
			kafkaProperties.setProperty("num.partitions", "1");
			kafkaProperties.setProperty("num.recovery.threads.per.data.dir", "1");
			kafkaProperties.setProperty("offsets.topic.replication.factor", "1");
			kafkaProperties.setProperty("transaction.state.log.replication.factor", "1");
			kafkaProperties.setProperty("transaction.state.log.min.isr", "1");
			kafkaProperties.setProperty("log.flush.interval.messages", "10000");
			kafkaProperties.setProperty("log.flush.interval.ms", "1000");
			kafkaProperties.setProperty("log.retention.hours", "168");
			kafkaProperties.setProperty("log.retention.bytes", "1073741824");
			kafkaProperties.setProperty("log.segment.bytes", "1073741824");
			kafkaProperties.setProperty("log.retention.check.interval.ms", "300000");
			kafkaProperties.setProperty("zookeeper.connect", "localhost:2181/kafka");
			kafkaProperties.setProperty("zookeeper.connection.timeout.ms", "6000");
			kafkaProperties.setProperty("group.initial.rebalance.delay.ms", "0");
			
			//start kafka
			kafka = new KafkaLocal(kafkaProperties);
			
			Thread.sleep(10000);
			
			
			
		} catch (Exception e){
			e.printStackTrace(System.out);
			
			e.printStackTrace(System.out);
		}
		
		//do other things
	}
 
	
	public void testSomething() {
	}
	
}
