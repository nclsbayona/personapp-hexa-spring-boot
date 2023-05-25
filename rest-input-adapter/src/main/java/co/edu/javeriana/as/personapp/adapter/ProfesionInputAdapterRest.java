package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.ProfessionUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.mapper.ProfessionMapperRest;
import co.edu.javeriana.as.personapp.model.request.profesion.DeleteProfesionRequest;
import co.edu.javeriana.as.personapp.model.request.profesion.EditProfesionRequest;
import co.edu.javeriana.as.personapp.model.request.profesion.ProfesionRequest;
import co.edu.javeriana.as.personapp.model.response.profesion.ProfesionResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class ProfesionInputAdapterRest {

	@Autowired
	@Qualifier("professionOutputAdapterMaria")
	private ProfessionOutputPort profesionOutputPortMaria;

	@Autowired
	@Qualifier("professionOutputAdapterMongo")
	private ProfessionOutputPort profesionOutputPortMongo;

	@Autowired
	private ProfessionMapperRest profesionMapperRest;

	ProfessionInputPort professionInputPort;

	private String setProfesionOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			professionInputPort = new ProfessionUseCase(profesionOutputPortMaria);
			return DatabaseOption.MARIA.toString();
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			professionInputPort = new ProfessionUseCase(profesionOutputPortMongo);
			return  DatabaseOption.MONGO.toString();
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public List<ProfesionResponse> historial(String database) {
		log.info("Into historial TelefonoEntity in Input Adapter");
		try {
			if(setProfesionOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return professionInputPort.findAll().stream().map(profesionMapperRest::fromDomainToAdapterRestMaria)
						.collect(Collectors.toList());
			}else {
				return professionInputPort.findAll().stream().map(profesionMapperRest::fromDomainToAdapterRestMongo)
						.collect(Collectors.toList());
			}
			
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<ProfesionResponse>();
		}
	}

	public ProfesionResponse findOne(String database, Integer phoneNumber) {
		log.info("Into historial TelefonoEntity in Input Adapter");
		try {
			if(setProfesionOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return profesionMapperRest.fromDomainToAdapterRestMaria(professionInputPort.findOne(phoneNumber));
			}else {
				return profesionMapperRest.fromDomainToAdapterRestMongo(professionInputPort.findOne(phoneNumber));
			}
			
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public ProfesionResponse crearProfesion(ProfesionRequest request) {
		try {
			setProfesionOutputPortInjection(request.getDatabase());
			Profession phone = professionInputPort.create(profesionMapperRest.fromAdapterToDomain(request));
			return profesionMapperRest.fromDomainToAdapterRestMaria(phone);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
		}
		return null;
	}

	public ProfesionResponse editarProfesion(EditProfesionRequest request) {
		try {
			setProfesionOutputPortInjection(request.getDatabase());
			Profession phone = professionInputPort.edit(request.getIdentification(), profesionMapperRest.fromAdapterToDomain(request));
			return profesionMapperRest.fromDomainToAdapterRestMaria(phone);
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
		}
		return null;
	}

	public Boolean borrarProfesion(DeleteProfesionRequest request) {
		try {
			setProfesionOutputPortInjection(request.getDatabase());
			return professionInputPort.drop(request.getIdentification());
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
		}
		return null;
	}
}
