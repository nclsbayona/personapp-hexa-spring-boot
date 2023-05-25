package co.edu.javeriana.as.personapp.application.port.in;

import java.util.List;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;

@Port
public interface PhoneInputPort {

    public void setPersistence(PhoneOutputPort personPersistence);
	
	public Phone create(Phone person);

	public Phone edit(Integer identification, Phone phone) throws NoExistException;

	public Boolean drop(Integer identification) throws NoExistException;

	public List<Phone> findAll();

	public Phone findOne(Integer identification) throws NoExistException;

    public Integer count();

    public Person getPerson(Integer identification) throws NoExistException;
}
