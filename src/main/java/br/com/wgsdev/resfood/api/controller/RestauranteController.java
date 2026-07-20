package br.com.wgsdev.resfood.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.wgsdev.resfood.domain.model.Cozinha;
import br.com.wgsdev.resfood.domain.model.Restaurante;
import br.com.wgsdev.resfood.domain.repository.RestauranteRepository;
import br.com.wgsdev.resfood.domain.service.CadastroRestauranteService;
import br.com.wgsdev.resfood.domain.exception.NegocioException;
import br.com.wgsdev.resfood.api.model.CozinhaModel;
import br.com.wgsdev.resfood.api.model.RestauranteModel;
import br.com.wgsdev.resfood.api.model.input.RestauranteInput;
import br.com.wgsdev.resfood.domain.exception.CozinhaNaoEncontradaException;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroRestauranteService cadastroRestaurante;

  @GetMapping
  public List<RestauranteModel> listar() {
    return toCollectionModel(restauranteRepository.findAll());
  }

  @GetMapping("/{restauranteId}")
  public RestauranteModel buscar(@PathVariable Long restauranteId) {
    Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

    return toModel(restaurante);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
    try {
      Restaurante restaurante = toDomainObject(restauranteInput);

      return toModel(cadastroRestaurante.salvar(restaurante));
    } catch (CozinhaNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @PutMapping("/{restauranteId}")
  public RestauranteModel atualizar(@PathVariable Long restauranteId,
      @RequestBody @Valid RestauranteInput restauranteInput) {
    Restaurante restaurante = toDomainObject(restauranteInput);

    Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
    BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
        "produtos");

    try {
      return toModel(cadastroRestaurante.salvar(restauranteAtual));
    } catch (CozinhaNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  private RestauranteModel toModel(Restaurante restaurante) {
    CozinhaModel cozinhaModel = new CozinhaModel();
    cozinhaModel.setId(restaurante.getCozinha().getId());
    cozinhaModel.setNome(restaurante.getCozinha().getNome());

    RestauranteModel restauranteModel = new RestauranteModel();
    restauranteModel.setId(restaurante.getId());
    restauranteModel.setNome(restaurante.getNome());
    restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
    restauranteModel.setCozinha(cozinhaModel);

    return restauranteModel;
  }

  private List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
    return restaurantes.stream()
        .map(restaurante -> toModel(restaurante))
        .collect(Collectors.toList());
  }

  private Restaurante toDomainObject(RestauranteInput restauranteInput) {
    Restaurante restaurante = new Restaurante();
    restaurante.setNome(restauranteInput.getNome());
    restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

    Cozinha cozinha = new Cozinha();
    cozinha.setId(restauranteInput.getCozinha().getId());

    restaurante.setCozinha(cozinha);
    return restaurante;
  }

}
