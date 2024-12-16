package br.ufg.sicoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequisicaoInformarPossivelColetaDTO extends GeolocalizacaoDTO {

    private String lixeiraId;

    private Long caminhaoId;

}
