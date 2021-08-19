package br.com.roberto.consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import br.com.roberto.model.Movie;

@ApplicationScoped
public class MovieConsumer {
	
	private final Logger logger = Logger.getLogger(MovieConsumer.class);
	
	@Incoming("movies-in")
	public void receive(String json) {
		
		Jsonb create = JsonbBuilder.create();
		Movie movie = create.fromJson(json, Movie.class);
		System.out.println("------------------------------");
		System.out.println(json);
		System.out.println("------------------------------");
		System.out.println(movie);
		
		//logger.infof("Got a movie: %d - %s", movie.year, movie.title);	
	}

}
