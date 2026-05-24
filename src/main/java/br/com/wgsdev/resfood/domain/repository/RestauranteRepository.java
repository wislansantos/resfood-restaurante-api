package br.com.wgsdev.resfood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.wgsdev.resfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    
    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();
    
}
