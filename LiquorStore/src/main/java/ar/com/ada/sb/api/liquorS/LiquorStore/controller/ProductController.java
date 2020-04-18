package ar.com.ada.sb.api.liquorS.LiquorStore.controller;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.ProductDTO;
import ar.com.ada.sb.api.liquorS.LiquorStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping({"/products"})
public class ProductController {

    @Autowired @Qualifier("productService")
    private ProductService productService;

    @GetMapping({"", "/"}) // localhost:8080/products/ y localhost:8080/products/
    public ResponseEntity getAllProducts(){
        List<ProductDTO> all = productService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/{id}", "/{id}/"}) // localhost:8080/products/1 y localhost:8080/products/1/
    public ResponseEntity getProductsById(@PathVariable Long id){
        ProductDTO productById = productService.findById(id);
        return ResponseEntity.ok(productById);
    }

    @PostMapping({"", "/"}) // localhost:8080/products/ y localhost:8080/products/
    public ResponseEntity addNewProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        ProductDTO productSaved = productService.save(productDTO);
        return ResponseEntity.created(new URI("/products/" + + productDTO.getId())).body(productSaved);
    }

    @PutMapping({"/{id}","/{id}/"}) // localhost:8080/products/1 y localhost:8080/products/1/
    public ResponseEntity updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id){
        ProductDTO productUpdated = productService.UpdateProd(productDTO, id);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping({"/{id}", "/{id}/"}) // localhost:8080/products/1 y localhost:8080/products/1/
    public ResponseEntity deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
