package co.edu.javeriana.as.personapp.terminal.mapper;

import java.util.List;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;

@Mapper
public class PersonaMapperCli {


	public Person fromAdapterCliToDomain(PersonaModelCli persona, Gender gender, List<Phone> phones, List<Study> studies){
		Person person=new Person();
		person.setIdentification(persona.getCc());
		person.setFirstName(persona.getNombre());
		person.setLastName(persona.getApellido());
		person.setAge(persona.getEdad());
		person.setGender(gender);
		person.setPhoneNumbers(phones);
		person.setStudies(studies);
		return person;
	}

	public PersonaModelCli fromDomainToAdapterCli(Person person) {
		PersonaModelCli personaModelCli = new PersonaModelCli();
		personaModelCli.setCc(person.getIdentification());
		personaModelCli.setNombre(person.getFirstName());
		personaModelCli.setApellido(person.getLastName());
		personaModelCli.setGenero(person.getGender().toString());
		personaModelCli.setEdad(person.getAge());
		return personaModelCli;
	}
}
