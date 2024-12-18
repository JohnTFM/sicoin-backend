package br.ufg.sicoin.model.evento;

import br.ufg.sicoin.dto.Geolocalizado;
import br.ufg.sicoin.model.caminhao.Caminhao;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class LocalizacaoCaminhaoEvent extends EventoSicoin implements Geolocalizado {

    @ManyToOne
    @JoinColumn(name = "caminhao_id")
    Caminhao caminhao;

    Double latitude;

    Double longitude;

    @Override
    public String getTipo() {
        return "LocalizacaoCaminhaoEvent";
    }
}
