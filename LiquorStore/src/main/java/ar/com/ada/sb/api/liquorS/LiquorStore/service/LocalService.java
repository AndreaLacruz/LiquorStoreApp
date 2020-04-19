package ar.com.ada.sb.api.liquorS.LiquorStore.service;

import ar.com.ada.sb.api.liquorS.LiquorStore.components.BusinessLogicExceptionComponent;
import ar.com.ada.sb.api.liquorS.LiquorStore.exception.ApiEntityError;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.dto.LocalDTO;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Client;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Local;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Product;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.mapper.circularDependencies.CycleAvoidingMappingContext;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.mapper.circularDependencies.LocalCycleMapper;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.repository.ClientRepository;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.repository.LocalRepository;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("localService")
public class LocalService implements Services<LocalDTO>{

    @Autowired @Qualifier("businessLogicExceptionComponent")
    BusinessLogicExceptionComponent logicExceptionComponent;

    @Qualifier("localRepository") @Autowired
    private LocalRepository localRepository;

    @Qualifier("clientRepository") @Autowired
    private ClientRepository clientRepository;

    @Autowired @Qualifier("productRepository")
    private ProductRepository productRepository;

    private LocalCycleMapper localCycleMapper = LocalCycleMapper.MAPPER;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;


    @Override
    public LocalDTO save(LocalDTO dto) {
        Local localToSave = localCycleMapper.toEntity(dto, context);
        Local localSaved = localRepository.save(localToSave);
        LocalDTO localDTOsaved = localCycleMapper.toDto(localSaved, context);
        return localDTOsaved;
    }

    @Override
    public List<LocalDTO> findAll() {
        List<Local> localEntityList = localRepository.findAll();
        List<LocalDTO> localDTOList = localCycleMapper.toDto(localEntityList, context);
        return localDTOList;
    }

    @Override
    public void delete(Long id) {
    }

    public LocalDTO update(LocalDTO localDTO, Long id){
        Optional<Local> byIdOptional = localRepository.findById(id);
        LocalDTO localD = null;

        if (byIdOptional.isPresent()){
            Local localById = byIdOptional.get();
            localD.setId(localById.getId());
            Local localToUpdate = localCycleMapper.toEntity(localDTO, context);
            Local localUpdated = localRepository.save(localToUpdate);
            localD = localCycleMapper.toDto(localUpdated, context);
        } else {
            logicExceptionComponent.throwExceptionEntityNotFound("Local", id);
        } return localD;
    }

    public LocalDTO addClientToLocal(Long clientId, Long localId) {
        Optional<Local> localOptional = localRepository.findById(localId);
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        LocalDTO localDTOWithClient = null;

        if (!localOptional.isPresent())
            logicExceptionComponent.throwExceptionEntityNotFound("Local", localId);

        if (!clientOptional.isPresent())
            logicExceptionComponent.throwExceptionEntityNotFound("Client", clientId);

        Local local = localOptional.get();
        Client clientAdding = clientOptional.get();

        local.addClient(clientAdding);
        Local localWithClient = localRepository.save(local);
        localDTOWithClient = localCycleMapper.toDto(localWithClient, context);

        ApiEntityError apiEntityError = new ApiEntityError(
                    "Client", "Already Exist", "The Client with this id: "
                    + clientId + " Already inside Local DataBase "
        );

        return localDTOWithClient;

    }

    public LocalDTO addProductToLocal(Long productId, Long localId) {
        Optional<Local> localOptional = localRepository.findById(localId);
        Optional<Product> productOptional = productRepository.findById(productId);
        LocalDTO localDTOWithProduct = null;

        if (!localOptional.isPresent())
            logicExceptionComponent.throwExceptionEntityNotFound("Local", localId);

        if (!productOptional.isPresent())
            logicExceptionComponent.throwExceptionEntityNotFound("Product", productId);

        Local local = localOptional.get();
        Product productToSet = productOptional.get();

        local.addProduct(productToSet);
        Local localWithProduct = localRepository.save(local);
        localDTOWithProduct = localCycleMapper.toDto(localWithProduct, context);

        return localDTOWithProduct;
    }
}
