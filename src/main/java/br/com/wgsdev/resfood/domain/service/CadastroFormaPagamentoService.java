package br.com.wgsdev.resfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wgsdev.resfood.domain.exception.EntidadeEmUsoException;
import br.com.wgsdev.resfood.domain.exception.FormaPagamentoNaoEncontradaException;
import br.com.wgsdev.resfood.domain.model.FormaPagamento;
import br.com.wgsdev.resfood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

  private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

  @Autowired
  private FormaPagamentoRepository formaPagamentoRepository;

  @Transactional
  public FormaPagamento salvar(FormaPagamento formaPagamento) {
    return formaPagamentoRepository.save(formaPagamento);
  }

  @Transactional
  public void excluir(Long formaPagamentoId) {
    try {
      buscarOuFalhar(formaPagamentoId);
      formaPagamentoRepository.deleteById(formaPagamentoId);
      formaPagamentoRepository.flush();
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(MSG_FORMA_PAGAMENTO_EM_USO);
    }
  }

  public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
    return formaPagamentoRepository.findById(formaPagamentoId)
        .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
  }

}
