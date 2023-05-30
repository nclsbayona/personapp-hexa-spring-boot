package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;

@Mapper
public class TelefonoMapperCli {
    
    public Phone fromAdapterCliToDomain(TelefonoModelCli telefono, Person person){
		Phone phone=new Phone();
        phone.setNumber(telefono.getNumero());
        phone.setCompany(telefono.getCompania());
        phone.setOwner(person);
		return phone;
	}

	public TelefonoModelCli fromDomainToAdapterCli(Phone phone, PersonaModelCli person) {
		TelefonoModelCli personaModelCli = new TelefonoModelCli();
		personaModelCli.setNumero(phone.getNumber());
		personaModelCli.setCompania(phone.getCompany());
		personaModelCli.setPersona(person);
		return personaModelCli;
	}
}
