package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.caminhao.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {

    @Query(value = """
            SELECT c.*
            FROM caminhao c
            """, nativeQuery = true)
    List<Caminhao> findAllCaminhoes();
}