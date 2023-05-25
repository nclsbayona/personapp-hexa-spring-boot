package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
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

	public Study fromAdapterToDomain(EstudioRequest request) {
		Study telefono = new Study();
		telefono.setUniversityName(request.getUniversityName());
		telefono.setGraduationDate(request.getGraduationDate());
		//telefono.setProfession(request.getProfessionIdentification());
		//telefono.setPerson(request.getPersonIdentification());
		return telefono;
	}
		
}
