package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.lixeira.Lixeira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.Instant;
import java.util.List;

public interface LixeiraRepository extends JpaRepository<Lixeira, Long> {

    @Query("update Lixeira l set " +
            "l.pesoAtual = :pesoAtual, " +
            "l.volumeAtual = :volumeAtual, " +
            "l.ultimaAtualizacao = :timestamp" +
            " where l.id = :id")
    @Modifying
    void atualizarLixeiraDoMqqtt(
            @Param("id") String id,
            @Param("pesoAtual") Double pesoAtual,
            @Param("volumeAtual") Double volumeAtual,
            @Param("timestamp") Instant timestamp
            );

    @Query(value = """
        SELECT l.*
        FROM lixeira l
        WHERE (6371 * acos(
                   cos(radians(:lat)) * cos(radians(l.latitude)) *
                   cos(radians(l.longitude) - radians(:lon)) +
                   sin(radians(:lat)) * sin(radians(l.latitude))
               )) <= :radius
        ORDER BY (6371 * acos(
                   cos(radians(:lat)) * cos(radians(l.latitude)) *
                   cos(radians(l.longitude) - radians(:lon)) +
                   sin(radians(:lat)) * sin(radians(l.latitude))
               )) ASC
        """, nativeQuery = true)
    List<Lixeira> findNearbyLixeiras(
            @Param("lat") double latitude,
            @Param("lon") double longitude,
            @Param("radius") double radiusInKm
    );


}
