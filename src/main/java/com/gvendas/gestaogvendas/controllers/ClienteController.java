package com.gvendas.gestaogvendas.controllers;

import com.gvendas.gestaogvendas.entities.Cliente;
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

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Api(tags = "Cliente")
public class ClienteController {
  private final ClienteService clienteService;

  @ApiOperation(value = "Lista todos os registros de Cliente", nickname = "todosClientes")
  @GetMapping
  public ResponseEntity<List<Cliente>> listAll() {
    return ResponseEntity.ok(clienteService.listAll());
  }

  @ApiOperation(value = "Busca um único registro de Cliente fornecido seu código", nickname = "clientePorCodigo")
  @GetMapping("/{codigo}")
  public ResponseEntity<Optional<Cliente>> findByCodigo(@PathVariable Long codigo) {
    Optional<Cliente> cliente = clienteService.findByCodigo(codigo);
    return cliente.isPresent() ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
  }
}
