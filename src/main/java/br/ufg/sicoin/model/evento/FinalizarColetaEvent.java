package br.ufg.sicoin.model.evento;


import br.ufg.sicoin.model.caminhao.Caminhao;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FinalizarColetaEvent  extends EventoSicoin {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caminhao_id")
    public Caminhao caminhao;

    public FinalizarColetaEvent(Caminhao caminhaoAtualizar) {
        this.caminhao = caminhaoAtualizar;
    }

    @Override
    @Transient
    public String getTipo() {
        return "FinalizarColetaEvent";
    }
}
