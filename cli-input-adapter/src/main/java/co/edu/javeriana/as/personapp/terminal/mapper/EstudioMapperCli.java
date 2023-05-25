package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.model.EstudioModelCli;

@Mapper
public class EstudioMapperCli {
    
    public Study fromAdapterCliToDomain(EstudioModelCli persona){
		Study person=new Study();
		person.setGraduationDate(persona.getGraduationDate());
		person.setUniversityName(persona.getUniversityName());
		//person.setProfession(persona.getProfesion());
        //person.setPerson(persona.getPersona());
		return person;
	}

	public EstudioModelCli fromDomainToAdapterCli(Study person) {
		EstudioModelCli personaModelCli = new EstudioModelCli();
		personaModelCli.setGraduationDate(person.getGraduationDate());
		personaModelCli.setUniversityName(person.getUniversityName());
		//personaModelCli.setProfesion(person.getProfession());
		//personaModelCli.setPersona(person.getPerson());
		return personaModelCli;
	}
}
