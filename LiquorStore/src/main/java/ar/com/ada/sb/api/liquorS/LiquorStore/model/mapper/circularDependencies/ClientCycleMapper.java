package ar.com.ada.sb.api.liquorS.LiquorStore.model.mapper.circularDependencies;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.ClientDTO;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring")
public interface ClientCycleMapper extends DataCycleMapper<ClientDTO, Client> {
    ClientCycleMapper MAPPER = Mappers.getMapper(ClientCycleMapper.class);

}
