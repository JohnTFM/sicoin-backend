package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.evento.ColetaEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import java.time.Instant;
import java.util.List;

public interface ColetaEventRepository extends CrudRepository<ColetaEvent, Long> {

    @Query("select ce from ColetaEvent ce where ce.criadoEm>= :limiteInferior and ce.criadoEm<= :limiteSuperior and" +
            " ce.lixeira.id = :id order by ce.criadoEm desc")
    List<ColetaEvent> obterColetasContemporaneas(Instant limiteInferior,Instant limiteSuperior ,String id);




    @Query(value = """
            SELECT ce.*
            FROM coleta_event ce
            """, nativeQuery = true)
    List<ColetaEvent> findAllColetaEvents();

    List<ColetaEvent> findAllByLixeiraId(String lixeiraId);
}