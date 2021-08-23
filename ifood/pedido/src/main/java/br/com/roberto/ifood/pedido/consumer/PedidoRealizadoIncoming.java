package br.com.roberto.ifood.pedido.consumer;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.Decimal128;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import br.com.roberto.ifood.pedido.dto.PedidoRealizadoDto;
import br.com.roberto.ifood.pedido.dto.PratoPedidoDto;
import br.com.roberto.ifood.pedido.entity.Pedido;
import br.com.roberto.ifood.pedido.entity.Prato;
import br.com.roberto.ifood.pedido.entity.Restaurante;

@ApplicationScoped
public class PedidoRealizadoIncoming {
	
	@Incoming("pedidos")
	public void lerPedidos(PedidoRealizadoDto dto) {
		System.out.println("------------------"); 
		System.out.println(dto); 
		
		Pedido p = new Pedido();
		p.cliente = dto.cliente;
		p.pratos = new ArrayList<>();
		Restaurante restaurante = new Restaurante();
		dto.pratos.forEach(prato -> p.pratos.add(from(prato)));
		restaurante.nome = dto.restaurante.nome;
		p.restaurante = restaurante;
		
		p.persist();
	}

	private Prato from(PratoPedidoDto prato) {
		Prato p = new Prato();
		p.descricao = prato.descricao;
		p.nome = prato.nome;
		p.preco = new Decimal128(prato.preco);
		return null;
	}
	

	
	
	
}
