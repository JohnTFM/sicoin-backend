package br.ufg.sicoin.repository;


import br.ufg.sicoin.model.caminhao.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
}
