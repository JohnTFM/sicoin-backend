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


    public Double getVolumeAtual() {
        if(volumeAtual == null) {
            return 0.0;
        }
        return volumeAtual;
    }

    public Double getPesoAtual() {

        if(pesoAtual == null) {
            return 0.0;
        }
        return pesoAtual;
    }

}
