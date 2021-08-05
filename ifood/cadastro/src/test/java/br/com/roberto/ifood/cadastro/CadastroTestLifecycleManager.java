package br.com.roberto.ifood.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class CadastroTestLifecycleManager implements QuarkusTestResourceLifecycleManager{

	public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.2"); 
	
	@Override
	public Map<String, String> start() {
		POSTGRES.start();
		Map<String, String> propriedades = new HashMap<String,String>();
		
		//DB
		propriedades.put("quarkus.datasource.reactive.url", "jdbc:mariadb://localhost:5432/postgres");
		propriedades.put("quarkus.datasource.username", "cadastro");
		propriedades.put("quarkus.datasource.password", "cadastro");
		
		return propriedades;
	}

	@Override
	public void stop() {
		if (POSTGRES!=null && POSTGRES.isRunning()) {
			POSTGRES.stop();
		}
	}
}
