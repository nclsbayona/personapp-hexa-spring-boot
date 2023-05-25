package co.edu.javeriana.as.personapp.model.request.telefono;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class EditTelefonoRequest extends TelefonoRequest{
    String identification;
}
