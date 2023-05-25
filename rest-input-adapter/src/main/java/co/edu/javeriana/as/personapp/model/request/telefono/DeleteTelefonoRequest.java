package co.edu.javeriana.as.personapp.model.request.telefono;

import lombok.Data;

@Data
public class DeleteTelefonoRequest {
    private String phoneNumber;
    private String database;
}
