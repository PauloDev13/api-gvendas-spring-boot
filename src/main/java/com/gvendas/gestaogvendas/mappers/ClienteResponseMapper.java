package com.gvendas.gestaogvendas.mappers;

import com.gvendas.gestaogvendas.dtos.cliente.ClienteResponseDTO;
import com.gvendas.gestaogvendas.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteResponseMapper {
  ClienteResponseDTO toDTO(Cliente cliente);
  Cliente fromDTO(ClienteResponseDTO clienteDTO);

  //  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  //  void updateDto(ClienteResponseDTO clienteDto, @MappingTarget Cliente cliente);
}
