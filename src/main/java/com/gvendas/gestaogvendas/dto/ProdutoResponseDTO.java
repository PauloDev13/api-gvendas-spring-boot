package com.gvendas.gestaogvendas.dto;

import com.gvendas.gestaogvendas.entities.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Produto Response DTO")
public class ProdutoResponseDTO {
  @ApiModelProperty(value="Código")
  private Long codigo;

  @ApiModelProperty(value="Descrição")
  private String descricao;

  @ApiModelProperty(value="Quantidade")
  private Integer quantidade;

  @ApiModelProperty(value="Preço custo")
  private BigDecimal precoCusto;

  @ApiModelProperty(value="Preço venda")
  private BigDecimal precoVenda;

  @ApiModelProperty(value="Observação")
  private String observacao;

  @ApiModelProperty(value="Categoria DTO")
  private CategoriaResponseDTO categoriaDto;

  public ProdutoResponseDTO(Long codigo, String descricao, Integer quantidade, BigDecimal precoCusto,
                            BigDecimal precoVenda, String observacao, CategoriaResponseDTO categoriaDto) {
    this.codigo = codigo;
    this.descricao = descricao;
    this.quantidade = quantidade;
    this.precoCusto = precoCusto;
    this.precoVenda = precoVenda;
    this.observacao = observacao;
    this.categoriaDto = categoriaDto;
  }

  public static ProdutoResponseDTO ProductToDTO(Produto produto) {
    return new ProdutoResponseDTO(
        produto.getCodigo(),
        produto.getDescricao(),
        produto.getQuantidade(),
        produto.getPrecoCusto(),
        produto.getPrecoVenda(),
        produto.getObservacao(),
        CategoriaResponseDTO.CategoryToDTO(produto.getCategoria())
    );
  }

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
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

  public CategoriaResponseDTO getCategoriaDto() {
    return categoriaDto;
  }

  public void setCategoriaDto(CategoriaResponseDTO categoriaDto) {
    this.categoriaDto = categoriaDto;
  }
}
