package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.evento.FinalizarColetaEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinalizarColetaEventRepository extends JpaRepository<FinalizarColetaEvent, Long> {
}
