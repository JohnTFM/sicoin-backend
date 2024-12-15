package br.ufg.sicoin.model.lixeira;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "lixeira")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lixeira {

    /**
     * Ex: L1, L2, L3, Ln
     */
    @Id
    public String id;

    Double latitude;

    Double longitude;

    /**
     * Em litros (L)
     */
    Double volumeAtual;

    /**
     * Em litros (L)
     */
    Double volumeMaximo;

    /**
     * Em kilograma (Kg)
     */
    Double pesoAtual;

    /**
     * Em kilograma (Kg)
     */
    Double pesoMaximo;

    Instant ultimaAtualizacao;

    Instant momentoUltimaColeta;

}
