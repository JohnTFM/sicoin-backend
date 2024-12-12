package br.ufg.sicoin.model.lixeira;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "lixeira")
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
