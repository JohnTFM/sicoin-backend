package br.ufg.sicoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeolocalizacaoDTO {

    protected Double latitude;
    protected Double longitude;

}
