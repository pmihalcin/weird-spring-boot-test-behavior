package foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableIntegration
@EnableCaching
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static final String AMQP_PROFILE = "amqp";
    public static final String NOT_AMQP_PROFILE = "!" + AMQP_PROFILE;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
