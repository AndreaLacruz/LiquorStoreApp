package ar.com.ada.sb.api.liquorS.LiquorStore.service;

import ar.com.ada.sb.api.liquorS.LiquorStore.components.BusinessLogicExceptionComponent;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.ProductDTO;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Product;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.mapper.circularDependencies.CycleAvoidingMappingContext;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.mapper.circularDependencies.ProductCycleMapper;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductService implements Services<ProductDTO> {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("productRepository")
    private ProductRepository productRepository;

    private ProductCycleMapper productCycleMapper = ProductCycleMapper.MAPPER;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    @Override
    public ProductDTO save(ProductDTO dto) {
        Product productToSave = productCycleMapper.toEntity(dto, context);
        Product productSaved = productRepository.save(productToSave);
        ProductDTO productDTOsaved = productCycleMapper.toDto(productSaved, context);
        return productDTOsaved;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> productsEntityList = productRepository.findAll();
        List<ProductDTO> productDTOSList = productCycleMapper.toDto(productsEntityList, context);
        return productDTOSList;
    }

    @Override
    public void delete(Long id) {
        Optional<Product> byIdOptional = productRepository.findById(id);
        if (byIdOptional.isPresent()){
            Product productToDelete = byIdOptional.get();
            productRepository.delete(productToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Product", id);
        }
    }

    public ProductDTO findById(Long id){
        Optional<Product> byId = productRepository.findById(id);
        ProductDTO productDTO = null;

        if (byId.isPresent()){
            Product productById = byId.get();
            productDTO = productCycleMapper.toDto(productById, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Product", id);
        } return productDTO;
    }

    public ProductDTO UpdateProd(ProductDTO productDTO, Long id){
        Optional<Product> byIdOptional = productRepository.findById(id);
        ProductDTO prodDTOupdated = null;

        if (byIdOptional.isPresent()){
            Product prodById = byIdOptional.get();
            prodDTOupdated.setId(prodById.getId());
            Product productToUpdate = productCycleMapper.toEntity( productDTO, context);
            Product productUpdated = productRepository.save(productToUpdate);
            prodDTOupdated = productCycleMapper.toDto(productUpdated, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Product", id);
        } return prodDTOupdated;
    }
}
