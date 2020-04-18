package ar.com.ada.sb.api.liquorS.LiquorStore.components.data;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Local;
import ar.com.ada.sb.api.liquorS.LiquorStore.model.repository.LocalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LocalLoaderData implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalLoaderData.class);

    @Autowired
    @Qualifier("localRepository")
    private LocalRepository localRepository;

    @Value("${spring.application.env}")
    private String appEnv;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Environment: " + appEnv);

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading Local Initial Data");

            List<Local> localList = Arrays.asList(
                    new Local("CocoMarket", "Av. Cabildo 1010"),
                    new Local("Carrefour", "Olleros 3454")
            );
            localList.forEach(local -> localRepository.save(local));

        /*for (Local local: localList){
            localRepository.save(local);
        }

        for (int i = 0; i <= localList.size(); i++){
            Local local = localList.get(i);
            localRepository.save(local);
        }*/
        }
    }
}
