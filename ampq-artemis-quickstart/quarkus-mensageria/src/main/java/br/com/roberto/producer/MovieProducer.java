package br.com.roberto.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.com.roberto.model.Movie;

@ApplicationScoped
public class MovieProducer {
	
	@Inject
	@Channel("movies-out")
	Emitter<Movie> emitter;
	
	public void send(Movie movie) {
		emitter.send(movie)
			.whenComplete((sucesso, falha) ->{
				if (falha!=null) {
					System.out.println("Falha "+falha.getMessage());
				}else {
					System.out.println("------------------------------");
					System.out.println(movie);
					System.out.println("Mensagem Enviada com Sucesso !");
					System.out.println("------------------------------");
				}
			});
	}
	
	

}
