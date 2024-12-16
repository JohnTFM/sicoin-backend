package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.evento.ColetaEvent;
import org.springframework.data.repository.CrudRepository;

public interface ColetaEventRepository extends CrudRepository<ColetaEvent, Long> {
}
