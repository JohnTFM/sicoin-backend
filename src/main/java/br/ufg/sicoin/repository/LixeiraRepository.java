package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.lixeira.Lixeira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LixeiraRepository extends JpaRepository<Lixeira, Long> {

}
