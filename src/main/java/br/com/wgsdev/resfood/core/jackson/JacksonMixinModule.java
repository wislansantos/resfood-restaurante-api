package br.com.wgsdev.resfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.wgsdev.resfood.api.model.mixin.RestauranteMixin;
import br.com.wgsdev.resfood.domain.model.Restaurante;

@Component
public class JacksonMixinModule extends SimpleModule {

  private static final long serialVersionUID = 1L;

  public JacksonMixinModule() {
    setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
  }

}
