package br.ufg.sicoin.event.listeners;

import br.ufg.sicoin.model.evento.ColetaEvent;
import br.ufg.sicoin.model.evento.StatusColetaEvento;
import br.ufg.sicoin.repository.ColetaEventRepository;
import br.ufg.sicoin.repository.LixeiraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class ColetaConfirmadaListener {

    private final LixeiraRepository lixeiraRepository;

    @EventListener
    @Transactional
    public void handle(ColetaEvent coletaEvent) {

        if(coletaEvent==null || !coletaEvent.getStatus().equals(StatusColetaEvento.CONFIRMADA)){
            return;
        }

        try{
            lixeiraRepository.setarUltimaColeta(coletaEvent.getLixeira().getId(), Instant.now());

        }catch (Exception e){
            log.info(e.getMessage(),e);
        }

    }

}
