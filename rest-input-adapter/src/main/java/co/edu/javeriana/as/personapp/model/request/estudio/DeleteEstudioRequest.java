package co.edu.javeriana.as.personapp.model.request.estudio;

import lombok.Data;

@Data
public class DeleteEstudioRequest {
    private Integer professionIdentification;
    private Integer personIdentification;
    private String database;
}
