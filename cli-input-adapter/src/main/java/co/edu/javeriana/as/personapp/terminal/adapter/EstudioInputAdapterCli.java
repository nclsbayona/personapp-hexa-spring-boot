package co.edu.javeriana.as.personapp.terminal.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
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
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.mapper.EstudioMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.EstudioModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class EstudioInputAdapterCli {

	@Autowired
	@Qualifier("studyOutputAdapterMaria")
	private StudyOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("studyOutputAdapterMongo")
	private StudyOutputPort personOutputPortMongo;

	@Autowired
	private EstudioMapperCli personaMapperCli;

	StudyInputPort personInputPort;

	public void setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new StudyUseCase(personOutputPortMaria);
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new StudyUseCase(personOutputPortMongo);
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public void historial1() {
		log.info("Into historial PersonaEntity in Input Adapter");
		List<EstudioModelCli> persona = personInputPort.findAll().stream().map(personaMapperCli::fromDomainToAdapterCli)
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
		System.out.println("El nombre de la universidad nueva: ");
		String identification = sc.nextLine();
		System.out.println("La fecha de graduacion nueva: ");
		String nombre = sc.nextLine();
		System.out.println("La persona nueva: ");
		String apellido = sc.nextLine();
		System.out.println("La profesion nueva: ");
		String edad = sc.nextLine();
		Person person = null;
		Profession profession = null;
		LocalDate date = LocalDate.parse(nombre);
		Study phone = new Study(person, profession, date, identification);
		phone = personInputPort.create(phone);
		System.out.println(personaMapperCli.fromDomainToAdapterCli(phone));
	}

	public void editar(Scanner sc) {
		log.info("Into editar PersonaEntity in Input Adapter");
		try {
			sc = new Scanner(System.in);
			System.out.println("La profesion actual: ");
			String old_identification = sc.nextLine();
			System.out.println("La persona actual: ");
			String old_nombre = sc.nextLine();
			System.out.println("El nombre de la universidad nueva: ");
			String identification = sc.nextLine();
			System.out.println("La fecha de graduacion nueva: ");
			String nombre = sc.nextLine();
			System.out.println("La persona nueva: ");
			String apellido = sc.nextLine();
			System.out.println("La profesion nueva: ");
			String edad = sc.nextLine();
			Person person = null;
			Profession profession = null;
			LocalDate date = LocalDate.parse(nombre);
			Study phone = new Study(person, profession, date, identification);
			phone = personInputPort.edit(Integer.parseInt(old_identification), Integer.parseInt(old_nombre), phone);
			System.out.println(personaMapperCli.fromDomainToAdapterCli(phone));
		} catch (NoExistException e) {
			log.warn(e.getMessage());
		}
	}

	public void eliminar(Scanner sc) {
		log.info("Into eliminar PersonaEntity in Input Adapter");
		try {
			sc = new Scanner(System.in);
			System.out.println("La profesion actual: ");
			String old_identification = sc.nextLine();
			System.out.println("La persona actual: ");
			String old_nombre = sc.nextLine();
			System.out
					.println(personInputPort.drop(Integer.parseInt(old_identification), Integer.parseInt(old_nombre)));
		} catch (NoExistException e) {
			log.warn(e.getMessage());
		}
	}

}
