package br.com.roberto.ifood.pedido.deserializer;

import br.com.roberto.ifood.pedido.dto.PedidoRealizadoDto;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PedidoDeserializer extends ObjectMapperDeserializer<PedidoRealizadoDto> {

	public PedidoDeserializer() {
		super(PedidoRealizadoDto.class);
	}

}
