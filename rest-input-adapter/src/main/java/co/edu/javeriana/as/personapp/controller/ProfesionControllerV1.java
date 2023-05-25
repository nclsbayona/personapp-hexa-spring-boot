package co.edu.javeriana.as.personapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.as.personapp.adapter.ProfesionInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.profesion.DeleteProfesionRequest;
import co.edu.javeriana.as.personapp.model.request.profesion.EditProfesionRequest;
import co.edu.javeriana.as.personapp.model.request.profesion.ProfesionRequest;
import co.edu.javeriana.as.personapp.model.response.profesion.ProfesionResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/profesion")
public class ProfesionControllerV1 {
    
    @Autowired
    private ProfesionInputAdapterRest personaInputAdapterRest;
    
    @ResponseBody
	@GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProfesionResponse> personas(@PathVariable String database) {
		log.info("Into profesiones REST API");
			return personaInputAdapterRest.historial(database.toUpperCase());
	}
	
	@ResponseBody
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProfesionResponse crearTelefono(@RequestBody ProfesionRequest request) {
		log.info("esta en el metodo crearProfesion en el controller del api");
		return personaInputAdapterRest.crearProfesion(request);
	}

	@ResponseBody
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProfesionResponse editarTelefono(@RequestBody EditProfesionRequest request) {
		log.info("esta en el metodo editarProfesion en el controller del api");
		return personaInputAdapterRest.editarProfesion(request);
	}

	@ResponseBody
	@DeleteMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean borrarTelefono(@RequestBody DeleteProfesionRequest request) {
		log.info("esta en el metodo borrarProfesion en el controller del api");
		return personaInputAdapterRest.borrarProfesion(request);
	}
}
