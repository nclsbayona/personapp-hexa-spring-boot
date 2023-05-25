package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;

@Mapper
public class TelefonoMapperCli {
    
    public Phone fromAdapterCliToDomain(TelefonoModelCli persona){
		Phone person=new Phone();
        person.setNumber(persona.getNumero());
        person.setCompany(persona.getCompania());
        //person.setOwner(persona.getPersona());
		return person;
	}

	public TelefonoModelCli fromDomainToAdapterCli(Phone person) {
		TelefonoModelCli personaModelCli = new TelefonoModelCli();
		personaModelCli.setNumero(person.getNumber());
		personaModelCli.setCompania(person.getCompany());
		//personaModelCli.setOwner(person.getOwner());
		return personaModelCli;
	}
}
