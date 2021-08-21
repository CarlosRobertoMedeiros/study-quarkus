package br.com.roberto.ifood.marketplace.consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import br.com.roberto.ifood.marketplace.entity.Restaurante;
import io.vertx.mutiny.pgclient.PgPool;

@ApplicationScoped
public class RestauranteCadastradoConsumer {
	
	private final Logger logger = Logger.getLogger(Restaurante.class);
	
	@Inject
	PgPool client;
	
	@Incoming("restaurante-in")
	public void receive(Restaurante restaurante) {
		
		System.out.println("------------------------------");
		System.out.println(restaurante);
		System.out.println("Mensagem do Consumidor");
		System.out.println("------------------------------");
		
		restaurante.persist(client);
	}


}
