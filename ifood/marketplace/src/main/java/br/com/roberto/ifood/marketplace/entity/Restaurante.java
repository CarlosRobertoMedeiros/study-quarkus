package br.com.roberto.ifood.marketplace.entity;


import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

public class Restaurante {
	
	public Long id;
	public String nome;
	private Localizacao localizacao;
	
	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nome=" + nome + ", localizacao=" + localizacao + "]";
	}

	public void persist(PgPool client) {
		client
			.preparedQuery("insert into tb_localizacao(id,latitude,longitude) values ($1, $2, $3)")
			.executeAndAwait((Tuple.of(localizacao.id, localizacao.latitude, localizacao.longitude)));
			
		client
			.preparedQuery("insert into tb_restaurante(id,nome,localizacao_id) values ($1, $2, $3)") 
			.executeAndAwait((Tuple.of(id, nome, localizacao.id)));
		
	}
	
	

}


//Continuar daqui
//https://www.udemy.com/course/des-web-quarkus/learn/lecture/19303588#announcements