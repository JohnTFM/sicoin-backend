package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.lixeira.Lixeira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.Instant;

public interface LixeiraRepository extends JpaRepository<Lixeira, Long> {

    @Query("update Lixeira l set " +
            "l.pesoAtual = :pesoAtual, " +
            "l.volumeAtual = :volumeAtual, " +
            "l.ultimaAtualizacao = :timestamp," +
            "l.latitude = :latitude," +
            "l.longitude = :longitude" +
            " where l.id = :id")
    @Modifying
    void atualizarLixeiraDoMqqtt(
            @Param("id") String id,
            @Param("pesoAtual") Double pesoAtual,
            @Param("volumeAtual") Double volumeAtual,
            @Param("timestamp") Instant timestamp,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude
            );

}
