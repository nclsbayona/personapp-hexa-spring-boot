package co.edu.javeriana.as.personapp.mapper;

import java.util.List;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.profesion.ProfesionRequest;
import co.edu.javeriana.as.personapp.model.response.profesion.ProfesionResponse;

@Mapper
public class ProfessionMapperRest {
	
	public ProfesionResponse fromDomainToAdapterRestMaria(Profession person) {
		return fromDomainToAdapterRest(person, "MariaDB");
	}
	public ProfesionResponse fromDomainToAdapterRestMongo(Profession person) {
		return fromDomainToAdapterRest(person, "MongoDB");
	}
	
	public ProfesionResponse fromDomainToAdapterRest(Profession person, String database) {
		return new ProfesionResponse(
				person.getIdentification(), 
				person.getName(), 
				person.getDescription(),
				database,
				"OK");
	}

	public Profession fromAdapterToDomain(ProfesionRequest request, List<Study> studies) {
		Profession person = new Profession();
		person.setIdentification(request.getIdentification());
		person.setName(request.getName());
		person.setDescription(request.getDescription());
		person.setStudies(studies);
		return person;
	}
		
}
