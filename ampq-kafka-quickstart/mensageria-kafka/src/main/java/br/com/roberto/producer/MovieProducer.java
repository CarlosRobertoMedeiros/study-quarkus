package br.com.roberto.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.com.roberto.model.Movie;
import io.smallrye.reactive.messaging.kafka.Record;

@ApplicationScoped
public class MovieProducer {
	
	@Inject
	@Channel("movies-out")
	Emitter<Record<Integer, String>> emitter;
	
	public void sendMovieToKafka(Movie movie) {
		emitter.send(Record.of(movie.year, movie.title));
	}

	
}
