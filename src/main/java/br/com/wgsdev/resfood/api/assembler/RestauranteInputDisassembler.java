package br.com.wgsdev.resfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.wgsdev.resfood.api.model.input.RestauranteInput;
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
    // A linha imediatamente abaixo evita uma exception do Hibernate
    restaurante.setCozinha(new Cozinha());

    modelMapper.map(restauranteInput, restaurante);
  }

}
