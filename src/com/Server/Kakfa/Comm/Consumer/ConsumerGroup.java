package com.Server.Kakfa.Comm.Consumer;

import java.util.Properties;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ConsumerGroup {
	
//   public static void main(String[] args) throws Exception
//   {
//
//	   new ConsumerGroup().start();
//
//   }
   
   public void start()
   {

	   System.out.println("STARTING CONSUMER GROUP SERVER : ");

	   String topic = "test";
	   String group = "test-consumer-group";
	   
	   Properties props = new Properties();
	     props.put("bootstrap.servers", "localhost:9092");
//	     props.put("group.id", "test-consumer-group");
//	     props.put("zookeeper.connect", "localhost:2181");
//	     props.put("zookeeper.connection.timeout.ms", "6000");

	   props.put("zookeeper.connect", "localhost:2181");
	   props.put("group.id", "test-consumer-group");
	   props.put("zookeeper.session.timeout.ms", "400");
	   props.put("zookeeper.sync.time.ms", "300");
	   props.put("auto.commit.interval.ms", "1000");
	  
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	     
      
      consumer.subscribe(Arrays.asList(topic));

      int i = 0;
         
      while (true)
	      {
	         ConsumerRecords<String, String> records = consumer.poll(100);

	            for (ConsumerRecord<String, String> record : records)
		            {
		            	//System.out.println("offset = "+record.offset()+", key = "+record.key()+", value = "+record.value());

		            	System.out.println(" FROM KAFKA CONSUMER RECORD - "+"RECORD = "+record+" : key = "+record.key()+", value = "+record.value());
		            }
	               
	      }
   }
}
