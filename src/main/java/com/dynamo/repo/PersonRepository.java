package com.dynamo.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.dynamo.entity.Person;

@Repository
public class PersonRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	// this is the method that will save a person
	public Person save(Person person) {
		dynamoDBMapper.save(person);
		return person;
	}

	// method to get a person by its id
	public Person findbyId(String id) {
		return dynamoDBMapper.load(Person.class, id);
	}

	// a method to show all person in the db
	public List<Person> findAll() {
		return dynamoDBMapper.scan(Person.class, new DynamoDBScanExpression());
	}

	// to update the value of a person
	public String update(String id, Person person) {
		dynamoDBMapper.save(person, new DynamoDBSaveExpression().withExpectedEntry("id",
				new ExpectedAttributeValue(new AttributeValue().withS(id))));
		return id;
	}
	// method to delete a person 
	public String delete(String id) {
		Person person = dynamoDBMapper.load(Person.class, id);
		dynamoDBMapper.delete(person);
		return "This person has been sucessfully deleted with id : " + id;
	}
	

}
