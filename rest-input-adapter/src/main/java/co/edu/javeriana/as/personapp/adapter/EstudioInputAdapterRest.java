package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mapper.EstudioMapperRest;
import co.edu.javeriana.as.personapp.model.request.estudio.DeleteEstudioRequest;
import co.edu.javeriana.as.personapp.model.request.estudio.EditEstudioRequest;
import co.edu.javeriana.as.personapp.model.request.estudio.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.estudio.EstudioResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class EstudioInputAdapterRest {

	@Autowired
	@Qualifier("studyOutputAdapterMaria")
	private StudyOutputPort studyOutputPortMaria;

	@Autowired
	@Qualifier("studyOutputAdapterMongo")
	private StudyOutputPort studyOutputPortMongo;

	@Autowired
	private EstudioMapperRest estudioMapperRest;

	StudyInputPort studyInputPort;

	private String setStudyOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			studyInputPort = new StudyUseCase(studyOutputPortMaria);
			return DatabaseOption.MARIA.toString();
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			studyInputPort = new StudyUseCase(studyOutputPortMongo);
			return  DatabaseOption.MONGO.toString();
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public List<EstudioResponse> historial(String database) {
		log.info("Into historial EstudioEntity in Input Adapter");
		try {
			if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return studyInputPort.findAll().stream().map(estudioMapperRest::fromDomainToAdapterRestMaria)
						.collect(Collectors.toList());
			}else {
				return studyInputPort.findAll().stream().map(estudioMapperRest::fromDomainToAdapterRestMongo)
						.collect(Collectors.toList());
			}
			
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<EstudioResponse>();
		}
	}

	public EstudioResponse getOneAsAdapter(String database, Integer identificationPerson, Integer identificationProfession) {
		log.info("Into historial PersonaEntity in Input Adapter");
		try {
			if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return estudioMapperRest.fromDomainToAdapterRestMaria(studyInputPort.findOne(identificationPerson, identificationProfession));
			}else {
				return estudioMapperRest.fromDomainToAdapterRestMongo(studyInputPort.findOne(identificationPerson, identificationProfession));
			}
			
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public Study getOneAsDomain(String database, Integer identificationPerson, Integer identificationProfession) {
		log.info("Into historial PersonaEntity in Input Adapter");
		try {
			if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return studyInputPort.findOne(identificationPerson, identificationProfession);
			}else {
				return studyInputPort.findOne(identificationPerson, identificationProfession);
			}
			
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public EstudioResponse crearEstudio(EstudioRequest request) {
		try {
			setStudyOutputPortInjection(request.getDatabase());
			Study person = studyInputPort.create(estudioMapperRest.fromAdapterToDomain(request));
			return estudioMapperRest.fromDomainToAdapterRestMaria(person);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
		}
		return null;
	}

	public EstudioResponse editarEstudio(EditEstudioRequest request) {
		try {
			setStudyOutputPortInjection(request.getDatabase());
			Study person = studyInputPort.edit(request.getProfessionIdentification(), request.getPersonIdentification(), estudioMapperRest.fromAdapterToDomain(request));
			return estudioMapperRest.fromDomainToAdapterRestMaria(person);
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
		}
		return null;
	}

	public Boolean borrarEstudio(DeleteEstudioRequest request) {
		try {
			setStudyOutputPortInjection(request.getDatabase());
			return studyInputPort.drop(request.getProfessionIdentification(), request.getPersonIdentification());
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
		}
		return null;
	}
}
