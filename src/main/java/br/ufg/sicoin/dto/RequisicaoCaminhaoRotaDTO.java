package br.ufg.sicoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RequisicaoCaminhaoRotaDTO extends GeolocalizacaoDTO {
    double kilometrosLimite;
}
