package ar.com.ada.sb.api.liquorS.LiquorStore.model.mapper.circularDependencies;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.LocalDTO;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Local;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocalCycleMapper extends DataCycleMapper<LocalDTO, Local> {
    LocalCycleMapper MAPPER = Mappers.getMapper(LocalCycleMapper.class);
}
