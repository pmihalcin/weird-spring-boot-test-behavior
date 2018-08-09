package foo.evaluation.integration;

import static org.springframework.jndi.JndiLocatorDelegate.isDefaultJndiEnvironmentAvailable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.jms.support.destination.JndiDestinationResolver;

@Configuration
public class DestinationResolverConfig {

    @Bean
    public DestinationResolver destinationResolver() {
        JndiDestinationResolver jndiDestinationResolver = new JndiDestinationResolver();
        jndiDestinationResolver.setResourceRef(true);
        return isDefaultJndiEnvironmentAvailable() ? jndiDestinationResolver : new DynamicDestinationResolver();
    }
}
