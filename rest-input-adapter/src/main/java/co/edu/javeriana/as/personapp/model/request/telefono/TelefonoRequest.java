package co.edu.javeriana.as.personapp.model.request.telefono;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoRequest {
    private String phoneNumber;
	private String cellProvider;
	private Integer idOwner;
	private String database;
}
