package br.com.roberto.ifood.cadastro.openapi;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.roberto.ifood.cadastro.dto.AdicionarPratoDto;
import br.com.roberto.ifood.cadastro.dto.AdicionarRestauranteDto;
import br.com.roberto.ifood.cadastro.dto.AtualizarPratoDto;
import br.com.roberto.ifood.cadastro.dto.AtualizarRestauranteDto;
import br.com.roberto.ifood.cadastro.dto.PratoDto;
import br.com.roberto.ifood.cadastro.dto.RestauranteDto;
import br.com.roberto.ifood.cadastro.infra.ConstraintViolationResponse;

@Tag(name = "restaurante") //Obs usando mais tag você duplica o local onde quer disponibilizar a funcionalidade
public interface RestauranteResourceOpenApi {
	
	public List<RestauranteDto> buscar();
	
	@APIResponse(responseCode = "201", description = "Caso restaurante seja cadastrado com sucesso")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class)))
	public Response adicionar(@Valid AdicionarRestauranteDto dto);
	
	public void atualizar(@PathParam("id") Long id,  AtualizarRestauranteDto dto);
	
	public void delete(@PathParam("id") Long id); 
	
    @Tag(name="prato")
	public List<PratoDto> buscarPratos(@PathParam("idRestaurante") Long idRestaurante);
	
    @Tag(name="prato")
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, AdicionarPratoDto dto); 
	
    @Tag(name="prato")
    public void atualizarPrato(@PathParam("idRestaurante") Long idRestaurante,@PathParam("id") Long id,AtualizarPratoDto dto);
	
    @Tag(name="prato")
    public void delete(@PathParam("idRestaurante") Long idRestaurante,  @PathParam("id") Long id);

}
