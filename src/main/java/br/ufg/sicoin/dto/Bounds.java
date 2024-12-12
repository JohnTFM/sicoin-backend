package br.ufg.sicoin.dto;

import lombok.Data;

@Data
public  class Bounds {
    private LatLng northeast;
    private LatLng southwest;
}