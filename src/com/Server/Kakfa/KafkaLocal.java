package com.Server.Kakfa;

import java.io.IOException;
import java.util.Properties;

import com.Server.Zookeeper.ZooKeeperLocal;

import kafka.server.KafkaConfig;
import kafka.server.KafkaServerStartable;

public class KafkaLocal {
	
	public KafkaServerStartable kafka;
	public ZooKeeperLocal zookeeper;

	public KafkaLocal(Properties kafkaProperties) throws IOException, InterruptedException{

		KafkaWithOutZookeeperLocal(kafkaProperties);


	}
	
	public KafkaLocal(Properties kafkaProperties, Properties zkProperties) throws IOException, InterruptedException{

		KafkaWithZookeeperLocal(kafkaProperties,zkProperties);


	}

	private void KafkaWithOutZookeeperLocal(Properties kafkaProperties) throws IOException, InterruptedException{
		KafkaConfig kafkaConfig = new KafkaConfig(kafkaProperties);


		//start local kafka broker
		System.out.println("starting local kafka broker...");
		kafka = new KafkaServerStartable(kafkaConfig);

		kafka.startup();
		System.out.println("local kafka broker started");


	}

	private void KafkaWithZookeeperLocal(Properties kafkaProperties, Properties zkProperties) throws IOException, InterruptedException{
		KafkaConfig kafkaConfig = new KafkaConfig(kafkaProperties);

		//start local zookeeper
		System.out.println("starting local zookeeper...");
		zookeeper = new ZooKeeperLocal(zkProperties);
		System.out.println("zookeeper server started");

		Thread.sleep(15000);
		//start local kafka broker
		System.out.println("starting local kafka broker...");
		kafka = new KafkaServerStartable(kafkaConfig);

		kafka.startup();
		System.out.println("local kafka broker started");
	}
	
	
	public void stop(){
		//stop kafka broker
		System.out.println("stopping kafka...");
		kafka.shutdown();
		System.out.println("done");
	}

}
