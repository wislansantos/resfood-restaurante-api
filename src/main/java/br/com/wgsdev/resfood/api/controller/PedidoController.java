package br.com.wgsdev.resfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.wgsdev.resfood.api.assembler.PedidoInputDisassembler;
import br.com.wgsdev.resfood.api.assembler.PedidoModelAssembler;
import br.com.wgsdev.resfood.api.assembler.PedidoResumoModelAssembler;
import br.com.wgsdev.resfood.api.model.PedidoModel;
import br.com.wgsdev.resfood.api.model.PedidoResumoModel;
import br.com.wgsdev.resfood.api.model.input.PedidoInput;
import br.com.wgsdev.resfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.wgsdev.resfood.domain.exception.NegocioException;
import br.com.wgsdev.resfood.domain.model.Pedido;
import br.com.wgsdev.resfood.domain.model.Usuario;
import br.com.wgsdev.resfood.domain.repository.PedidoRepository;
import br.com.wgsdev.resfood.domain.service.EmissaoPedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private EmissaoPedidoService emissaoPedido;

  @Autowired
  private PedidoModelAssembler pedidoModelAssembler;

  @Autowired
  private PedidoResumoModelAssembler pedidoResumoModelAssembler;

  @Autowired
  private PedidoInputDisassembler pedidoInputDisassembler;

  @GetMapping
  public List<PedidoResumoModel> listar() {
    List<Pedido> todosPedidos = pedidoRepository.findAll();

    return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
    try {
      Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

      // TODO pegar usuário autenticado
      novoPedido.setCliente(new Usuario());
      novoPedido.getCliente().setId(1L);

      novoPedido = emissaoPedido.emitir(novoPedido);

      return pedidoModelAssembler.toModel(novoPedido);
    } catch (EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @GetMapping("/{pedidoId}")
  public PedidoModel buscar(@PathVariable Long pedidoId) {
    Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

    return pedidoModelAssembler.toModel(pedido);
  }

}
