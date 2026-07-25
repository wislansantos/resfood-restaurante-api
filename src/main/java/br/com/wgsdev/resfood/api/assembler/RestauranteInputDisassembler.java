package br.com.wgsdev.resfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.wgsdev.resfood.api.model.input.RestauranteInput;
import br.com.wgsdev.resfood.domain.model.Cidade;
import br.com.wgsdev.resfood.domain.model.Cozinha;
import br.com.wgsdev.resfood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public Restaurante toDomainObject(RestauranteInput restauranteInput) {
    return modelMapper.map(restauranteInput, Restaurante.class);
  }

  public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
    // As tres linhas de código imediatamente abaixo
    // evitam uma exception do Hibernate
    restaurante.setCozinha(new Cozinha());
    if (restaurante.getEndereco() != null) {
      restaurante.getEndereco().setCidade(new Cidade());
    }

    modelMapper.map(restauranteInput, restaurante);
  }

}
