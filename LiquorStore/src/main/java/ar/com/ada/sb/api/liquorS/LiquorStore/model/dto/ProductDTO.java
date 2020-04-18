package ar.com.ada.sb.api.liquorS.LiquorStore.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Setter @Getter @NoArgsConstructor
@JsonPropertyOrder({"id", "name", "alcoholicDrink", "brand", "local"})
public class ProductDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "choice is required")
    private Boolean alcoholicDrink;

    @NotBlank(message = "name brand is required")
    private String brand;

    @JsonIgnoreProperties(value = "products")
    private Set<LocalDTO> locals;

    public ProductDTO(Long id, String name, Boolean alcoholicDrink, String brand, Set<LocalDTO> locals) {
        this.id = id;
        this.name = name;
        this.alcoholicDrink = alcoholicDrink;
        this.brand = brand;
        this.locals = locals;
    }

    public ProductDTO(String name, Boolean alcoholicDrink, String brand, Set<LocalDTO> locals) {
        this.name = name;
        this.alcoholicDrink = alcoholicDrink;
        this.brand = brand;
        this.locals = locals;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alcoholicDrink=" + alcoholicDrink +
                ", brand='" + brand + '\'' +
                ", local=" + locals +
                '}';
    }
}
