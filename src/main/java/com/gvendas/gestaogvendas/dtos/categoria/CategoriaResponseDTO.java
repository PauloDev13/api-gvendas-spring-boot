package com.gvendas.gestaogvendas.dtos.categoria;

import com.gvendas.gestaogvendas.entities.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Categoria Response DTO")
public class CategoriaResponseDTO {

  @ApiModelProperty(value="Código")
  private Long codigo;

  @ApiModelProperty(value="Nome")
  private String nome;

  public CategoriaResponseDTO(Long codigo, String nome) {
    this.codigo = codigo;
    this.nome = nome;
  }

  public static CategoriaResponseDTO categoryToDTO(Categoria categoria) {
    return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
  }

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
