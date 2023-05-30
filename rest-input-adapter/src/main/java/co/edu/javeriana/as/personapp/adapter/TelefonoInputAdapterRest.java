package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mapper.TelefonoMapperRest;
import co.edu.javeriana.as.personapp.model.request.telefono.DeleteTelefonoRequest;
import co.edu.javeriana.as.personapp.model.request.telefono.EditTelefonoRequest;
import co.edu.javeriana.as.personapp.model.request.telefono.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.telefono.TelefonoResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class TelefonoInputAdapterRest {

	@Autowired
	@Qualifier("phoneOutputAdapterMaria")
	private PhoneOutputPort phoneOutputPortMaria;

	@Autowired
	@Qualifier("phoneOutputAdapterMongo")
	private PhoneOutputPort phoneOutputPortMongo;

	@Autowired
	@Qualifier("personOutputAdapterMaria")
	private PersonOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("personOutputAdapterMongo")
	private PersonOutputPort personOutputPortMongo;

	@Autowired
	private TelefonoMapperRest telefonoMapperRest;

	PhoneInputPort phoneInputPort;

	private String setPhoneOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			phoneInputPort = new PhoneUseCase(phoneOutputPortMaria, personOutputPortMaria);
			return DatabaseOption.MARIA.toString();
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			phoneInputPort = new PhoneUseCase(phoneOutputPortMongo, personOutputPortMongo);
			return  DatabaseOption.MONGO.toString();
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public List<TelefonoResponse> historial(String database) {
		log.info("Into historial TelefonoEntity in Input Adapter");
		try {
			if(setPhoneOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return phoneInputPort.findAll().stream().map(telefonoMapperRest::fromDomainToAdapterRestMaria)
						.collect(Collectors.toList());
			}else {
				return phoneInputPort.findAll().stream().map(telefonoMapperRest::fromDomainToAdapterRestMongo)
						.collect(Collectors.toList());
			}
			
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<TelefonoResponse>();
		}
	}

	public TelefonoResponse findOne(String database, String phoneNumber) {
		log.info("Into historial TelefonoEntity in Input Adapter");
		try {
			if(setPhoneOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return telefonoMapperRest.fromDomainToAdapterRestMaria(phoneInputPort.findOne(phoneNumber));
			}else {
				return telefonoMapperRest.fromDomainToAdapterRestMongo(phoneInputPort.findOne(phoneNumber));
			}
			
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public TelefonoResponse crearTelefono(TelefonoRequest request) {
		try {
			setPhoneOutputPortInjection(request.getDatabase());
			Person person = phoneInputPort.getPerson(request.getIdOwner());
			Phone phone = phoneInputPort.create(telefonoMapperRest.fromAdapterToDomain(request, person));
			return telefonoMapperRest.fromDomainToAdapterRestMaria(phone);
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
		}
		return null;
	}

	public TelefonoResponse editarTelefono(EditTelefonoRequest request) {
		try {
			setPhoneOutputPortInjection(request.getDatabase());
			Person person = phoneInputPort.getPerson(request.getIdOwner());;
			Phone phone = phoneInputPort.edit(request.getPhoneNumber(), telefonoMapperRest.fromAdapterToDomain(request, person));
			return telefonoMapperRest.fromDomainToAdapterRestMaria(phone);
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
		}
		return null;
	}

	public Boolean borrarTelefono(DeleteTelefonoRequest request) {
		try {
			setPhoneOutputPortInjection(request.getDatabase());
			return phoneInputPort.drop(request.getPhoneNumber());
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
		}
		return null;
	}
}
