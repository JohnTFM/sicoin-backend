package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.evento.ColetaEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ColetaEventRepository extends CrudRepository<ColetaEvent, Long> {

    @Query(value = """
            SELECT ce.*
            FROM coleta_event ce
            """, nativeQuery = true)
    List<ColetaEvent> findAllColetaEvents();
}