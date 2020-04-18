package ar.com.ada.sb.api.liquorS.LiquorStore.model.entity;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.ClientDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Entity (name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 10)
    private Integer dni;

    @ManyToOne @JoinColumn(name = "Local_id", nullable = false)
    private Set<Local> locals;

    public Client(Long id) {
        this.id = id;
    }
}
