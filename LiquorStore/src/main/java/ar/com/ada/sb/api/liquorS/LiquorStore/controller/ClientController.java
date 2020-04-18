package ar.com.ada.sb.api.liquorS.LiquorStore.controller;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.ClientDTO;
import ar.com.ada.sb.api.liquorS.LiquorStore.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping({"/clients"})
public class ClientController {

    @Autowired @Qualifier("clientService")
    private ClientService clientService;

    @GetMapping({"","/"}) // localhost:8080/clients y localhost:8080/clients/
    public ResponseEntity getAllClients(){
        List<ClientDTO> all = clientService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/{id}","/{id}/"}) // localhost:8080/clients/1 y localhost:8080/clients/1/
    public ResponseEntity getClientByDNI(@PathVariable Integer dni){
        ClientDTO clientByDNI = clientService.findClientByDNI(dni);
        return ResponseEntity.ok(clientByDNI);
    }

    @PostMapping({"", "/"}) // localhost:8080/clients y localhost:8080/clients/
    public ResponseEntity addNewClient(@Valid  @RequestBody ClientDTO clientDTO ) throws URISyntaxException {
        ClientDTO clientSaved = clientService.save(clientDTO);
        return ResponseEntity.created(new URI("/clients/" + clientDTO.getId())).body(clientSaved);
    }

    @PutMapping({"/{id}", "/{id}/"}) // localhost:8080/clients/1 y localhost:8080/clients/1/
    public ResponseEntity updateClient(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id) {
        ClientDTO clientUpdate = clientService.updateClient(clientDTO,id);
        return ResponseEntity.ok(clientUpdate);
    }

    @DeleteMapping({"/{id}", "/{id}/"}) // localhost:8080/clients/1 y localhost:8080/clients/1/
    public ResponseEntity deleteClient(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
