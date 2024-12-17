package br.ufg.sicoin.event.events;

import br.ufg.sicoin.model.lixeira.Lixeira;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ColetaLixeiraEvent {

    Lixeira lixeira;
    boolean isColetada;

}
