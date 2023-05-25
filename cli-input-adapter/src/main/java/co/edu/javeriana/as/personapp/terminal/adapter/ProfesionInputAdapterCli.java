package co.edu.javeriana.as.personapp.terminal.adapter;

import java.util.List;
import java.util.Scanner;
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
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.mapper.ProfesionMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.ProfesionModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class ProfesionInputAdapterCli {

	@Autowired
	@Qualifier("professionOutputAdapterMaria")
	private ProfessionOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("professionOutputAdapterMongo")
	private ProfessionOutputPort personOutputPortMongo;

	@Autowired
	private ProfesionMapperCli personaMapperCli;

	ProfessionInputPort personInputPort;

	public void setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new ProfessionUseCase(personOutputPortMaria);
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new ProfessionUseCase(personOutputPortMongo);
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public void historial1() {
		log.info("Into historial PersonaEntity in Input Adapter");
		List<ProfesionModelCli> persona = personInputPort.findAll().stream()
				.map(personaMapperCli::fromDomainToAdapterCli)
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
		System.out.println("La identificacion nueva: ");
		String identification = sc.nextLine();
		System.out.println("El nombre nuevo: ");
		String nombre = sc.nextLine();
		System.out.println("La descripcion nueva: ");
		String edad = sc.nextLine();
		Integer id = Integer.parseInt(identification);
		List<Study> studies = null;
		try {
			studies = personInputPort.getStudies(id);
		} catch (NoExistException e) {
			log.warn(e.getMessage());
		}
		Profession person = new Profession(id, nombre, edad, studies);
		person = personInputPort.create(person);
		System.out.println(personaMapperCli.fromDomainToAdapterCli(person));
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
			System.out.println("La descripcion nueva: ");
			String edad = sc.nextLine();
			Integer id = Integer.parseInt(identification);
			List<Study> studies = null;
			try {
				studies = personInputPort.getStudies(id);
			} catch (NoExistException e) {
				log.warn(e.getMessage());
			}
			Profession person = new Profession(id, nombre, edad, studies);
			person = personInputPort.edit(Integer.parseInt(old_identification), person);
			System.out.println(personaMapperCli.fromDomainToAdapterCli(person));
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
			System.out.println(personInputPort.drop(Integer.parseInt(old_identification)));
		} catch (NoExistException e) {
			log.warn(e.getMessage());
		}
	}

}
