package br.ufg.sicoin.dto;

import lombok.Data;

@Data
public  class Step {
    private Distance distance;
    private Duration duration;
    private LatLng startLocation;
    private LatLng endLocation;
    private String htmlInstructions;
    private Polyline polyline;
    private String travelMode;

    // Getters e Setters
    // Demais métodos omitidos para concisão
}