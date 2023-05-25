package co.edu.javeriana.as.personapp.model.request.estudio;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudioRequest {
    private Integer personIdentification;
	private Integer professionIdentification;
	private String universityName;
	private LocalDate graduationDate;
	private String database;
}
