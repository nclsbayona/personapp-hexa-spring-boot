package co.edu.javeriana.as.personapp.application.port.in;

import java.util.List;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;

@Port
public interface StudyInputPort {

    public void setPersistence(StudyOutputPort studyPersistence);
	
	public Study create(Study person);

	public Study edit(Integer identification_profession, Integer identification_person,  Study study) throws NoExistException;

	public Boolean drop(Integer identification_profession, Integer identification_person) throws NoExistException;

	public List<Study> findAll();

	public Study findOne(Integer identification_profession, Integer identification_person) throws NoExistException;
    
    public Integer count();

    public Person getPerson(Integer identification_profession, Integer identification_person) throws NoExistException;

	public Profession getProfession(Integer identification_profession, Integer identification_person) throws NoExistException;
}
