package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.estudio.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.estudio.EstudioResponse;

@Mapper
public class EstudioMapperRest {
	
	public EstudioResponse fromDomainToAdapterRestMaria(Study telefono) {
		return fromDomainToAdapterRest(telefono, "MariaDB");
	}
	public EstudioResponse fromDomainToAdapterRestMongo(Study telefono) {
		return fromDomainToAdapterRest(telefono, "MongoDB");
	}
	
	public EstudioResponse fromDomainToAdapterRest(Study estudio, String database) {
		return new EstudioResponse(
			estudio.getPerson().getIdentification(), estudio.getProfession().getIdentification(), estudio.getUniversityName(), estudio.getGraduationDate(),
				database,
				"OK");
	}

	public Study fromAdapterToDomain(EstudioRequest request, Person person, Profession profession) {
		Study telefono = new Study();
		telefono.setUniversityName(request.getUniversityName());
		telefono.setGraduationDate(request.getGraduationDate());
		telefono.setProfession(profession);
		telefono.setPerson(person);
		return telefono;
	}
		
}
