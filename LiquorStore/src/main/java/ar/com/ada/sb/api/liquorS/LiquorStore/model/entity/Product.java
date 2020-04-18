package ar.com.ada.sb.api.liquorS.LiquorStore.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Product")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean alcoholicDrink;

    @Column(nullable = false, length = 25)
    private String brand;

    @ManyToOne
    @JoinColumn(name = "Local_id", nullable = false)
    private Set<Local> locals;

    public Product(String name, Boolean alcoholicDrink, String brand) {
        this.name = name;
        this.alcoholicDrink = alcoholicDrink;
        this.brand = brand;
    }

    public Product(Long id) {
        this.id = id;
    }
}
