package biz.bagira.shds.listener;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsConsumerTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ActiveMQConnectionFactory activeMQConnectionFactory;

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void shouldSendMessage() throws JMSException {
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("admin.queue");
        String text = "Hello world!";
        jmsTemplate.convertAndSend(destination, text);
        session.close();
        connection.close();
        assertThat(this.outputCapture.toString().contains("Hello world!")).isTrue();

    }

}