package br.ufg.sicoin.dto;

import br.ufg.sicoin.model.caminhao.EstadoCaminhao;


public interface AtualizaEstadoCaminhao extends Geolocalizado {
    Long getIdCaminhao();
    EstadoCaminhao getEstadoCaminhao();
}
