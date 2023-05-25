package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.model.request.telefono.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.telefono.TelefonoResponse;

@Mapper
public class TelefonoMapperRest {
	
	public TelefonoResponse fromDomainToAdapterRestMaria(Phone telefono) {
		return fromDomainToAdapterRest(telefono, "MariaDB");
	}
	public TelefonoResponse fromDomainToAdapterRestMongo(Phone telefono) {
		return fromDomainToAdapterRest(telefono, "MongoDB");
	}
	
	public TelefonoResponse fromDomainToAdapterRest(Phone telefono, String database) {
		return new TelefonoResponse(
				telefono.getNumber(), telefono.getCompany(), telefono.getOwner().getIdentification(), 
				database,
				"OK");
	}

	public Phone fromAdapterToDomain(TelefonoRequest request) {
		Phone telefono = new Phone();
		telefono.setNumber(request.getPhoneNumber());
		telefono.setCompany(request.getCellProvider());
		//telefono.setOwner(request.getIdOwner());
		return telefono;
	}
		
}
