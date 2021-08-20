package br.com.roberto.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.roberto.ifood.cadastro.entity.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
	
	@Mapping(target = "nome", source = "nomeFantasia")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAtualizacao", ignore = true)
	@Mapping(target = "localizacao.id", ignore = true)
	public Restaurante toRestaurante(AdicionarRestauranteDto dto);
	
	//O MappingTarget só pode existir um por assinatura
	@Mapping(target = "nome", source = "nomeFantasia")
	public void toRestaurante(AtualizarRestauranteDto dto, @MappingTarget Restaurante restaurante);
	
	 @Mapping(target = "nomeFantasia", source = "nome")
	 //Exemplo de formatação.
	 @Mapping(target = "dataCriacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
	 public RestauranteDto toRestauranteDto(Restaurante r);
	

}
