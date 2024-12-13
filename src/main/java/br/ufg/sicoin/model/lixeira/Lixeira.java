package br.ufg.sicoin.model.lixeira;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "lixeira")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lixeira {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    Double latitude;

    Double longitude;

    Double volumeAtual;

    Double pesoAtual;

    Double volumeMaximo;

    Double pesoMaximo;

}
