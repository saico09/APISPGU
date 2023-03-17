package co.api.producer.apiproducerreglas.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerGestionDelBeneficio {

	@Autowired
	public JmsTemplate jmsTemplate;

	public void send(String cola, Object mensaje) {
		this.jmsTemplate.convertAndSend(cola, mensaje);
	}

}
