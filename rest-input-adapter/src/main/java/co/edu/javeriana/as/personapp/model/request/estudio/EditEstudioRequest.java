package co.edu.javeriana.as.personapp.model.request.estudio;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class EditEstudioRequest extends EstudioRequest{
    String oldIdentification;
}
