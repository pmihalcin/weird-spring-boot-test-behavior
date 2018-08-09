package foo.evaluation.integration;

import static org.springframework.integration.dsl.channel.MessageChannels.direct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

/**
 * Created by patrik.mihalcin on 4.8.2017.
 */
@Configuration
public class ChannelConfig {

    @Bean
    public MessageChannel incomingMessageLoggingChannel() {
        return direct().get();
    }

    @Bean
    public MessageChannel outgoingMessageLoggingChannel() {
        return direct().get();
    }

    @Bean
    public MessageChannel downstreamEventChannel() {
        return direct().get();
    }
}
