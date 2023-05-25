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
	
	public EstudioResponse fromDomainToAdapterRest(Study telefono, String database) {
		return new EstudioResponse(
			telefono.getPerson().getIdentification(), telefono.getProfession().getIdentification(), telefono.getUniversityName(), telefono.getGraduationDate(),
				database,
				"OK");
	}

	public Study fromAdapterToDomain(EstudioRequest request, Person owner, Profession profesion) {
		Study telefono = new Study();
		telefono.setUniversityName(request.getUniversityName());
		telefono.setGraduationDate(request.getGraduationDate());
		telefono.setPerson(owner);
		telefono.setProfession(profesion);
		return telefono;
	}
		
}
