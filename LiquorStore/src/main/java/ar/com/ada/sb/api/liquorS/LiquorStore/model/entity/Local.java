package ar.com.ada.sb.api.liquorS.LiquorStore.model.entity;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.ClientDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Local")
public class Local {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @OneToMany(mappedBy = "locals")
    private List<Product> products;


    @OneToMany(mappedBy = "locals")
    private Set<Client> clients;

    public Local(Long id) {
        this.id = id;
    }

    public Local(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public void setProducts(Product productToSet) {
    }
}
