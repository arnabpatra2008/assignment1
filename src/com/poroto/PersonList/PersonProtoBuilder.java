package com.poroto.PersonList;

import java.io.FileOutputStream;
import java.io.IOException;

import com.Person.Person;
import com.Server.Kakfa.Comm.Producer.LocalKafkaProducer;


public class PersonProtoBuilder {
	
	private Person person;
	
	public PersonProtoBuilder(Person person) {
		
		AddPersion(person);
		
	}
	
	public void AddPersion(Person person)
	{
		
		System.out.println("FROM PersonProtoBuilder : "+person.getfirst_name() +" - "+person.getlast_name());
		
		PersonProto.Person personBuilder = PersonProto.Person.newBuilder()
																			.setFirstname(person.getfirst_name())
																			.setLastname(person.getlast_name())
																			.build();
		
		
		try {
				new LocalKafkaProducer(personBuilder);
		       // write
		      //  FileOutputStream output = new FileOutputStream("H:\\Auzmor_project_supportings\\person.txt");
		      //  personBuilder.writeTo(output);;
		      //  output.close();
		 
		       
		 
		    } 
		// 	catch (IOException e) {System.out.println(e);}
			catch (Exception e) {System.out.println(e);}
	
		
		
	}

}
