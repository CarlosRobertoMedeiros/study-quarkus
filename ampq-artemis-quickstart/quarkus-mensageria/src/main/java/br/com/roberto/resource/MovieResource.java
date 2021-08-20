package br.com.roberto.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.roberto.model.Movie;
import br.com.roberto.producer.MovieProducer;

@Path("/movie")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

	@Inject
	MovieProducer movieProducer;

	@POST
	public Response send(Movie movie) {
		movieProducer.send(movie);
		return Response.accepted().build();
	}

}
