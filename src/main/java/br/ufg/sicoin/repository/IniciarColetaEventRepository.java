package br.ufg.sicoin.repository;

import br.ufg.sicoin.model.evento.IniciarColetaEvent;
import org.springframework.data.repository.CrudRepository;

public interface IniciarColetaEventRepository extends CrudRepository<IniciarColetaEvent, Long> {

}
