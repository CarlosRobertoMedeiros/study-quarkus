package br.com.roberto.ifood.cadastro.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import br.com.roberto.ifood.cadastro.dto.AdicionarPratoDto;
import br.com.roberto.ifood.cadastro.dto.AdicionarRestauranteDto;
import br.com.roberto.ifood.cadastro.dto.AtualizarPratoDto;
import br.com.roberto.ifood.cadastro.dto.AtualizarRestauranteDto;
import br.com.roberto.ifood.cadastro.dto.PratoDto;
import br.com.roberto.ifood.cadastro.dto.PratoMapper;
import br.com.roberto.ifood.cadastro.dto.RestauranteDto;
import br.com.roberto.ifood.cadastro.dto.RestauranteMapper;
import br.com.roberto.ifood.cadastro.entity.Prato;
import br.com.roberto.ifood.cadastro.entity.Restaurante;
import br.com.roberto.ifood.cadastro.openapi.RestauranteResourceOpenApi;
import br.com.roberto.ifood.cadastro.producer.RestauranteProducer;


@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-oauth", type = SecuritySchemeType.OAUTH2, 
	flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
@SecurityRequirement(name="ifood-oauth", scopes = {})
@ApplicationScoped
public class RestauranteResource implements RestauranteResourceOpenApi {

	@Inject
    RestauranteMapper restauranteMapper;
    
    @Inject
    PratoMapper pratoMapper;
	
	//Usado para 
    @Inject
	RestauranteProducer restauranteProducer;
    
    @GET
	@Counted(name = "Quantidade buscas Restaurantes") //Metrics
	@SimplyTimed(name = "Tempo Simples de Busca")
	@Timed(name="Tempo Completo de Busca")
	//@Gauge() Consulta de tempos em tempos
    public List<RestauranteDto> buscar() {
        Stream<Restaurante> restaurantes =  Restaurante.streamAll();
		return restaurantes
				.map(r -> restauranteMapper.toRestauranteDto(r))
				.collect(Collectors.toList());
    }
    
    @POST
    @Transactional
    public Response adicionar(@Valid AdicionarRestauranteDto dto) {
    	Restaurante restaurante = restauranteMapper.toRestaurante(dto);
    	
    	restaurante.persist();
    	restauranteProducer.send(restaurante);
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id,  AtualizarRestauranteDto dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
    	
    	if (restauranteOp.isEmpty()) {
    		throw new NotFoundException();
    	}
    	Restaurante restaurante = restauranteOp.get();
    	
    	restauranteMapper.toRestaurante(dto,restaurante);
    	restaurante.persist();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);

    	restauranteOp.ifPresentOrElse(Restaurante::delete, () ->{ 
    		throw new NotFoundException();
    	});
    }
    
    //EndPoints de Pratos
    @GET
    @Path("{idRestaurante}/pratos")
    public List<PratoDto> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
        	throw new NotFoundException("Restaurante Não Existe");
        }
        Stream<Prato> pratos = Prato.stream("restaurante", restauranteOp.get());
        return pratos
        		.map(p -> pratoMapper.toDto(p))
        		.collect(Collectors.toList());
    }
    
    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, AdicionarPratoDto dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
        	throw new NotFoundException("Restaurante Não Existe");
        }
        
        Prato prato = pratoMapper.toPrato(dto);
        prato.restaurante = restauranteOp.get();
        prato.persist();
        return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    public void atualizarPrato(@PathParam("idRestaurante") Long idRestaurante,  
    						   @PathParam("id") Long id,
    						   AtualizarPratoDto dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
        	throw new NotFoundException("Restaurante Não Existe");
        }
        
        Optional<Prato> pratoOp = Prato.findByIdOptional(id);
        
        if (pratoOp.isEmpty()) {
        	throw new NotFoundException("Prato Não Existe");
        }
        
        Prato prato = pratoOp.get();
        pratoMapper.toPrato(dto,prato);
        prato.persist();
    }
    
    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    public void delete(@PathParam("idRestaurante") Long idRestaurante,  @PathParam("id") Long id) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);

    	if (restauranteOp.isEmpty()) {
        	throw new NotFoundException("Restaurante Não Existe");
        }
        
    	Optional<Prato> pratoOp = Prato.findByIdOptional(id);
        
        pratoOp.ifPresentOrElse(Prato::delete, () ->{
        	throw new NotFoundException();
        });
    }
}

//TODO: Continuar Daqui
//Adicionando KeyCloak
//https://www.udemy.com/course/des-web-quarkus/learn/lecture/19192844#announcements
