package co.edu.javeriana.as.personapp.model.request.profesion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionRequest {
    private Integer identification;
	private String name;
	private String description;
	private String database;
}
