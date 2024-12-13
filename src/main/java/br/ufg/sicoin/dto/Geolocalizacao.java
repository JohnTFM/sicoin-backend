package br.ufg.sicoin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Geolocalizacao {

    String latitude;
    String longitude;

}
