package br.ufg.sicoin.service;

import br.ufg.sicoin.dto.RequisicaoInformarPossivelColetaDTO;
import br.ufg.sicoin.event.events.ColetaCaminhaoEvent;
import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.evento.ColetaEvent;
import br.ufg.sicoin.repository.CaminhaoRepository;
import br.ufg.sicoin.repository.ColetaEventRepository;
import br.ufg.sicoin.repository.LixeiraRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColetaService {

    private final ColetaEventRepository coletaEventRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final CaminhaoRepository caminhaoRepository;

    private final LixeiraRepository lixeiraRepository;

    public void emitirEventoColeta(RequisicaoInformarPossivelColetaDTO requisicaoInformarPossivelColetaDTO){

        ColetaCaminhaoEvent coletaEvent = new ColetaCaminhaoEvent(
                lixeiraRepository.getReferenceById(requisicaoInformarPossivelColetaDTO.getLixeiraId()),
                caminhaoRepository.getReferenceById(requisicaoInformarPossivelColetaDTO.getCaminhaoId())
        );

        applicationEventPublisher.publishEvent(coletaEvent);

    }

    public List<ColetaEvent> obterColetasContemporaneasPorLixeira(String lixeiraId){
        return coletaEventRepository.obterColetasContemporaneas(
                Instant.now().minus(1L, ChronoUnit.MINUTES),
                Instant.now().plus(1L, ChronoUnit.MINUTES),
                lixeiraId);
    }

    public List<ColetaEvent> obterTodosOsColetaEvents()
    {
        return coletaEventRepository.findAllColetaEvents();
    }
}