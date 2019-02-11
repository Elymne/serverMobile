package uit.nantes.serverMobile.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uit.nantes.serverMobile.domain.UserService;
import uit.nantes.serverMobile.infra.IUserRepository;
import uit.nantes.serverMobile.infra.UserRepository;

/**
 * @author Djurdjevic Sacha
 */
@Configuration
public class Config {

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    public IUserRepository getUserRepository() {
        return new UserRepository();
    }

}
