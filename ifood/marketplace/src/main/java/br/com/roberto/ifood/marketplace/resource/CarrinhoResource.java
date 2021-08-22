package br.com.roberto.ifood.marketplace.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.com.roberto.ifood.marketplace.dto.PedidoRealizadoDto;
import br.com.roberto.ifood.marketplace.dto.PratoDto;
import br.com.roberto.ifood.marketplace.dto.PratoPedidoDto;
import br.com.roberto.ifood.marketplace.dto.RestauranteDto;
import br.com.roberto.ifood.marketplace.entity.Prato;
import br.com.roberto.ifood.marketplace.entity.PratoCarrinho;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

@Path("carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {

	private static final String CLIENTE = "a";

	@Inject
	PgPool client;
	
	@Inject
	@Channel("pedidos")
	Emitter<PedidoRealizadoDto> emitterPedido;
	

	@GET
	public Uni<List<PratoCarrinho>> buscarCarrinho() {
		return PratoCarrinho.findCarrinho(client, CLIENTE);

	}

	@POST
	@Path("/prato/{idPrato}")
	public Uni<Long> adicionarPrato(@PathParam("idPrato") Long idPrato) {
		PratoCarrinho pc = new PratoCarrinho();
		pc.cliente = CLIENTE;
		pc.prato = idPrato;
		return PratoCarrinho.save(client, CLIENTE, idPrato);

	}

	@POST
	@Path("/realizar-pedido")
	public Uni<Boolean> finalizar() {
		PedidoRealizadoDto pedido = new PedidoRealizadoDto();
		String cliente = CLIENTE;
		pedido.cliente = cliente;
		List<PratoCarrinho> pratoCarrinho = PratoCarrinho.findCarrinho(client, cliente).await().indefinitely();
		
		// Utilizar mapstruts
		List<PratoPedidoDto> pratos = pratoCarrinho.stream().map(pc -> from(pc)).collect(Collectors.toList());
		pedido.pratos = pratos;

		RestauranteDto restaurante = new RestauranteDto();
		restaurante.nome = "nome restaurante";
		pedido.restaurante = restaurante;
		emitterPedido.send(pedido);
		return PratoCarrinho.delete(client, cliente);
	}

	private PratoPedidoDto from(PratoCarrinho pratoCarrinho) {
		PratoDto dto = Prato.findById(client, pratoCarrinho.prato).await().indefinitely();
		return new PratoPedidoDto(dto.nome, dto.descricao, dto.preco);
	}

}
//Continuar daqui a partir de 03:10
//https://www.udemy.com/course/des-web-quarkus/learn/lecture/19350294#announcements
