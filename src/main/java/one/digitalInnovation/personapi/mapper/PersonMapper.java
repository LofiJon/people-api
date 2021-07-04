package one.digitalInnovation.personapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import one.digitalInnovation.personapi.dto.request.PersonDto;
import one.digitalInnovation.personapi.model.Person;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE= Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate",source = "birthDate",dateFormat = "dd/MM/yyyy")
    Person toModel(PersonDto personDto);

    PersonDto toDTO(Person person);
}