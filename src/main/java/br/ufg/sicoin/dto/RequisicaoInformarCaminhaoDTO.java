package br.ufg.sicoin.dto;

import br.ufg.sicoin.model.caminhao.EstadoCaminhao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RequisicaoInformarCaminhaoDTO extends GeolocalizacaoDTO {

    Long idCaminhao;

    EstadoCaminhao estadoCaminhao;

    Double distanciaMaximaLixeira;

    Double volumeMinimoLixeira;

}
