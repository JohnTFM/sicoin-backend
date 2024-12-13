package br.ufg.sicoin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RespostaRotaDTO {

    /**
     * Polyline é a "mini-rota" entre dois pontos somentes. Com várias lixeiras, teremos vários polylines
     */
    private List<String> polylines;

    private Instant timestamp;

    private String googleStatus;

    public RespostaRotaDTO(){
        this.timestamp = Instant.now();
    }

}
