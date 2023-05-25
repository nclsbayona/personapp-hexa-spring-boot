package co.edu.javeriana.as.personapp.mongo.adapter;

import java.util.List;

import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import co.edu.javeriana.as.personapp.mongo.mapper.TelefonoMapperMongo;
import co.edu.javeriana.as.personapp.mongo.repository.TelefonosRepositoryMongo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter("phoneOutputAdapterMongo")
public class PhoneOutputAdapterMongo implements PhoneOutputPort {

	@Autowired
	private TelefonosRepositoryMongo telefonoRepositoryMongo;

	@Autowired
	private TelefonoMapperMongo telefonoMapperMongo;

	@Override
	public Phone save(Phone phone) {
		log.debug("Into save on Adapter Mongo");
		TelefonoDocument persistedTelefono = telefonoRepositoryMongo.save(telefonoMapperMongo.fromDomainToAdapter(phone));
		return telefonoMapperMongo.fromAdapterToDomain(persistedTelefono);
	}

	@Override
	public Boolean delete(String identification) {
		log.debug("Into delete on Adapter Mongo");
		telefonoRepositoryMongo.deleteById(identification);
		return telefonoRepositoryMongo.findById(identification).isEmpty();
	}

	@Override
	public List<Phone> find() {
		log.debug("Into find on Adapter Mongo");
		return telefonoRepositoryMongo.findAll().stream().map(telefonoMapperMongo::fromAdapterToDomain)
				.collect(Collectors.toList());
	}

	@Override
	public Phone findById(String identification) {
		log.debug("Into findById on Adapter Mongo");
		if (telefonoRepositoryMongo.findById(identification).isEmpty()) {
			return null;
		} else {
			return telefonoMapperMongo.fromAdapterToDomain(telefonoRepositoryMongo.findById(identification).get());
		}
	}

}
