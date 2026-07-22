package br.com.wgsdev.resfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import br.com.wgsdev.resfood.api.assembler.CozinhaInputDisassembler;
import br.com.wgsdev.resfood.api.assembler.CozinhaModelAssembler;
import br.com.wgsdev.resfood.api.model.CozinhaModel;
import br.com.wgsdev.resfood.api.model.input.CozinhaInput;
import br.com.wgsdev.resfood.domain.model.Cozinha;
import br.com.wgsdev.resfood.domain.repository.CozinhaRepository;
import br.com.wgsdev.resfood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinha;

  @Autowired
  private CozinhaModelAssembler cozinhaModelAssembler;

  @Autowired
  private CozinhaInputDisassembler cozinhaInputDisassembler;

  @GetMapping
  public List<CozinhaModel> listar() {
    return cozinhaModelAssembler.toCollectionModel(cozinhaRepository.findAll());
  }

  @GetMapping("/{cozinhaId}")
  public CozinhaModel buscar(@PathVariable Long cozinhaId) {
    return cozinhaModelAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
    Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
    return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinha));
  }

  @PutMapping("/{cozinhaId}")
  public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
    Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

    cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
    return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
  }

  @DeleteMapping("/{cozinhaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long cozinhaId) {
    cadastroCozinha.excluir(cozinhaId);
  }

}
