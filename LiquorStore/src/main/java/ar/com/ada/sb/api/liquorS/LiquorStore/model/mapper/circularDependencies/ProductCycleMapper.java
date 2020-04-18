package ar.com.ada.sb.api.liquorS.LiquorStore.model.mapper.circularDependencies;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.ProductDTO;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductCycleMapper extends DataCycleMapper<ProductDTO, Product>{
    ProductCycleMapper MAPPER = Mappers.getMapper(ProductCycleMapper.class);
}
