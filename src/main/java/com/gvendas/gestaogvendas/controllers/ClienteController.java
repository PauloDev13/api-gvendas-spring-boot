package com.gvendas.gestaogvendas.controllers;

//import com.gvendas.gestaogvendas.dto.ClienteResponseMapper;

import com.gvendas.gestaogvendas.dto.ClienteResponseDTO;
import com.gvendas.gestaogvendas.entities.Cliente;
import com.gvendas.gestaogvendas.mappers.ClienteResponseMapper;
import com.gvendas.gestaogvendas.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Api(tags = "Cliente")
public class ClienteController {
  private final ClienteService clienteService;
  private final ClienteResponseMapper clienteMapper;

  @ApiOperation(value = "Lista todos os registros de Cliente", nickname = "todosClientes")
  @GetMapping
  public ResponseEntity<List<ClienteResponseDTO>> listAll() {
    List<Cliente> clientesDTO = clienteService.listAll();
    return ResponseEntity.ok(clienteService.listAll().stream()
        .map(clienteMapper::toDTO).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Busca um único registro de Cliente fornecido seu código", nickname = "clientePorCodigo")
  @GetMapping("/{codigo}")
  public ResponseEntity<ClienteResponseDTO> findByCodigo(@PathVariable Long codigo) {
    Optional<Cliente> cliente = clienteService.findByCodigo(codigo);
    return cliente.map(value -> ResponseEntity.ok(clienteMapper.toDTO(value)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
