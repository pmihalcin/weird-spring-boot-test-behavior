package foo.evaluation.integration;

import static org.springframework.integration.handler.LoggingHandler.Level.INFO;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * Flow configuration for logging incoming and outgoing messages
 */
@Configuration
@RequiredArgsConstructor
public class MessageLoggingFlowConfig {

    private final MessageChannel incomingMessageLoggingChannel;
    private final MessageChannel outgoingMessageLoggingChannel;

    @Bean
    IntegrationFlow incomingMessageLoggingFlow() {
        return IntegrationFlows.from(incomingMessageLoggingChannel)
                .handle(incomingLoggingHandler())
                .get();
    }

    @Bean
    IntegrationFlow outgoingMessageLoggingFlow() {
        return IntegrationFlows.from(outgoingMessageLoggingChannel)
                .handle(outgoingLoggingHandler())
                .get();
    }

    @Bean
    public MessageHandler incomingLoggingHandler() {
        LoggingHandler loggingHandler = new LoggingHandler(INFO);
        loggingHandler.setLoggerName("MESSAGES_LOGGER.net.homecredit.mer");
        loggingHandler.setLogExpressionString(
                "'<< Received a message with headers: ' + headers + ' and payload:\n' + payload");
        return loggingHandler;
    }

    @Bean
    public MessageHandler outgoingLoggingHandler() {
        LoggingHandler loggingHandler = new LoggingHandler(INFO);
        loggingHandler.setLoggerName("MESSAGES_LOGGER.net.homecredit.mer");
        loggingHandler.setLogExpressionString(
                "'>> Publishing a message with headers: ' + headers + ' and payload:\n' + payload");
        return loggingHandler;
    }
}
