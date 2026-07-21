package br.com.wgsdev.resfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.wgsdev.resfood.api.model.mixin.CidadeMixin;
import br.com.wgsdev.resfood.api.model.mixin.CozinhaMixin;
import br.com.wgsdev.resfood.domain.model.Cidade;
import br.com.wgsdev.resfood.domain.model.Cozinha;

@Component
public class JacksonMixinModule extends SimpleModule {

  private static final long serialVersionUID = 1L;

  public JacksonMixinModule() {
    setMixInAnnotation(Cidade.class, CidadeMixin.class);
    setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
  }

}
