package br.com.roberto.ifood.pedido.dto;

import java.util.List;

public class PedidoRealizadoDto {

	public List<PratoPedidoDto> pratos;

	public RestauranteDto restaurante;

	public String cliente;

	@Override
	public String toString() {
		return "PedidoRealizadoDto [pratos=" + pratos + ", restaurante=" + restaurante + ", cliente=" + cliente + "]";
	}
	
	

}
