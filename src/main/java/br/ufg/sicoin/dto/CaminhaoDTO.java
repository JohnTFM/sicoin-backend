package br.ufg.sicoin.dto;

import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.caminhao.EstadoCaminhao;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CaminhaoDTO {

    Long id;
    Double latitude;
    Double longitude;
    EstadoCaminhao estadoCaminhao;

    public CaminhaoDTO(Caminhao caminhao) {
     this.id = caminhao.getId();
     this.latitude = caminhao.getLatitudeAtual();
     this.longitude = caminhao.getLongitudeAtual();
     this.estadoCaminhao = caminhao.getEstado();
    }

}
