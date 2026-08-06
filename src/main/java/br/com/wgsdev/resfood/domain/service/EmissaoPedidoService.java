package br.com.wgsdev.resfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wgsdev.resfood.domain.exception.PedidoNaoEncontradoException;
import br.com.wgsdev.resfood.domain.model.Pedido;
import br.com.wgsdev.resfood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

  @Autowired
  private PedidoRepository pedidoRepository;

  public Pedido buscarOuFalhar(Long pedidoId) {
    return pedidoRepository.findById(pedidoId)
        .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
  }

}
