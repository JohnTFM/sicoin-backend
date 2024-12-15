package br.ufg.sicoin.model.caminhao;

import jakarta.persistence.*;


@Entity
public class Caminhao {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

}
