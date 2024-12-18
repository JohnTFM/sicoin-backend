package br.ufg.sicoin.event.transients;

import br.ufg.sicoin.model.lixeira.Lixeira;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ColetaLixeiraEvent {

    Lixeira lixeira;
    boolean isColetada;

}
