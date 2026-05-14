package br.com.wgsdev.resfood.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
    
    @JoinColumn(name = "endereco_cep")
    private String cep;
    
    @JoinColumn(name = "endereco_logradouro")
    private String logradouro;
    
    @JoinColumn(name = "endereco_numero")
    private String numero;
    
    @JoinColumn(name = "endereco_complemento")
    private String complemento;
    
    @JoinColumn(name = "endereco_bairro")
    private String bairro;
    
    @ManyToOne
    @JoinColumn(name = "endereco_cidade_id")
    private Cidade cidade;
    
}
