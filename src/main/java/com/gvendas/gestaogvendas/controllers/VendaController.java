package com.gvendas.gestaogvendas.controllers;

import com.gvendas.gestaogvendas.dtos.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaogvendas.dtos.venda.VendaRequestDTO;
import com.gvendas.gestaogvendas.services.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Venda")
@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @ApiOperation(value = "Listar vendas por Cliente", nickname = "listarVendaPorCliente")
    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> ListVendaByCliente(@PathVariable Long codigoCliente) {
      return ResponseEntity.ok(vendaService.ListVendaByCliente(codigoCliente));
    }

    @ApiOperation(value = "Listar vendas por código", nickname = "listarVendaPorCódigo")
    @GetMapping("/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> listVendaByCodigo(@PathVariable Long codigoVenda) {
        return ResponseEntity.ok(vendaService.listVendaByCodigo(codigoVenda));
    }

    @ApiOperation(value = "Registrar vendas", nickname = "registrarVenda")
    @PostMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> save(@PathVariable Long codigoCliente,
                                                        @Valid @RequestBody VendaRequestDTO vendaRequestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.save(codigoCliente, vendaRequestDTO));
    }


}
