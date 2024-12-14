package br.ufg.sicoin.dto.googlemaps;

import lombok.Data;

@Data
public  class Bounds {
    private LatLng northeast;
    private LatLng southwest;
}