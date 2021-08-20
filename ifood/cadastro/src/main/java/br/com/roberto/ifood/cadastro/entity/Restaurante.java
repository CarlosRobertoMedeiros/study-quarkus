package br.com.roberto.ifood.cadastro.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="tb_restaurante")
public class Restaurante extends PanacheEntityBase {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long id;
	 	
	 	public String proprietario;
	 	
	 	public String cnpj;
	 	
	 	public String nome;
	 	
	 	@OneToOne(cascade = CascadeType.ALL)
	 	public Localizacao localizacao;
	 	
	 	@CreationTimestamp
	 	@Schema(hidden = true)
	 	public Date dataCriacao;
	 	
	 	@UpdateTimestamp
	 	@Schema(hidden = true)
	 	public Date dataAtualizacao;

		@Override
		public String toString() {
			return "Restaurante [id=" + id + ", proprietario=" + proprietario + ", cnpj=" + cnpj + ", nome=" + nome
					+ ", localizacao=" + localizacao + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
					+ dataAtualizacao + "]";
		}
	 	
	 	
	 	
	 	
	 	
	 	
	 	
}
