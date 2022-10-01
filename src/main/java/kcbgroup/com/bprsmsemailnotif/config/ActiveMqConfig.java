package kcbgroup.com.bprsmsemailnotif.config;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@EnableJms
@Configuration
public class ActiveMqConfig {

	@Value("${spring.activemq.broker-url}")
	private String BROKER_URL;

	@Value("${spring.activemq.user}")
	private String BROKER_USERNAME;

	@Value("${spring.activemq.password}")
	private String BROKER_PASSWORD;

	@Bean
	public ConnectionFactory connectionFactoryAMQP() {
		JmsConnectionFactory connectionFactory = new JmsConnectionFactory();
		connectionFactory.setRemoteURI(BROKER_URL);
		connectionFactory.setUsername(BROKER_USERNAME);
		connectionFactory.setPassword(BROKER_PASSWORD);
		return connectionFactory;
	}

	@Bean
	public JmsListenerContainerFactory<?> queueListenerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setMessageConverter(messageConverter());
		return factory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory myJmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactoryAMQP());
		factory.setMessageConverter(messageConverter());
		factory.setSessionTransacted(true);
		factory.setConcurrency("5");
		return factory;
	}

	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
