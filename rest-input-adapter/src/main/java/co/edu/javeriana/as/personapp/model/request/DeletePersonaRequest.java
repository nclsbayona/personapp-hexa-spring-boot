package co.edu.javeriana.as.personapp.model.request;
import lombok.Data;

@Data
public class DeletePersonaRequest{
    private Integer identification;
	private String database;
}
