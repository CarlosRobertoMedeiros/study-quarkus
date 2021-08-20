package br.com.roberto.ifood.marketplace.entity;

import java.math.BigDecimal;

import br.com.roberto.ifood.marketplace.dto.PratoDto;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

public class Prato {

	public Long id;
	
	public String nome;
	
	public String descricao;
	
	public Restaurante restaurante;
	
	public BigDecimal preco;

	public static Multi<PratoDto> findAll(PgPool client) {
		
		return client.query("select * from TB_Prato")
			.execute()
			.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
			.onItem().transform(Prato::from);
		
	}
	
	private static PratoDto from(Row row) {
		return new PratoDto(row.getLong("id"),
							row.getString("nome"),
							row.getString("descricao"),
							row.getBigDecimal("preco"));
	}

	/*
	public static Uni<PratoDto> findByRestaurante(PgPool client, Long idRestaurante) {

		return client
					.preparedQuery("select * from TB_Prato where prato.restaurante_id = $1 order by nome asc") 
					.execute(Tuple.of(idRestaurante))
					.onItem()
					.transform(m -> m.iterator().hasNext() ? from(m.iterator().next()) : null);
		
				
	}*/
	
	public static Multi<PratoDto> findByRestaurante(PgPool client, Long idRestaurante) {

		return client
					.preparedQuery("select * from TB_Prato where restaurante_id = $1 order by nome asc") 
					.execute(Tuple.of(idRestaurante))
					.onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
					.onItem().transform(Prato::from);
	}

}
