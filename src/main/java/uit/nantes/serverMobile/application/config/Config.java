package uit.nantes.serverMobile.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.access.AccessDeniedHandler;
import uit.nantes.serverMobile.application.controller.util.JsonResponse;
import uit.nantes.serverMobile.domain.EventService;
import uit.nantes.serverMobile.domain.ExpenseService;
import uit.nantes.serverMobile.domain.UserService;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Configuration
@EnableJpaRepositories(basePackages = "uit.nantes.serverMobile.infra.jpa")
@EntityScan(basePackages = "uit.nantes.serverMobile.api.entities")
public class Config {

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    public EventService getEventService() {
        return new EventService();
    }

    @Bean
    public ExpenseService getExpenseService() {
        return new ExpenseService();
    }

    @Bean
    public JsonResponse getToJson() {
        return new JsonResponse();
    }

}
