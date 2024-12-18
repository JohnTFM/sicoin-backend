package br.ufg.sicoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoRotaDTO  implements Geolocalizado {
    double kilometrosLimite;
    Double latitude;
    Double longitude;
}
