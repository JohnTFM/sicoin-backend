package br.ufg.sicoin.model.regiao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Regiao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String nome;


}
