package foo.evaluation

import org.apache.activemq.command.ActiveMQTextMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.destination.DestinationResolver
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

import javax.jms.ConnectionFactory

import static foo.evaluation.integration.JmsEndpointsConfig.UPSTREAM_EVENT_QUEUE

@SpringBootTest
@DirtiesContext
@Import(FailureSimulator)
class JmsSimulatedBusinessFailureSpec extends Specification {

    private static final String ACTIVEMQ_DLQ = "ActiveMQ.DLQ"
    @Autowired
    JmsTemplate jmsTemplate
    @Autowired
    ConnectionFactory connectionFactory
    @Autowired
    DestinationResolver resolver

    def "business processing failure"() {
        when: "I send a message to JMS upstream queue"
        jmsTemplate.convertAndSend(UPSTREAM_EVENT_QUEUE, "message")

        then: "message is retried 6 times and parked in DLQ"
        jmsTemplate.setReceiveTimeout(25000L)
        def msg = jmsTemplate.receive(ACTIVEMQ_DLQ) as ActiveMQTextMessage
        msg != null
    }

}