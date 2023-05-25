package co.edu.javeriana.as.personapp.mongo.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mongo.document.EstudiosDocument;
import co.edu.javeriana.as.personapp.mongo.mapper.EstudiosMapperMongo;
import co.edu.javeriana.as.personapp.mongo.repository.EstudiosRepositoryMongo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter("studyOutputAdapterMongo")
public class StudyOutputAdapterMaria implements StudyOutputPort{

    @Autowired
	private EstudiosRepositoryMongo estudioRepositoryMongo;

	@Autowired
	private EstudiosMapperMongo estudioMapperMongo;
    
    @Override
    public Study save(Study study) {
        log.debug("Into save on Adapter MariaDB");
		EstudiosDocument persistedTelefono = estudioRepositoryMongo.save(estudioMapperMongo.fromDomainToAdapter(study));
		return estudioMapperMongo.fromAdapterToDomain(persistedTelefono);
    }

    @Override
    public Boolean delete(Integer identification_profession, Integer identification_person) {
        log.debug("Into delete on Adapter MariaDB");
        estudioRepositoryMongo.deleteById(identification_profession+"-"+identification_person);
		return estudioRepositoryMongo.findById(identification_profession+"-"+identification_person).isEmpty();
    }

    @Override
    public List<Study> find() {
        log.debug("Into find on Adapter MariaDB");
        return estudioRepositoryMongo.findAll().stream().map(estudioMapperMongo::fromAdapterToDomain).collect(Collectors.toList());
    }

    @Override
    public Study findById(Integer identification_profession, Integer identification_person) {
        log.debug("Into findById on Adapter MariaDB");
        if (estudioRepositoryMongo.findById(identification_profession+"-"+identification_person).isEmpty()) {
			return null;
		} else {
			return estudioMapperMongo.fromAdapterToDomain(estudioRepositoryMongo.findById(identification_profession+"-"+identification_person).get());
		}
    }
}
