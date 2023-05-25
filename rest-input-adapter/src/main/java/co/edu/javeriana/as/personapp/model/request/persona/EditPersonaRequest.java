package co.edu.javeriana.as.personapp.model.request.persona;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class EditPersonaRequest extends PersonaRequest{
    private Integer identification;
}
