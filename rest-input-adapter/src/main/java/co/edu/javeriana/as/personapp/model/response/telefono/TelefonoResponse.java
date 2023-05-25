package co.edu.javeriana.as.personapp.model.response.telefono;

import co.edu.javeriana.as.personapp.model.request.telefono.TelefonoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class TelefonoResponse extends TelefonoRequest {
    private String status;
	
	public TelefonoResponse(String phoneNumber, String cellProvider, Integer idOwner, String database, String status) {
		super(phoneNumber, cellProvider, idOwner, database);
		this.status = status;
	}
}
