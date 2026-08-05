package br.com.wgsdev.resfood.domain.model;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @ManyToMany
  @JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
  private Set<Permissao> permissoes = new HashSet<>();

  public boolean removerPermissao(Permissao permissao) {
    return getPermissoes().remove(permissao);
  }

  public boolean adicionarPermissao(Permissao permissao) {
    return getPermissoes().add(permissao);
  }

}
