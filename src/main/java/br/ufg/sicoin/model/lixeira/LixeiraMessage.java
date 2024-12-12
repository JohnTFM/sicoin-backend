package br.ufg.sicoin.model.lixeira;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LixeiraMessage {

    UUID id;

    Double latitude;

    Double longitude;

    Double volume;

    Double peso;

    String topico;

    Instant timestamp;

}
