package one.digitalInnovation.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import one.digitalInnovation.personapi.dto.request.PersonDto;
import one.digitalInnovation.personapi.dto.response.MessageDto;
import one.digitalInnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalInnovation.personapi.mapper.PersonMapper;
import one.digitalInnovation.personapi.model.Person;
import one.digitalInnovation.personapi.repository.PersonRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private PersonRepository personRespository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageDto createPerson(PersonDto personDto) {
        Person personToSave = personMapper.toModel(personDto);
        Person savedPerson = personRespository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with id: ");
    }

    public List<PersonDto> listAll() {
        List<Person> list=personRespository.findAll();
        return list.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDto findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExist(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        Person person = verifyIfExist(id);
        personRespository.delete(person);
    }

    private Person verifyIfExist(Long id) throws PersonNotFoundException {
        return personRespository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public MessageDto update(PersonDto personDTO, Long id) throws PersonNotFoundException {
        verifyIfExist(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatePerson = personRespository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "updated person with id: ");


    }

    private MessageDto createMessageResponse(Long id, String s) {
        return MessageDto.builder().message(s + id).build();
    }
}