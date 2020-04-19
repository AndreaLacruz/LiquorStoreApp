package ar.com.ada.sb.api.liquorS.LiquorStore.service;

import ar.com.ada.sb.api.liquorS.LiquorStore.components.BusinessLogicExceptionComponent;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.ClientDTO;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Client;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.mapper.circularDependencies.ClientCycleMapper;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.mapper.circularDependencies.CycleAvoidingMappingContext;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("clientService")
public class ClientService implements Services<ClientDTO> {

    @Autowired @Qualifier("businessLogicExceptionComponent")
    BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("clientRepository")
    private ClientRepository clientRepository;

    private ClientCycleMapper clientCycleMapper = ClientCycleMapper.MAPPER;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    @Override
    public ClientDTO save(ClientDTO dto) {
        Client clientToSave = clientCycleMapper.toEntity(dto, context);
        Client clientSaved = clientRepository.save(clientToSave);
        ClientDTO clientDTOSaved = clientCycleMapper.toDto(clientSaved, context);
        return clientDTOSaved;
    }

    @Override
    public List<ClientDTO> findAll() {
        List<Client> clientEntityList = clientRepository.findAll();
        List<ClientDTO> clientDTOList = clientCycleMapper.toDto(clientEntityList, context);
        return clientDTOList;
    }

    @Override
    public void delete(Long id) {
        Optional<Client> byIdOptional = clientRepository.findById(id);
        if (byIdOptional.isPresent()){
            Client clientToDelete = byIdOptional.get();
            clientRepository.delete(clientToDelete);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Client", id);
        }
    }

    public ClientDTO updateClient(ClientDTO clientDTO, Long id){
        Optional<Client> byIdOptional = clientRepository.findById(id);
        ClientDTO clientDTOUpdated = null;
        if (byIdOptional.isPresent()){
            Client clientById = byIdOptional.get();
            clientDTOUpdated.setId(clientById.getId());
            Client clientToUpdate = clientCycleMapper.toEntity(clientDTOUpdated, context);
            Client clientUpdated = clientRepository.save(clientToUpdate);
            clientDTOUpdated = clientCycleMapper.toDto(clientUpdated, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Client", id);
        } return clientDTOUpdated;
    }

    public ClientDTO findClientByDNI(Integer dni){
        Optional<Client> byDNI = clientRepository.findByDni(dni);
        ClientDTO clientDTO = null;
        Long id = null;

        if (byDNI.isPresent()){
            Client clientByDNI = byDNI.get();
            clientDTO = clientCycleMapper.toDto(clientByDNI, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Client", id);
        } return clientDTO;
    }
}
