package br.com.roberto.ifood.marketplace.dto;

import java.math.BigDecimal;

public class PratoDto {
	
	public Long id;
	
	public String nome;
	
	public String descricao;
	
	public BigDecimal preco;

	public PratoDto() {}
	
	public PratoDto(Long id, String nome, String descricao, BigDecimal preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	

}
