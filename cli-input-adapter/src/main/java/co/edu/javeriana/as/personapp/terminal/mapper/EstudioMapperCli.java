package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.model.EstudioModelCli;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import co.edu.javeriana.as.personapp.terminal.model.ProfesionModelCli;

@Mapper
public class EstudioMapperCli {
    
    public Study fromAdapterCliToDomain(EstudioModelCli estudio, Profession profession, Person person){
		Study study=new Study();
		study.setGraduationDate(estudio.getGraduationDate());
		study.setUniversityName(estudio.getUniversityName());
		study.setProfession(profession);
        study.setPerson(person);
		return study;
	}

	public EstudioModelCli fromDomainToAdapterCli(Study person, PersonaModelCli personaModelCli, ProfesionModelCli profesionModelCli) {
		EstudioModelCli estudioModelCli = new EstudioModelCli();
		estudioModelCli.setGraduationDate(person.getGraduationDate());
		estudioModelCli.setUniversityName(person.getUniversityName());
		estudioModelCli.setPersona(personaModelCli);
		estudioModelCli.setProfesion(profesionModelCli);
		return estudioModelCli;
	}
}
