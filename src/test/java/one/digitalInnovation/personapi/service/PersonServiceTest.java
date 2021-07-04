package one.digitalInnovation.personapi.service;

import static one.digitalInnovation.personapi.utils.PersonUtils.createFakeDTO;
import static one.digitalInnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import one.digitalInnovation.personapi.dto.request.PersonDto;
import one.digitalInnovation.personapi.dto.response.MessageDto;
import one.digitalInnovation.personapi.mapper.PersonMapper;
import one.digitalInnovation.personapi.model.Person;
import one.digitalInnovation.personapi.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSuccessSavedMessage() {
        PersonDto personDto = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personMapper.toModel(personDto)).thenReturn(expectedSavedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageDto expectedSuccessMessage = createExpectedSuccessMessage(expectedSavedPerson.getId());
        MessageDto successMessage = personService.createPerson(personDto);

        assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageDto createExpectedSuccessMessage(Long savedPersonId) {
        return MessageDto.builder()
                .message("Person successfully created with ID " + savedPersonId)
                .build();
    }

}