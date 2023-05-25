package co.edu.javeriana.as.personapp.model.request.profesion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class EditProfesionRequest extends ProfesionRequest{
    String oldIdentification;
}
