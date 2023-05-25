package co.edu.javeriana.as.personapp.mariadb.adapter;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntityPK;
import co.edu.javeriana.as.personapp.mariadb.mapper.EstudiosMapperMaria;
import co.edu.javeriana.as.personapp.mariadb.repository.EstudiosRepositoryMaria;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter("studyOutputAdapterMaria")
@Transactional
public class StudyOutputAdapterMaria implements StudyOutputPort{

    @Autowired
	private EstudiosRepositoryMaria estudioRepositoryMaria;

	@Autowired
	private EstudiosMapperMaria estudioMapperMaria;
    
    @Override
    public Study save(Study study) {
        log.debug("Into save on Adapter MariaDB");
		EstudiosEntity persistedTelefono = estudioRepositoryMaria.save(estudioMapperMaria.fromDomainToAdapter(study));
		return estudioMapperMaria.fromAdapterToDomain(persistedTelefono);
    }

    @Override
    public Boolean delete(Integer identification_profession, Integer identification_person) {
        log.debug("Into delete on Adapter MariaDB");
        EstudiosEntityPK identification=new EstudiosEntityPK(identification_profession, identification_person);
        estudioRepositoryMaria.deleteById(identification);
		return estudioRepositoryMaria.findById(identification).isEmpty();
    }

    @Override
    public List<Study> find() {
        log.debug("Into find on Adapter MariaDB");
        return estudioRepositoryMaria.findAll().stream().map(estudioMapperMaria::fromAdapterToDomain).collect(Collectors.toList());
    }

    @Override
    public Study findById(Integer identification_profession, Integer identification_person) {
        log.debug("Into findById on Adapter MariaDB");
        EstudiosEntityPK identification=new EstudiosEntityPK(identification_profession, identification_person);
        if (estudioRepositoryMaria.findById(identification).isEmpty()) {
			return null;
		} else {
			return estudioMapperMaria.fromAdapterToDomain(estudioRepositoryMaria.findById(identification).get());
		}
    }
}
