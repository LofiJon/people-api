package one.digitalInnovation.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalInnovation.personapi.dto.MessageDto;
import one.digitalInnovation.personapi.model.Person;
import one.digitalInnovation.personapi.repository.PersonRepository;

@Service
public class PersonService {
		
		private PersonRepository personRepository;
		
		@Autowired
		public PersonService(PersonRepository personRepository) {
			this.personRepository = personRepository;
		}
		
		public MessageDto createPeople(Person person) {
			Person personSave = personRepository.save(person);
			
			return MessageDto
					.builder()
					.message("Person DIO created with success! ID " 
					+ personSave.getId() 
					+ " Name: " + personSave.getFirstName())
					.build();
			
		}
}
