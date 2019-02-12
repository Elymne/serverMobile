package uit.nantes.serverMobile.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uit.nantes.serverMobile.application.controller.util.ToJson;
import uit.nantes.serverMobile.domain.UserService;

/**
 * @author Djurdjevic Sacha
 */
@Configuration
@EnableJpaRepositories(basePackages="uit.nantes.serverMobile.infra.jpa")
@EntityScan(basePackages = "uit.nantes.serverMobile.api.entities")
public class Config {

    @Bean
    public UserService getUserService() {
        return new UserService();
    }
    
    @Bean
    public ToJson getToJson(){
        return new ToJson();
    }

}
