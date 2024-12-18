package br.ufg.sicoin.event.listeners;

import br.ufg.sicoin.event.transients.ColetaCaminhaoEvent;
import br.ufg.sicoin.model.evento.ColetaEvent;
import br.ufg.sicoin.model.evento.StatusColetaEvento;
import br.ufg.sicoin.repository.ColetaEventRepository;
import br.ufg.sicoin.service.ColetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ColetaCaminhaoListener {

    private final ColetaEventRepository coletaEventRepository;

    private final ColetaService coletaService;

    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public synchronized void handle(ColetaCaminhaoEvent coletaCaminhaoEvent) {
        synchronized (ColetaLixeiraListener.semaphore){
            List<ColetaEvent> coletaEventDb = coletaService.obterColetasContemporaneasPorLixeira(
                    coletaCaminhaoEvent.getLixeira().getId()
            );

            if(coletaEventDb == null || coletaEventDb.isEmpty()) {
                ColetaEvent coletaEvent = new ColetaEvent();
                coletaEvent.setCaminhao(coletaCaminhaoEvent.getCaminhao());
                coletaEvent.setLixeira(coletaCaminhaoEvent.getLixeira());
                coletaEvent.setStatus(StatusColetaEvento.NAO_CONFIRMADA);
                coletaEventRepository.save(coletaEvent);
                return;
            }

            ColetaEvent coletaEvent = coletaEventDb.getFirst();

            coletaEvent.setCaminhao(coletaCaminhaoEvent.getCaminhao());

            coletaEvent.setStatus(StatusColetaEvento.CONFIRMADA);

            applicationEventPublisher.publishEvent(coletaEventRepository.save(coletaEvent));

        }
    }

}
