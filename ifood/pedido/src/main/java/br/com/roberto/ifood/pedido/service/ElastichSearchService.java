package br.com.roberto.ifood.pedido.service;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class ElastichSearchService {
	
	private RestHighLevelClient client;
	
	
	void startup(@Observes StartupEvent startupEvent) {
		
		client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost",9200,"http"))
				);
	}
	
	void shutdwon(@Observes ShutdownEvent shutdownEvent) {
		if (client!=null){
			try {
				client.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getCause()+" - "+ e.getMessage());
			}
			
		}
	}
	
	public void index(String index, String json) {
		IndexRequest ir = new IndexRequest(index).source(json,XContentType.JSON);
		try {
			client.index(ir,  RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException(e.getCause()+" - "+ e.getMessage());
		}
	}
	
	

}
