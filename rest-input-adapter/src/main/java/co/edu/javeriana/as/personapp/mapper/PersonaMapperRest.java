package co.edu.javeriana.as.personapp.mapper;

import java.util.List;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.persona.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.persona.PersonaResponse;

@Mapper
public class PersonaMapperRest {
	
	public PersonaResponse fromDomainToAdapterRestMaria(Person person) {
		return fromDomainToAdapterRest(person, "MariaDB");
	}
	public PersonaResponse fromDomainToAdapterRestMongo(Person person) {
		return fromDomainToAdapterRest(person, "MongoDB");
	}
	
	public PersonaResponse fromDomainToAdapterRest(Person person, String database) {
		return new PersonaResponse(
				person.getIdentification()+"", 
				person.getFirstName(), 
				person.getLastName(), 
				person.getAge()+"", 
				person.getGender().toString(), 
				database,
				"OK");
	}

	public Person fromAdapterToDomain(PersonaRequest request, Gender gender, List<Study> studies, List<Phone> phoneNumbers) {
		Person person = new Person();
		person.setGender(gender);
		person.setAge(Integer.parseInt(request.getAge()));
		person.setFirstName(request.getFirstName());
		person.setLastName(request.getLastName());
		person.setIdentification(Integer.parseInt(request.getDni()));
		person.setStudies(studies);
		person.setPhoneNumbers(phoneNumbers);
		return person;
	}
		
}
