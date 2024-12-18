package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.evento.LocalizacaoCaminhaoEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoCaminhaoRepository extends JpaRepository<LocalizacaoCaminhaoEvent, Long> {
}
