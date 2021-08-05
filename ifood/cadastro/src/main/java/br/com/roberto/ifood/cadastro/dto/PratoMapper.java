package br.com.roberto.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import br.com.roberto.ifood.cadastro.Prato;

@Mapper(componentModel = "cdi")
public interface PratoMapper {
	
	PratoDto toDto(Prato p);

    Prato toPrato(AdicionarPratoDto dto);

    void toPrato(AtualizarPratoDto dto, @MappingTarget Prato prato);

}
