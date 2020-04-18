package ar.com.ada.sb.api.liquorS.LiquorStore.components;

import ar.com.ada.sb.api.liquorS.LiquorStore.exception.ApiEntityError;
import ar.com.ada.sb.api.liquorS.LiquorStore.exception.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("businessLogicExceptionComponent")
public class BusinessLogicExceptionComponent {

    public void throwExceptionEntityNotFound(String entityName, Long id){
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName, "Not Found", "The" + entityName + " with id " + id + " does not exist."
        );

        throw new BusinessLogicException(
                entityName + " does not exist", HttpStatus.NOT_FOUND, apiEntityError
        );
    }
}
