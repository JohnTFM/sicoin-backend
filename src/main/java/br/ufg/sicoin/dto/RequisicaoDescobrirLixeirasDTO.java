package br.ufg.sicoin.dto;

import br.ufg.sicoin.model.caminhao.EstadoCaminhao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequisicaoDescobrirLixeirasDTO  implements AtualizaEstadoCaminhao {

    Double distanciaMaximaLixeira;

    Double volumeMinimoLixeira;

    Double latitude;

    Double longitude;

    Long idCaminhao;

    EstadoCaminhao estadoCaminhao;

}
