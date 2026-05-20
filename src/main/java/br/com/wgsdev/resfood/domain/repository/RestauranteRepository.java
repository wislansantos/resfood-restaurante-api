package br.com.wgsdev.resfood.domain.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wgsdev.resfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    
    @Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAll();
    
}
