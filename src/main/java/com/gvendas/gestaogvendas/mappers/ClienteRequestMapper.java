package com.gvendas.gestaogvendas.mappers;

import com.gvendas.gestaogvendas.dto.ClienteRequestDTO;
import com.gvendas.gestaogvendas.entities.Cliente;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClienteRequestMapper {

  ClienteRequestDTO totDTO(Cliente cliente);

  @Mapping(target = "codigo", ignore = true)
  Cliente fromDTO(ClienteRequestDTO clienteRequestDTO);

//  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//  void updateDto(ClienteRequestDTO clienteRequestDTO, @MappingTarget Cliente cliente);
}
