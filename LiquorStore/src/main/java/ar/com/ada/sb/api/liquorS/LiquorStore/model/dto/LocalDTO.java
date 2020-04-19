package ar.com.ada.sb.api.liquorS.LiquorStore.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Setter @Getter @NoArgsConstructor
@JsonPropertyOrder({"id", "name", "address", "products", "client"})
public class LocalDTO implements Serializable {

    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "address is required")
    private String address;

    @JsonIgnoreProperties(value = "locals")
    private List<ProductDTO> products;

    @JsonIgnoreProperties(value = "locals")
    private ClientDTO clients;

    public LocalDTO(Long id, String name, String address, List<ProductDTO> products, ClientDTO clients) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.products = products;
        this.clients = clients;
    }

    public LocalDTO(String name, String address, List<ProductDTO> products, ClientDTO clients) {
        this.name = name;
        this.address = address;
        this.products = products;
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "LocalDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", products=" + products +
                ", client=" + clients +
                '}';
    }
}
