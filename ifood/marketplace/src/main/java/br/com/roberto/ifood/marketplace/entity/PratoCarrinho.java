package br.com.roberto.ifood.marketplace.entity;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

public class PratoCarrinho {
	
	public String cliente;
	
	public Long prato;
	
	public PratoCarrinho() {};

	public PratoCarrinho(String cliente, Long prato) {
		super();
		this.cliente = cliente;
		this.prato = prato;
	}


	public static Uni<Long> save(PgPool client, String cliente, Long prato) {
		return client
			.preparedQuery("INSERT INTO tb_prato_cliente (cliente, prato) VALUES ($1, $2) RETURNING (prato)")
			.execute(Tuple.of(cliente , prato))
			.map(pgRowSet -> pgRowSet.iterator().next().getLong("prato"));
	}
	
	public static Uni<List<PratoCarrinho>> findCarrinho(PgPool client, String cliente) {
		return client
				.preparedQuery("SELECT prato, cliente FROM tb_prato_cliente WHERE cliente = $1")
				.execute(Tuple.of(cliente))
				.map(pgRowSet -> {
                    List<PratoCarrinho> list = new ArrayList();
                    for (Row row : pgRowSet) {
                        list.add(toPratoCarrinho(row));
                    }
                    return list;
                });
	}
	
	public static Uni<Boolean> delete(PgPool client, String cliente) {
        return client
        		.preparedQuery("DELETE FROM tb_prato_cliente WHERE cliente = $1")
        		.execute(Tuple.of(cliente))
                .map(pgRowSet -> pgRowSet.rowCount() == 1);

    }
	
	
	
	private static PratoCarrinho toPratoCarrinho(Row row) {
		return new PratoCarrinho(row.getString("cliente"), row.getLong("prato"));
	}



	@Override
	public String toString() {
		return "PratoCarrinho [cliente=" + cliente + ", prato=" + prato + "]";
	}
	
	

}
