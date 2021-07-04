package one.digitalInnovation.personapi.utils;
import java.util.Collections;

import one.digitalInnovation.personapi.dto.request.PersonDto;
import one.digitalInnovation.personapi.model.Person;

public class PersonUtils {

    private static final String FIRST_NAME = "Pedro";
    private static final String LAST_NAME = "Mateus";
    private static final String CPF_NUMBER = "999.999.999-99";
    private static final long PERSON_ID = 1L;
    //public static final LocalDate BIRTH_DATE = LocalDate.of(2010, 10, 1);

    public static PersonDto createFakeDTO() {
        final PersonDto personDto = PersonDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .phone(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
        return personDto;
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .phone(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }
}
