package br.ufg.sicoin.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity()
@Table(name = "lixeira")
public class Lixeira {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;



}
