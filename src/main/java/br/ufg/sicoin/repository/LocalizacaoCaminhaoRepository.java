package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.evento.LocalizacaoCaminhaoEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface LocalizacaoCaminhaoRepository extends JpaRepository<LocalizacaoCaminhaoEvent, Long> {
    List<LocalizacaoCaminhaoEvent> findByCriadoEmBetweenAndCaminhaoId(Instant inicioDoDia, Instant fimDoDia, Long caminhaoId);
}
