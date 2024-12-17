package br.ufg.sicoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequisicaoInformarPossivelColetaDTO implements Geolocalizado {

    private String lixeiraId;

    private Long caminhaoId;

    Double latitude;

    Double longitude;

}
