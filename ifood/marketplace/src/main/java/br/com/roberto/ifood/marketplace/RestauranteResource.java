package br.com.roberto.ifood.marketplace;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

@Path("restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {
	
	  @Inject
	  PgPool client;
	  
	  @GET
	  @Path("{idRestaurante}/pratos")
	  public Multi<PratoDto> buscarPratos(@PathParam("idRestaurante") Long idRestaurante){
		  return Prato.findByRestaurante(client, idRestaurante);
	  }
	  
	  

}

//TODO: https://www.youtube.com/watch?v=igjMjFOHyK8
//Implementar todo o Crud utilizando o Multiny

//Trabalhar com Filas MQ e artemis
//https://www.udemy.com/course/des-web-quarkus/learn/lecture/19327644#announcements
