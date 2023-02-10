	package com.dynamo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamo.entity.Person;
import com.dynamo.repo.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	// mapping to save a person
	@PostMapping
	public Person save(@RequestBody Person person) {
		return personRepository.save(person);
	}

	// mapping to get a person by its id
	@GetMapping("/{id}")
	public Person findById(@PathVariable(value = "id") String id) {
		return personRepository.findbyId(id);

	}

	// to get a list of all person
	@GetMapping
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	// to update a person

	@PutMapping("/{id}")
	public String update(@PathVariable(value = "id") String id, @RequestBody Person person) {
		return personRepository.update(id, person);
	}

	// to delete a person
	@DeleteMapping("/{id}")
	public String delete(@PathVariable(value = "id") String id) {
		return personRepository.delete(id);
	}

}
