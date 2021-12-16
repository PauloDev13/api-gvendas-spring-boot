package com.gvendas.gestaogvendas.dtos.categoria;

import com.gvendas.gestaogvendas.entities.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel("Categoria Request DTO")
public class CategoriaRequestDTO {

  @ApiModelProperty(value="Nome")
  @NotBlank(message = "Nome")
  @Length(min = 5, max = 50, message = "Nome")
  private String nome;

  public Categoria DtoToCategory() {
    return new Categoria(nome);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
