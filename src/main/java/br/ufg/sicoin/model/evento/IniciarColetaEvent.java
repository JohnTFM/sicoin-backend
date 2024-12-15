package br.ufg.sicoin.model.evento;

import br.ufg.sicoin.model.caminhao.Caminhao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class IniciarColetaEvent extends EventoSicoin {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caminhao_id")
    public Caminhao caminhao;

    public IniciarColetaEvent(Caminhao source) {
        caminhao = source;
    }

    @Override
    @Transient
    public String getTipo() {
        return "IniciarColetaEvent";
    }
}
