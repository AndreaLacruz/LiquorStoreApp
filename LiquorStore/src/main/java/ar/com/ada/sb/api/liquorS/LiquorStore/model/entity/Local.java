package ar.com.ada.sb.api.liquorS.LiquorStore.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "locals")
    private List<Product> products;

    @ManyToOne @JoinColumn (name = "client_id", nullable = false)
    private Client client;

    public Local(Long id) {
        this.id = id;
    }

    public Local(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addClient(Client client) {
        this.client = client;
    }

    public void addProduct(Product product) {
        if (this.products==null)
            this.products = new ArrayList<>();
        this.products.add(product);
    }
}
