package br.ufg.sicoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoCaminhaoRotaDTO {

    String latitude;

    String longitude;

    public Geolocalizacao obterGeolocalizacao(){
        return new Geolocalizacao(latitude, longitude);
    }

}
