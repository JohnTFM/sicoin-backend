package br.ufg.sicoin.dto;

import br.ufg.sicoin.model.lixeira.Lixeira;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RespostaDescobrirLixeiraDTO {

    List<Lixeira> lixeirasProximasPreenchidas;

    public RespostaDescobrirLixeiraDTO(List<Lixeira> lixeiras) {
        this.lixeirasProximasPreenchidas = lixeiras;
    }

}
