package br.com.wgsdev.resfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.wgsdev.resfood.api.model.input.CidadeInput;
import br.com.wgsdev.resfood.domain.model.Cidade;
import br.com.wgsdev.resfood.domain.model.Estado;

@Component
public class CidadeInputDisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public Cidade toDomainObject(CidadeInput cidadeInput) {
    return modelMapper.map(cidadeInput, Cidade.class);
  }

  public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
    // A linha imediatamente abaixo evita uma exception do Hibernate
    cidade.setEstado(new Estado());

    modelMapper.map(cidadeInput, cidade);
  }
  
}
