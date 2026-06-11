package br.com.wgsdev.resfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
    
    private String uri;
    private String title;
    
    ProblemType(String path, String title) {
        this.uri = "https://resfood.com.br" + path;
        this.title = title;
    }
    
}
