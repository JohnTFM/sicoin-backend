package br.ufg.sicoin.service;

import br.ufg.sicoin.dto.RequisicaoInformarPossivelColetaDTO;
import br.ufg.sicoin.model.evento.ColetaEvent;
import br.ufg.sicoin.repository.CaminhaoRepository;
import br.ufg.sicoin.repository.LixeiraRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColetaService {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final CaminhaoRepository caminhaoRepository;

    private final LixeiraRepository lixeiraRepository;

    public void emitirEventoColeta(RequisicaoInformarPossivelColetaDTO requisicaoInformarPossivelColetaDTO){

        ColetaEvent coletaEvent = new ColetaEvent(
                lixeiraRepository.getReferenceById(requisicaoInformarPossivelColetaDTO.getLixeiraId()),
                caminhaoRepository.getReferenceById(requisicaoInformarPossivelColetaDTO.getCaminhaoId())
        );

        applicationEventPublisher.publishEvent(coletaEvent);

    }

}
