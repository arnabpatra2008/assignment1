package com.Server.Kakfa.Comm.Producer;

import java.util.Properties;



import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import kafka.producer.ProducerConfig;

public class LocalKafkaProducer {
	
	
	
public LocalKafkaProducer(com.poroto.PersonList.PersonProto.Person personBuilder) {
		
		Properties props = new Properties();
		
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
		      
		      Producer<String, String> producer = new KafkaProducer<String, String>(props);
		
		    //  System.out.println(personBuilder.toString());
		      
		      producer.send(new ProducerRecord<String, String>("test", personBuilder.toString()));
		      
		   //   System.out.println("MESSEGE SUCCESSFULLY SENT");
		      
	
	     // for(int i = 0; i < 1000; i++)
	    	//  producer.send(new ProducerRecord<String, String>("test", personBuilder.toString(),Integer.toString(i)));
//		                System.out.println("Message sent successfully");
//		                producer.close();
	}
	
	
		
	}
	
	


