package foo.evaluation.integration;

import static java.util.Optional.ofNullable;
import static org.springframework.integration.context.IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

@Configuration
@Slf4j
public class JmsErrorFlowConfig {

    @Bean
    public IntegrationFlow errorHandlingFlow() {
        return IntegrationFlows.from(ERROR_CHANNEL_BEAN_NAME)
                .handle(this::errorMessageHandler)
                .get();
    }

    private void errorMessageHandler(Message<?> msg) {
        if (msg.getPayload() instanceof MessagingException) {
            MessagingException exception = (MessagingException) msg.getPayload();
            ofNullable(exception.getFailedMessage()).ifPresent(message -> {
                log.error("Failed msg headers: {}", message.getHeaders());
                log.error("Failed msg payload: {}", message.getPayload());
            });
            // cause JMS broker redeliver
            throw exception;
        } else {
            log.warn("There is no handling implemented yet for exception of type: " + msg.getPayload().getClass());
            log.warn("Error message delivered by Spring Integration is: " + msg);
        }
    }
}