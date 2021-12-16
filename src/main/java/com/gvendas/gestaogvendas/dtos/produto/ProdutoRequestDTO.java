package com.gvendas.gestaogvendas.dtos.produto;

import com.gvendas.gestaogvendas.entities.Categoria;
import com.gvendas.gestaogvendas.entities.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Produto Request DTO")
public class ProdutoRequestDTO {
  @ApiModelProperty(value="Descrição")
  @NotBlank(message = "Descrição")
  @Length(min = 5, max = 100, message = "Descrição")
  private String descricao;

  @ApiModelProperty(value="Quantidade")
  @NotNull(message = "Quantidade")
  private Integer quantidade;

  @ApiModelProperty(value="Preço custo")
  @NotNull(message = "Preço de custo")
  private BigDecimal precoCusto;

  @ApiModelProperty(value="Preço venda")
  @NotNull(message = "Preço de venda")
  private BigDecimal precoVenda;

  @ApiModelProperty(value="Observação")
  @Length(max = 500, message = "Observação")
  private String observacao;

  // Constructors
  public Produto DtoToProduct(Long codigo) {
    return new Produto(
        descricao,
        quantidade,
        precoCusto,
        precoVenda,
        observacao,
        new Categoria(codigo)
    );
  }

  public Produto DtoToProduct(Long codigoProduto, Long codigoCategoria) {
    return new Produto(
        codigoProduto,
        descricao,
        quantidade,
        precoCusto,
        precoVenda,
        observacao,
        new Categoria(codigoCategoria)
    );
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public BigDecimal getPrecoCusto() {
    return precoCusto;
  }

  public void setPrecoCusto(BigDecimal precoCusto) {
    this.precoCusto = precoCusto;
  }

  public BigDecimal getPrecoVenda() {
    return precoVenda;
  }

  public void setPrecoVenda(BigDecimal precoVenda) {
    this.precoVenda = precoVenda;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }
}
