package br.ufg.sicoin.dto;

import br.ufg.sicoin.model.caminhao.EstadoCaminhao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoIniciarRotaDTO implements AtualizaEstadoCaminhao {

    Long idCaminhao;
    EstadoCaminhao estadoCaminhao;
    Double latitude;
    Double longitude;

}
