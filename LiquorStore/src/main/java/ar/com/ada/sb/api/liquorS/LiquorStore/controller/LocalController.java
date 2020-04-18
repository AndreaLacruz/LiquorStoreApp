package ar.com.ada.sb.api.liquorS.LiquorStore.controller;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.LocalDTO;
import ar.com.ada.sb.api.liquorS.LiquorStore.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/locals"})
public class LocalController {

    @Autowired @Qualifier("localService")
    private LocalService localService;

    @GetMapping({"","/"})// localhost:8080/locals/ y localhost:8080/locals/
    public ResponseEntity getAllLocals(){
        List<LocalDTO> localAll = localService.findAll();
        return ResponseEntity.ok(localAll);
    }

    @PutMapping({"/{id", "/{id}/"}) // localhost:8080/locals/1 y localhost:8080/locals/1/
    public ResponseEntity updateLocal(@Valid @RequestBody LocalDTO localDTO, Long id){
        LocalDTO localUpdated = localService.update(localDTO, id);
        return ResponseEntity.ok(localUpdated);
    }

    @DeleteMapping({"/{id}", "/{id}/"}) // localhost:8080/locals/1 y localhost:8080/locals/1/
    public ResponseEntity deleteLocal(Long id){
        localService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // localhost:8080/locals/1/clients/20 y localhost:8080/locals/1/clients/20/ [PUT]
    @PutMapping({"/{localId}/clients/{clientId}","/{localId}/clients/{clientId}/"})
    public ResponseEntity addClientToLocal(@PathVariable Long clientId, @PathVariable Long localId){
        LocalDTO localDTOWithClient = localService.addClientToLocal(clientId, localId);
        return ResponseEntity.ok(localDTOWithClient);
    }

    // localhost:8080/locals/1/products/20 y localhost:8080/locals/1/products/20/ [PUT]
    @PutMapping({"/{localId}/products/{productId}", "/{localId}/products/{productId}/"})
    public ResponseEntity addProductToLocal(@PathVariable Long productId, @PathVariable Long localId){
        LocalDTO localDtoWithProd = localService.addProductToLocal(productId, localId);
        return ResponseEntity.ok(localDtoWithProd);
    }
}
