package br.ufg.sicoin.dto;

import lombok.Data;

import java.util.List;

@Data
public  class Leg {
    private Distance distance;
    private Duration duration;
    private String endAddress;
    private LatLng endLocation;
    private String startAddress;
    private LatLng startLocation;
    private List<Step> steps;

}