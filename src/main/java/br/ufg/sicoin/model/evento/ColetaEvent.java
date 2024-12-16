package br.ufg.sicoin.model.evento;

import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.lixeira.Lixeira;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ColetaEvent extends EventoSicoin{

    @ManyToOne
    @JoinColumn(name = "liexeira_id")
    Lixeira lixeira;

    @ManyToOne
    @JoinColumn(name = "caminhao_id")
    Caminhao caminhao;

    Boolean lixeiraEsvaziou;

    public ColetaEvent(Lixeira lixeira, Caminhao caminhao) {
        this.lixeira = lixeira;
        this.caminhao = caminhao;
    }

    @Override
    public String getTipo() {
        return "ColetaEvent";
    }
}
