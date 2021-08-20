package br.com.roberto.consumer;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import br.com.roberto.model.Movie;

@ApplicationScoped
public class MovieConsumer {
	
	private final Logger logger = Logger.getLogger(MovieConsumer.class);
	
	@Incoming("movies-in")
	public void receive(Movie movie) {
		
		System.out.println("------------------------------");
		System.out.println(movie);
		System.out.println("Mensagem do Consumidor");
		System.out.println("------------------------------");
	}

}
