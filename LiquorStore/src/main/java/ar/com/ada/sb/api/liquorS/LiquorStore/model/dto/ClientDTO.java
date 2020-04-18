package ar.com.ada.sb.api.liquorS.LiquorStore.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@JsonPropertyOrder({"id", "name", "dni", "local"})
public class ClientDTO implements Serializable {

    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "DNI is required")
    private Integer dni;

    @JsonIgnoreProperties(value = "client")
    private Set<LocalDTO> locals;

    public ClientDTO(Long id, String name, Integer dni, Set<LocalDTO> locals) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.locals = locals;
    }

    public ClientDTO(String name, Integer dni, Set<LocalDTO> locals) {
        this.name = name;
        this.dni = dni;
        this.locals = locals;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dni=" + dni +
                ", local=" + locals +
                '}';
    }
}
