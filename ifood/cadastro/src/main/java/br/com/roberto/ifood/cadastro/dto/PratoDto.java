package br.com.roberto.ifood.cadastro.dto;

import java.math.BigDecimal;

public class PratoDto {
	
	 public Long id;
	 public String nome;
     public String descricao;
     public RestauranteDto restaurante;
     public BigDecimal preco;

}
