package br.com.wgsdev.resfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.wgsdev.resfood.domain.model.Estado;

public class CidadeMixin {

  @JsonIgnoreProperties(value = "nome", allowGetters = true)
  private Estado estado;

}
