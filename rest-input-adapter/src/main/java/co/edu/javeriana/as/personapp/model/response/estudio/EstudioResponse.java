package co.edu.javeriana.as.personapp.model.response.estudio;

import java.time.LocalDate;

import co.edu.javeriana.as.personapp.model.request.estudio.EstudioRequest;
public class EstudioResponse extends EstudioRequest{
	
	private String status;
	
	public EstudioResponse(Integer personIdentification, Integer professionIdentification, String universityName, LocalDate graduationDate, String database, String status) {
		super(personIdentification, professionIdentification, universityName, graduationDate, database);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
