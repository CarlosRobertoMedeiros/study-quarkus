package br.com.roberto.ifood.pedido.dto;

import java.math.BigDecimal;

public class PratoPedidoDto {

	public String nome;

	public String descricao;

	public BigDecimal preco;

	public PratoPedidoDto() {
		super();
	}

	public PratoPedidoDto(String nome, String descricao, BigDecimal preco) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "PratoPedidoDto [nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + "]";
	}

}
