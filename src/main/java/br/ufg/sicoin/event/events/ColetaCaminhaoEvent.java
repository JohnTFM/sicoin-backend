package br.ufg.sicoin.event.events;

import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.lixeira.Lixeira;
import lombok.Data;

@Data
public class ColetaCaminhaoEvent {

    Caminhao caminhao;

    Lixeira lixeira;

    public ColetaCaminhaoEvent( Lixeira lixeira,Caminhao caminhao ) {
        if(lixeira == null || caminhao == null) {
            throw new IllegalArgumentException("A lixeira e o caminhao não podem ser nulos");
        }
        this.lixeira = lixeira;
        this.caminhao = caminhao;
    }


}
