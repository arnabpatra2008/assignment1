package com.Server.Kakfa.Comm.Consumer;

import java.io.File;
import java.io.IOException;

public class ConsoleKafkaConsumer {
	
	
	public ConsoleKafkaConsumer() {
		
		
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

}
