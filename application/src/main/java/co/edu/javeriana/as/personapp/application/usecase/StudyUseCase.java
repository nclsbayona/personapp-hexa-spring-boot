package co.edu.javeriana.as.personapp.application.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
public class StudyUseCase implements StudyInputPort {

	private StudyOutputPort studyPersistence;

	private PersonOutputPort personPersistence;

	private ProfessionOutputPort professionPersistence;

	public StudyUseCase(@Qualifier("studyOutputAdapterMaria") StudyOutputPort studyPersistence,
			@Qualifier("personOutputAdapterMaria") PersonOutputPort personPersistence,
			@Qualifier("professionOutputAdapterMaria") ProfessionOutputPort professionPersistence) {
		this.studyPersistence = studyPersistence;
		this.personPersistence = personPersistence;
		this.professionPersistence = professionPersistence;
	}

	@Override
	public void setPersistence(StudyOutputPort studyPersistence) {
		this.studyPersistence = studyPersistence;
	}

	@Override
	public Study create(Study study) {
		log.debug("Into create on Application Domain");
		return studyPersistence.save(study);
	}

	@Override
	public Study edit(Integer identification_profession, Integer identification_person, Study study)
			throws NoExistException {
		Study oldStudy = studyPersistence.findById(identification_profession, identification_person);
		if (oldStudy != null)
			return studyPersistence.save(study);
		throw new NoExistException("The study with id : profession-" + identification_profession + " and person-"
				+ identification_person + " does not exist into db, cannot be found");
	}

	@Override
	public Boolean drop(Integer identification_profession, Integer identification_person) throws NoExistException {
		Study oldStudy = studyPersistence.findById(identification_profession, identification_person);
		if (oldStudy != null)
			return studyPersistence.delete(identification_profession, identification_person);
		throw new NoExistException("The study with id : profession-" + identification_profession + " and person-"
				+ identification_person + " does not exist into db, cannot be found");
	}

	@Override
	public List<Study> findAll() {
		log.info("Output: " + studyPersistence.getClass());
		return studyPersistence.find();
	}

	@Override
	public Study findOne(Integer identification_profession, Integer identification_person) throws NoExistException {
		Study oldStudy = studyPersistence.findById(identification_profession, identification_person);
		if (oldStudy != null)
			return oldStudy;
		throw new NoExistException("The study with id : profession-" + identification_profession + " and person-"
				+ identification_person + " does not exist into db, cannot be found");
	}

	@Override
	public Integer count() {
		return findAll().size();
	}

	@Override
	public Person getPerson(Integer identification_person) throws NoExistException {
		Person person = personPersistence.findById(identification_person);
		if (person != null)
			return person;
		throw new NoExistException(
				"The person with id " + identification_person + " does not exist into db, cannot be found");
	}

	@Override
	public Profession getProfession(Integer identification_profession) throws NoExistException {
		Profession profession = professionPersistence.findById(identification_profession);
		if (profession != null)
			return profession;
		throw new NoExistException("The study with id : profession-" + identification_profession
				+ " does not exist into db, cannot be found");
	}

}
