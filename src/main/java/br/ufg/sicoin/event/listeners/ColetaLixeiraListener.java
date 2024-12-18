package br.ufg.sicoin.event.listeners;

import br.ufg.sicoin.event.transients.ColetaLixeiraEvent;
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
public class ColetaLixeiraListener {

    public static final Object semaphore = new Object();

    private final ColetaEventRepository coletaEventRepository;

    private final ColetaService coletaService;

    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void handleColetaLixeira(ColetaLixeiraEvent coletaLixeiraEvent){
        synchronized (semaphore){
            List<ColetaEvent> coletaEventDb = coletaService.obterColetasContemporaneasPorLixeira(coletaLixeiraEvent.getLixeira().getId());

            if(coletaEventDb == null || coletaEventDb.isEmpty()){
                ColetaEvent coletaEvent = new ColetaEvent();
                coletaEvent.setLixeira(coletaLixeiraEvent.getLixeira());
                coletaEvent.setStatus(StatusColetaEvento.NAO_CONFIRMADA);
                coletaEventRepository.save(coletaEvent);
                return;
            }

            ColetaEvent coletaEvent = coletaEventDb.getFirst();

            coletaEvent.setStatus(StatusColetaEvento.CONFIRMADA);

            applicationEventPublisher.publishEvent(coletaEventRepository.save(coletaEvent));
        }
    }

}
