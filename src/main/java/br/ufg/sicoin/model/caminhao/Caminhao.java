package br.ufg.sicoin.model.caminhao;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Caminhao {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    Double latitudeAtual;

    Double longitudeAtual;

    @Enumerated(EnumType.STRING)
    EstadoCaminhao estado;

}
