package br.com.wgsdev.resfood;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.wgsdev.resfood.domain.model.Cozinha;
import br.com.wgsdev.resfood.domain.service.CadastroCozinhaService;
import br.com.wgsdev.resfood.domain.exception.EntidadeEmUsoException;
import br.com.wgsdev.resfood.domain.exception.CozinhaNaoEncontradaException;

@SpringBootTest
class CadastroCozinhaIT {

  @Autowired
  private CadastroCozinhaService cadastroCozinha;

  @Test
  public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
    Cozinha novaCozinha = new Cozinha();
    novaCozinha.setNome("Chinesa");

    novaCozinha = cadastroCozinha.salvar(novaCozinha);

    assertThat(novaCozinha).isNotNull();
    assertThat(novaCozinha.getId()).isNotNull();
  }

  @Test
  public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
    Cozinha novaCozinha = new Cozinha();
    novaCozinha.setNome(null);

    assertThrows(ConstraintViolationException.class, () -> {
      cadastroCozinha.salvar(novaCozinha);
    });
  }

  @Test
  public void deveFalhar_QuandoExcluirCozinhaEmUso() {
    assertThrows(EntidadeEmUsoException.class, () -> {
      cadastroCozinha.excluir(1L);
    });
  }

  @Test
  public void deveFalhar_QuandoExcluirCozinhaInexistente() {
    assertThrows(CozinhaNaoEncontradaException.class, () -> {
      cadastroCozinha.excluir(100L);
    });
  }

}
