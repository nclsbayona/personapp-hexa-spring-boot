package co.edu.javeriana.as.personapp.terminal.mapper;

import java.util.List;
import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.model.ProfesionModelCli;

@Mapper
public class ProfesionMapperCli {

	public Profession fromAdapterCliToDomain(ProfesionModelCli persona, List<Study> studies){
		Profession person=new Profession();
		person.setIdentification(persona.getIdentification());
		person.setName(persona.getNombre());
		person.setDescription(persona.getDescripcion());
		person.setStudies(studies);
		return person;
	}

	public ProfesionModelCli fromDomainToAdapterCli(Profession person) {
		ProfesionModelCli personaModelCli = new ProfesionModelCli();
		personaModelCli.setIdentification(person.getIdentification());
		personaModelCli.setNombre(person.getName());
		personaModelCli.setDescripcion(person.getDescription());
		return personaModelCli;
	}
}
