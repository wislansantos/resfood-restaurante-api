package br.com.wgsdev.resfood.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.wgsdev.resfood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}
