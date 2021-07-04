package one.digitalInnovation.personapi.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import one.digitalInnovation.personapi.dto.request.PersonDto;
import one.digitalInnovation.personapi.dto.response.MessageDto;
import one.digitalInnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalInnovation.personapi.service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto getBook(@RequestBody PersonDto personDto){
        return personService.createPerson(personDto);

    }

    //listando pessoas
    @GetMapping
    public List<PersonDto> listAll(){
        return personService.listAll();
    }

    //buscando pelo id
    @GetMapping("/{id}")
    public PersonDto findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    //deletando
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }

    //Atualizando
    @PutMapping("/{id}")
    public MessageDto update(@RequestBody PersonDto personDto, @PathVariable @Valid Long id) throws PersonNotFoundException {
        return personService.update(personDto, id);
    }

}