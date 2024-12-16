package br.ufg.sicoin.event.listeners;

import br.ufg.sicoin.model.evento.ColetaEvent;
import br.ufg.sicoin.model.evento.FinalizarColetaEvent;
import br.ufg.sicoin.repository.ColetaEventRepository;
import br.ufg.sicoin.repository.FinalizarColetaEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ColetaListener {

    private final ColetaEventRepository coletaEventRepository;

    @EventListener
    @Async
    public void handleFinalizarColeta(ColetaEvent coletaEvent) {

        try{
            coletaEventRepository.save(coletaEvent);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

        log.info("Coleta Registrada");

    }
}
