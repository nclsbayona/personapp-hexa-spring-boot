package co.edu.javeriana.as.personapp.model.response.profesion;

import co.edu.javeriana.as.personapp.model.request.profesion.ProfesionRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProfesionResponse extends ProfesionRequest {
    private String status;
	
	public ProfesionResponse(Integer identification, String name, String description, String database, String status) {
		super(identification, name, description, database);
		this.status = status;
	}
}
