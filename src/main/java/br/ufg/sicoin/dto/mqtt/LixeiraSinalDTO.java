package br.ufg.sicoin.dto.mqtt;

import lombok.Data;

@Data
public class LixeiraSinalDTO {
    String id;
    Double latitude;
    Double longitude;
    Double volumeAtual;
    Double pesoAtual;
    long timestamp;
}
