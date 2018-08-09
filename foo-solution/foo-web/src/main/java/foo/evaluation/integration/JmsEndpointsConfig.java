package foo.evaluation.integration;

import static org.springframework.integration.context.IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME;
import static org.springframework.integration.dsl.jms.Jms.messageDrivenChannelAdapter;

import javax.jms.ConnectionFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.dsl.jms.JmsMessageDrivenChannelAdapter;
import org.springframework.integration.jms.JmsSendingMessageHandler;
import org.springframework.jms.support.destination.DestinationResolver;

/**
 * Created by Patrik.Mihalcin on 22.05.2018
 */
@Configuration
@RequiredArgsConstructor
public class JmsEndpointsConfig {

    private static final String UPSTREAM_EVENT_QUEUE = "upstream";
    private static final String DOWNSTREAM_EVENT_QUEUE = "downstream";

    private final ConnectionFactory jmsConnectionFactory;
    private final DestinationResolver destinationResolver;

    public JmsMessageDrivenChannelAdapter jmsUpstreamEventQueueMessageDrivenChannelAdapter() {
        return messageDrivenChannelAdapter(jmsConnectionFactory)
                .configureListenerContainer(container -> container
                        .destinationResolver(destinationResolver)
                        .concurrentConsumers(2)
                )
                .errorChannel(ERROR_CHANNEL_BEAN_NAME)
                .destination(UPSTREAM_EVENT_QUEUE)
                .get();
    }

    public JmsSendingMessageHandler jmsDownstreamEventQueueOutboundAdapter() {
        return Jms.outboundAdapter(jmsConnectionFactory)
                .configureJmsTemplate(template -> template.destinationResolver(destinationResolver))
                .destination(DOWNSTREAM_EVENT_QUEUE)
                .get();
    }

}
