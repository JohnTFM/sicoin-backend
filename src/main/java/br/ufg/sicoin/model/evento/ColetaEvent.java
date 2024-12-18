package br.ufg.sicoin.model.evento;

import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.lixeira.Lixeira;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ColetaEvent extends EventoSicoin {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "liexeira_id")
    Lixeira lixeira;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caminhao_id")
    Caminhao caminhao;

    @Enumerated(EnumType.STRING)
    StatusColetaEvento status;

    @Override
    public String getTipo() {
        return "ColetaEvent";
    }
}
