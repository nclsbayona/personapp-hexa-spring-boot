package co.edu.javeriana.as.personapp.terminal.model;

import co.edu.javeriana.as.personapp.domain.Profession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesionModelCli {

	public ProfesionModelCli(@NonNull Profession profession) {
		this.identification=profession.getIdentification();
		this.nombre=profession.getName();
		this.descripcion=profession.getDescription();
    }
	
    private Integer identification;
	private String nombre;
	private String descripcion;
}
