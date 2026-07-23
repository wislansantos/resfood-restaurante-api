package br.com.wgsdev.resfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.wgsdev.resfood.api.model.input.FormaPagamentoInput;
import br.com.wgsdev.resfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoInputDisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
    return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
  }

  public void copyToDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento) {
    modelMapper.map(formaPagamentoInput, formaPagamento);
  }

}
