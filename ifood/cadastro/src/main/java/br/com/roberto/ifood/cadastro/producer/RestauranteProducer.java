package br.com.roberto.ifood.cadastro.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.com.roberto.ifood.cadastro.entity.Restaurante;

@ApplicationScoped
public class RestauranteProducer {
	
	@Inject
	@Channel("restaurante-out")
	Emitter<Restaurante> emitter;
	
	public void send(Restaurante restaurante) {
		emitter.send(restaurante)
			.whenComplete((sucesso, falha) ->{
				if (falha!=null) {
					System.out.println("Falha "+falha.getMessage());
				}else {
					System.out.println("------------------------------");
					System.out.println(restaurante);
					System.out.println("Restaurante Enviado com Sucesso !");
					System.out.println("------------------------------");
				}
			});
	}
	

}
