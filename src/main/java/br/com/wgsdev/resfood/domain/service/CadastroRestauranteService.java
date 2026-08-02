package br.com.wgsdev.resfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wgsdev.resfood.domain.exception.EntidadeEmUsoException;
import br.com.wgsdev.resfood.domain.exception.RestauranteNaoEncontradoException;
import br.com.wgsdev.resfood.domain.model.Cidade;
import br.com.wgsdev.resfood.domain.model.Cozinha;
import br.com.wgsdev.resfood.domain.model.FormaPagamento;
import br.com.wgsdev.resfood.domain.model.Restaurante;
import br.com.wgsdev.resfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

  private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinha;

  @Autowired
  private CadastroCidadeService cadastroCidade;

  @Autowired
  private CadastroFormaPagamentoService cadastroFormaPagamento;

  @Transactional
  public Restaurante salvar(Restaurante restaurante) {
    Long cozinhaId = restaurante.getCozinha().getId();
    Long cidadeId = restaurante.getEndereco().getCidade().getId();

    Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
    Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

    restaurante.setCozinha(cozinha);
    restaurante.getEndereco().setCidade(cidade);

    return restauranteRepository.save(restaurante);
  }

  @Transactional
  public void excluir(Long restauranteId) {
    try {
      buscarOuFalhar(restauranteId);
      restauranteRepository.deleteById(restauranteId);
      restauranteRepository.flush();
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
    }
  }

  @Transactional
  public void ativar(Long restauranteId) {
    Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

    restauranteAtual.ativar();
  }

  @Transactional
  public void inativar(Long restauranteId) {
    Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

    restauranteAtual.inativar();
  }

  @Transactional
  public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
    Restaurante restaurante = buscarOuFalhar(restauranteId);
    FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

    restaurante.removerFormaPagamento(formaPagamento);
  }

  @Transactional
  public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
    Restaurante restaurante = buscarOuFalhar(restauranteId);
    FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

    restaurante.adicionarFormaPagamento(formaPagamento);
  }

  public Restaurante buscarOuFalhar(Long restauranteId) {
    return restauranteRepository.findById(restauranteId)
        .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
  }

}
