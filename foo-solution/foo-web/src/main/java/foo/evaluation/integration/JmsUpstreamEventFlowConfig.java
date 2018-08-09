package foo.evaluation.integration;

import static org.springframework.integration.dsl.IntegrationFlows.from;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.MessageHandler;

/**
 * Created by patrik.mihalcin on 4.8.2017.
 */
@Configuration
@RequiredArgsConstructor
public class JmsUpstreamEventFlowConfig {

    private final JmsEndpointsConfig jmsEndpointsConfig;
    private final ChannelConfig channelConfig;

    private final UpstreamEventTransformer upstreamTransformer;
    private final EvaluationHandler handler;

    @Bean
    public IntegrationFlow upstreamEventFlow() {
        return from(inboundAdapter())
                .wireTap(channelConfig.incomingMessageLoggingChannel())
                .transform(upstreamTransformer)
                .handle(handler)
                .channel(channelConfig.downstreamEventChannel())
                .get();
    }

    @Bean
    public IntegrationFlow downstreamEventFlow() {
        return from(channelConfig.downstreamEventChannel())
                .wireTap(channelConfig.outgoingMessageLoggingChannel())
                .handle(outboundAdapter())
                .get();
    }

    private MessageProducerSupport inboundAdapter() {
        return jmsEndpointsConfig.jmsUpstreamEventQueueMessageDrivenChannelAdapter();
    }

    private MessageHandler outboundAdapter() {
        return jmsEndpointsConfig.jmsDownstreamEventQueueOutboundAdapter();
    }
}
