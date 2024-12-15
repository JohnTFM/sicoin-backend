package br.ufg.sicoin.dto;

import br.ufg.sicoin.model.lixeira.Lixeira;
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

    private List<Lixeira> lixeiras;

    private Instant timestamp;

    private String googleStatus;

    private String backendStatus;

    public RespostaRotaDTO(){
        this.timestamp = Instant.now();
    }

}
