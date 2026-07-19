package br.com.wgsdev.resfood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.wgsdev.resfood.domain.model.Cozinha;
import br.com.wgsdev.resfood.domain.model.Endereco;
import br.com.wgsdev.resfood.domain.model.FormaPagamento;
import br.com.wgsdev.resfood.domain.model.Produto;

public class RestauranteMixin {

  @JsonIgnoreProperties(value = "nome", allowGetters = true)
  private Cozinha cozinha;

  @JsonIgnore
  private Endereco endereco;

  // @JsonIgnore
  private OffsetDateTime dataCadastro;

  // @JsonIgnore
  private OffsetDateTime dataAtualizacao;

  @JsonIgnore
  private List<FormaPagamento> formasPagamento = new ArrayList<>();

  @JsonIgnore
  private List<Produto> produtos = new ArrayList<>();

}
