package br.ufg.sicoin.dto;

import br.ufg.sicoin.model.lixeira.Lixeira;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class RespostaDescobrirLixeiraDTO {

    List<Lixeira> lixeirasProximasPreenchidas;

    public RespostaDescobrirLixeiraDTO(List<Lixeira> lixeiras) {
        this.lixeirasProximasPreenchidas = lixeiras;
        if(this.lixeirasProximasPreenchidas==null || this.lixeirasProximasPreenchidas.isEmpty()){
            this.lixeirasProximasPreenchidas = new ArrayList<>();
        }
    }

    public RespostaDescobrirLixeiraDTO() {
        this.lixeirasProximasPreenchidas = new ArrayList<>();
    }

}
