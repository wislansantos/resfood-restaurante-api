package br.com.wgsdev.resfood.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.wgsdev.resfood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}
