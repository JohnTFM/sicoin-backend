package br.ufg.sicoin.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class RespostaRotaDTO {

    /**
     * Polyline é a "mini-rota" entre dois pontos somentes. Com várias lixeiras, teremos vários polylines
     */
    private List<String> polylines;

    private Instant timestamp;

    public RespostaRotaDTO(){
        this.timestamp = Instant.now();
    }

}
