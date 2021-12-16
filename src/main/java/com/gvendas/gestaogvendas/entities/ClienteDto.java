package com.gvendas.gestaogvendas.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ClienteDto implements Serializable {
  private final String nome;
}
