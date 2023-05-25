package co.edu.javeriana.as.personapp.terminal.adapter;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.mapper.TelefonoMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class TelefonoInputAdapterCli {

	@Autowired
	@Qualifier("phoneOutputAdapterMaria")
	private PhoneOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("phoneOutputAdapterMongo")
	private PhoneOutputPort personOutputPortMongo;

	@Autowired
	private TelefonoMapperCli personaMapperCli;

	PhoneInputPort personInputPort;

	public void setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new PhoneUseCase(personOutputPortMaria);
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new PhoneUseCase(personOutputPortMongo);
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public void historial1() {
		log.info("Into historial PersonaEntity in Input Adapter");
		List<TelefonoModelCli> persona = personInputPort.findAll().stream().map(personaMapperCli::fromDomainToAdapterCli)
				.collect(Collectors.toList());
		persona.forEach(p -> System.out.println(p.toString()));
	}

	public void historial() {
		log.info("Into historial PersonaEntity in Input Adapter");
		personInputPort.findAll().stream()
				.map(personaMapperCli::fromDomainToAdapterCli)
				.forEach(System.out::println);
	}

	public void crear(Scanner sc) {
		log.info("Into crear PersonaEntity in Input Adapter");
		sc = new Scanner(System.in);
		// System.out.println("La identificacion actual: ");
		// String id = sc.nextLine();
		System.out.println("El numero nuevo: ");
		String identification = sc.nextLine();
		System.out.println("La compañia nueva: ");
		String nombre = sc.nextLine();
		System.out.println("El duenio nuevo: ");
		String apellido = sc.nextLine();
		Person person=null;
		Phone phone = new Phone(identification, nombre, person);
		phone = personInputPort.create(phone);
		System.out.println(personaMapperCli.fromDomainToAdapterCli(phone));
	}

	public void editar(Scanner sc) {
		log.info("Into editar PersonaEntity in Input Adapter");
		try {
			sc = new Scanner(System.in);
			System.out.println("El numero actual: ");
			String old_identification = sc.nextLine();
			System.out.println("El numero nuevo: ");
		String identification = sc.nextLine();
		System.out.println("La compañia nueva: ");
		String nombre = sc.nextLine();
		System.out.println("El duenio nuevo: ");
		String apellido = sc.nextLine();
		Person person=null;
		Phone phone = new Phone(identification, nombre, person);
		phone = personInputPort.edit(old_identification, phone);
		System.out.println(personaMapperCli.fromDomainToAdapterCli(phone));
		} catch (NoExistException e) {
			log.warn(e.getMessage());
		}
	}

	public void eliminar(Scanner sc) {
		log.info("Into eliminar PersonaEntity in Input Adapter");
		try {
			sc = new Scanner(System.in);
			System.out.println("La identificacion actual: ");
			String old_identification = sc.nextLine();
			System.out.println(personInputPort.drop(old_identification));
		} catch (NoExistException e) {
			log.warn(e.getMessage());
		}
	}

}
