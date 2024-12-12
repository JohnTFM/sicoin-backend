package br.ufg.sicoin.model.caminhao;

import br.ufg.sicoin.model.regiao.Regiao;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Caminhao {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    Regiao regiao;


}
