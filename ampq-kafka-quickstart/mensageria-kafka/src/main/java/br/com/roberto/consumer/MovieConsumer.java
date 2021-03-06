package br.com.roberto.consumer;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import io.smallrye.reactive.messaging.kafka.Record;


@ApplicationScoped
public class MovieConsumer {
	
	private final Logger logger = Logger.getLogger(MovieConsumer.class);
	
	//@Incoming("movies-in")
    public void receive(Record<Integer, String> record) {
        logger.infof("Got a movie: %d - %s", record.key(), record.value());
    }

}
