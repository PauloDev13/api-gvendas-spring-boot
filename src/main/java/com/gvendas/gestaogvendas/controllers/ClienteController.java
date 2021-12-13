package com.gvendas.gestaogvendas.controllers;

//import com.gvendas.gestaogvendas.dto.ClienteResponseMapper;

import com.gvendas.gestaogvendas.dto.ClienteRequestDTO;
import com.gvendas.gestaogvendas.dto.ClienteResponseDTO;
import com.gvendas.gestaogvendas.entities.Cliente;
import com.gvendas.gestaogvendas.mappers.ClienteRequestMapper;
import com.gvendas.gestaogvendas.mappers.ClienteResponseMapper;
import com.gvendas.gestaogvendas.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Api(tags = "Cliente")
public class ClienteController {
  private final ClienteService clienteService;
  private final ClienteResponseMapper clienteResponseMapper;
  private final ClienteRequestMapper clienteRequestMapper;

  @ApiOperation(value = "Lista todos os registros de Cliente", nickname = "todosClientes")
  @GetMapping
  public ResponseEntity<List<ClienteResponseDTO>> listAll() {
    List<Cliente> clientesDTO = clienteService.listAll();
    return ResponseEntity.ok(clienteService.listAll().stream()
        .map(clienteResponseMapper::toDTO).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Busca um único registro de Cliente fornecido seu código", nickname = "clientePorCodigo")
  @GetMapping("/{codigo}")
  public ResponseEntity<ClienteResponseDTO> findByCodigo(@PathVariable Long codigo) {
    Optional<Cliente> cliente = clienteService.findByCodigo(codigo);
    return cliente.map(value -> ResponseEntity.ok(clienteResponseMapper.toDTO(value)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @ApiOperation(value = "Insere um registro de Cliente", nickname = "salvaCliente")
  @PostMapping
  public ResponseEntity<ClienteResponseDTO> save(@Valid @RequestBody ClienteRequestDTO clienteDTO) {
    Cliente clienteSaved = clienteService.save(clienteRequestMapper.fromDTO(clienteDTO));
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseMapper.toDTO(clienteSaved));
  }

  @ApiOperation(
      value = "Atualiza um único registro de Cliente fornecido seu código",
      nickname = "atualizaCliente"
  )
  @PutMapping("/{codigo}")
  public ResponseEntity<ClienteResponseDTO> update (
     @PathVariable Long codigo,  @Valid @RequestBody ClienteRequestDTO clienteDTO) {

    Cliente clienteUpdated = clienteService.update(clienteRequestMapper.fromDTO(clienteDTO), codigo);
    return ResponseEntity.ok(clienteResponseMapper.toDTO(clienteUpdated));
  }
}
