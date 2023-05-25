package co.edu.javeriana.as.personapp.terminal.adapter;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.mapper.PersonaMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class PersonaInputAdapterCli {

	@Autowired
	@Qualifier("personOutputAdapterMaria")
	private PersonOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("personOutputAdapterMongo")
	private PersonOutputPort personOutputPortMongo;

	@Autowired
	private PersonaMapperCli personaMapperCli;

	PersonInputPort personInputPort;

	public void setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMaria);
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMongo);
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public void historial1() {
		log.info("Into historial PersonaEntity in Input Adapter");
		List<PersonaModelCli> persona = personInputPort.findAll().stream().map(personaMapperCli::fromDomainToAdapterCli)
				.collect(Collectors.toList());
		persona.forEach(p -> System.out.println(p.toString()));
	}

	public void historial() {
		log.info("Into historial PersonaEntity in Input Adapter");
		personInputPort.findAll().stream()
				.map(personaMapperCli::fromDomainToAdapterCli)
				.forEach(System.out::println);
	}

	private Gender checkForGender(String gender) {
		if (gender.equalsIgnoreCase(Gender.MALE.toString())) {
			return Gender.MALE;
		} else if (gender.equalsIgnoreCase(Gender.FEMALE.toString())) {
			return Gender.FEMALE;
		} else if (gender.equalsIgnoreCase(Gender.OTHER.toString())) {
			return Gender.OTHER;
		}
		return null;
	}

	public void crear(Scanner sc) {
		log.info("Into crear PersonaEntity in Input Adapter");
		try {
			sc = new Scanner(System.in);
			// System.out.println("La identificacion actual: ");
			// String id = sc.nextLine();
			System.out.println("La identificacion nueva: ");
			String identification = sc.nextLine();
			System.out.println("El nombre nuevo: ");
			String nombre = sc.nextLine();
			System.out.println("El apellido nuevo: ");
			String apellido = sc.nextLine();
			System.out.println("El genero nuevo: ");
			String genero = sc.nextLine();
			System.out.println("La edad nueva: ");
			String edad = sc.nextLine();
			Gender g = checkForGender(genero);
			if (g == null)
				throw new InvalidOptionException("gender fail");
			Integer id = Integer.parseInt(identification);
			List<Phone> phones = null;
			try {
				phones = personInputPort.getPhones(id);
			} catch (NoExistException e) {
				log.warn(e.getMessage());
			}
			List<Study> studies = null;
			try {
				studies = personInputPort.getStudies(id);
			} catch (NoExistException e) {
				log.warn(e.getMessage());
			}
			Person person = new Person(id, nombre, apellido, g, Integer.parseInt(edad), phones, studies);
			person = personInputPort.create(person);
			System.out.println(personaMapperCli.fromDomainToAdapterCli(person));
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
		}
	}

	public void editar(Scanner sc) {
		log.info("Into editar PersonaEntity in Input Adapter");
		try {
			sc = new Scanner(System.in);
			System.out.println("La identificacion actual: ");
			String old_identification = sc.nextLine();
			System.out.println("La identificacion nueva: ");
			String identification = sc.nextLine();
			System.out.println("El nombre nuevo: ");
			String nombre = sc.nextLine();
			System.out.println("El apellido nuevo: ");
			String apellido = sc.nextLine();
			System.out.println("El genero nuevo: ");
			String genero = sc.nextLine();
			System.out.println("La edad nueva: ");
			String edad = sc.nextLine();
			Gender g = checkForGender(genero);
			if (g == null)
				throw new InvalidOptionException("gender fail");
			Integer id = Integer.parseInt(identification);
			List<Phone> phones = null;
			try {
				phones = personInputPort.getPhones(id);
			} catch (NoExistException e) {
				log.warn(e.getMessage());
			}
			List<Study> studies = null;
			try {
				studies = personInputPort.getStudies(id);
			} catch (NoExistException e) {
				log.warn(e.getMessage());
			}
			Person person = new Person(id, nombre, apellido, g, Integer.parseInt(edad), phones, studies);
			person = personInputPort.edit(Integer.parseInt(old_identification), person);
			System.out.println(personaMapperCli.fromDomainToAdapterCli(person));
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
		}
	}

	public void eliminar(Scanner sc) {
		log.info("Into eliminar PersonaEntity in Input Adapter");
		try {
			sc = new Scanner(System.in);
			System.out.println("La identificacion actual: ");
			String old_identification = sc.nextLine();
			System.out.println(personInputPort.drop(Integer.parseInt(old_identification)));
		} catch (NoExistException e) {
			log.warn(e.getMessage());
		}
	}

}
